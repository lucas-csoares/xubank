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

    public Poupanca(Cliente cliente, Double saldo, String date) {
        super(cliente, saldo, date);
        this.saldo = saldo;
        this.ultimoRendimento = dataRegistro;
    }

    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Tipo    : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        saldo -= valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    public void transferir(Double valor, Conta conta) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferencia de " + valor + " não permitido. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta, LocalDateTime.now()));
    }

    public void atualizarSaldo() {

        aplicarRendaFixa();
    }

    private void aplicarRendaFixa() {

        LocalDate dataAtual = LocalDate.now();
        LocalDate proximaDataRendimento = obterProximaDataRendimento(ultimoRendimento);

        if (dataAtual.isAfter(proximaDataRendimento)) {
            int mesesRendimento = calcularDiferencaMeses(proximaDataRendimento, dataAtual);
            this.depositar(saldo * mesesRendimento * TAXA_RENDIMENTO);
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
