package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public final class Poupanca extends Conta implements OperacoesConta {

    private final double TAXA_RENDIMENTO = 0.005;

    private LocalDate ultimoRendimento;

    // O construtor `public Poupanca(Cliente cliente, Double saldo, String date)` está inicializando uma
    // nova instância da classe `Poupanca`.
    public Poupanca(Cliente cliente, Double saldo, String date) {
        super(cliente, saldo, date);
        this.saldo = saldo;
        this.ultimoRendimento = dataRegistro;
    }

    /**
     * A função "imprimir" imprime informações sobre um objeto, incluindo seu id, tipo, saldo e data de
     * registro.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Tipo    : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    /**
     * A função "sacar" permite ao usuário sacar uma quantia especificada de sua conta, atualizando o
     * saldo e registrando a transação.
     * 
     * @param valor O parâmetro "valor" representa a quantia de dinheiro que o usuário deseja sacar de sua
     * conta.
     */
    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        saldo -= valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    /**
     * A função "transferir" transfere uma quantia especificada de dinheiro de uma conta para outra,
     * atualizando os saldos e registrando a transação.
     * 
     * @param valor A quantia de dinheiro a ser transferida.
     * @param conta O parâmetro "conta" é um objeto da classe "Conta", que representa uma conta bancária.
     */
    public void transferir(Double valor, Conta conta) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferência de " + valor + " não permitida. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta, LocalDateTime.now()));
    }

    /**
     * A função "atualizarSaldo" aplica o rendimento da renda fixa.
     */
    public void atualizarSaldo() {

        aplicarRendaFixa();
    }

    /**
     * A função "aplicarRendaFixa" calcula e aplica o rendimento da renda fixa ao saldo atual se a data
     * atual for posterior à próxima data de pagamento de juros.
     */
    private void aplicarRendaFixa() {

        LocalDate dataAtual = LocalDate.now();
        LocalDate proximaDataRendimento = obterProximaDataRendimento(ultimoRendimento);

        if (dataAtual.isAfter(proximaDataRendimento)) {
            int mesesRendimento = calcularDiferencaMeses(proximaDataRendimento, dataAtual);
            this.depositar(saldo * mesesRendimento * TAXA_RENDIMENTO);
            ultimoRendimento = dataAtual;
        }
    }

    private int calcularDiferencaMeses(LocalDate dataInicial, LocalDate dataFinal) {
        return Period.between(dataInicial, dataFinal).getMonths() + 1;
    }

    /**
     * A função "obterProximaDataRendimento" retorna a próxima data de rendimento com base na data fornecida.
     * 
     * @param data O parâmetro "data" é do tipo LocalDate e representa uma data específica.
     * @return O método retorna um objeto LocalDate.
     */
    public LocalDate obterProximaDataRendimento(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), 5).plusMonths(1);
    }
}
