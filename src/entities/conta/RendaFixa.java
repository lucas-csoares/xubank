package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public final class RendaFixa extends Conta implements OperacoesConta {

    private final double RENDIMENTO;

    private LocalDate ultimoRendimento;
    private double ultimoValorRendimento;

    // O construtor `public RendaFixa(Cliente titular, Double saldo, double RENDIMENTO, String date)`
    // está inicializando uma nova instância da classe `RendaFixa`.
    public RendaFixa(Cliente titular, Double saldo, double RENDIMENTO, String date) {
        super(titular, saldo, date);
        this.RENDIMENTO = RENDIMENTO;
        this.ultimoRendimento = dataRegistro;
    }

    /**
     * A função "imprimir" imprime informações sobre um objeto, incluindo seu id, tipo, taxa de juros,
     * saldo e data de registro.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id        : %1s\n", this.id);
        System.out.printf("Tipo      : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Rendimento: %1s%%\n", this.RENDIMENTO * 100);
        System.out.printf("Saldo     : %1s\n", this.saldo);
        System.out.printf("Registro: %s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    public Double getRENDIMENTO() {
        return RENDIMENTO;
    }

    /**
     * A função "sacar" em Java permite que um usuário saque uma quantia especificada de sua conta,
     * atualize o saldo e adicione um registro de transação.
     * 
     * @param valor O parâmetro "valor" representa a quantia de dinheiro que o usuário deseja sacar de sua
     * conta.
     */
    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        double impostoSobreRendimento = calcularImpostoSobreRendimento();
        saldo -= (valor + impostoSobreRendimento);
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    private double calcularImpostoSobreRendimento() {
        return ultimoValorRendimento * IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    /**
     * A função "transferir" transfere uma quantia especificada de dinheiro de uma conta para outra,
     * atualiza os saldos e registra a transação.
     * 
     * @param valor A quantia de dinheiro a ser transferida.
     * @param conta O parâmetro "conta" é um objeto do tipo "Conta", que representa uma conta bancária.
     */
    public void transferir(Double valor, Conta conta) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferência de " + valor + " não permitida. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta, LocalDateTime.now()));
    }

    public void atualizarSaldo() {
        aplicarRendaFixa();
    }

    /**
     * A função "aplicarRendaFixa" calcula e aplica o rendimento fixo ao saldo atual se a data atual for
     * posterior à próxima data de pagamento de juros.
     */
    private void aplicarRendaFixa() {

        LocalDate dataAtual = LocalDate.now();
        LocalDate proximaDataRendimento = obterProximaDataRendimento(ultimoRendimento);

        if (dataAtual.isAfter(proximaDataRendimento)) {
            int mesesRendimento = calcularDiferencaMeses(proximaDataRendimento, dataAtual);
            ultimoValorRendimento = saldo * mesesRendimento * RENDIMENTO;
            this.depositar(ultimoValorRendimento);
            ultimoRendimento = dataAtual;
        }
    }

    /*todo: conferir se essa função está funcionando corretamente*/
    private int calcularDiferencaMeses(LocalDate dataInicial, LocalDate dataFinal) {
        return Period.between(dataInicial, dataFinal).getMonths() + 1;
    }

    /**
     * A função "obterPróximaDataRendimento" retorna a próxima data de rendimento com base na data fornecida.
     * 
     * @param data O parâmetro "data" é do tipo LocalDate e representa uma data específica.
     * @return O método está retornando um objeto LocalDate.
     */
    public LocalDate obterProximaDataRendimento(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), 5).plusMonths(1);
    }
}
