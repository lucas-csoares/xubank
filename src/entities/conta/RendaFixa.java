package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;

import java.time.LocalDate;
import java.time.Period;

public final class RendaFixa extends Conta implements OperacoesConta {

    private final double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;
    private final double RENDIMENTO;

    private LocalDate ultimoRendimento;
    private double ultimoValorRendimento;

    public RendaFixa(Cliente titular, Double saldo, double RENDIMENTO, String date) {
        super(titular, saldo, date);
        this.RENDIMENTO = RENDIMENTO;
        this.ultimoRendimento = dataRegistro;
    }

    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id        : %1s\n", this.id);
        System.out.printf("Tipo      : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Rendimento: %1s%%\n", this.RENDIMENTO * 100);
        System.out.printf("Saldo     : %1s\n", this.saldo);
        System.out.printf("Registro  : %1s\n", this.dataRegistro);
    }

    public Double getIMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE() {
        return IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    public Double getRENDIMENTO() {
        return RENDIMENTO;
    }


    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        double impostoSobreRendimento = calcularImpostoSobreRendimento();
        saldo -= (valor + impostoSobreRendimento);
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDate.now()));
    }

    private double calcularImpostoSobreRendimento() {
        return ultimoValorRendimento * IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    public void transferir(Double valor, Conta conta) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferencia de " + valor + " não permitido. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta, LocalDate.now()));
    }


    public void atualizarSaldo() {
        aplicarRendaFixa();
    }

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

    /*todo: conferir se essa funcao esta funcionando corretamente*/
    private int calcularDiferencaMeses(LocalDate dataInicial, LocalDate dataFinal) {
        return Period.between(dataInicial, dataFinal).getMonths() + 1;
    }

    public LocalDate obterProximaDataRendimento(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), 5).plusMonths(1);
    }


}
