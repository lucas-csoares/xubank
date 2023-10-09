package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;

import java.time.LocalDate;
import java.time.Period;

public final class Poupanca extends Conta implements OperacoesConta {

    private final double TAXA_RENDIMENTO = 0.005;

    private LocalDate ultimoRendimento;

    public Poupanca(Cliente titular, Double saldo) {
        super(titular, saldo);
        this.saldo = saldo;
        this.ultimoRendimento = dataRegistro;
    }

    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        saldo -= valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor));
    }

    public void transferir(Double valor, Conta conta) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferencia de " + valor + " não permitido. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta));
    }

    public void atualizarSaldo() {
        aplicarRendaFixa();
    }

    private void aplicarRendaFixa() {

        LocalDate dataAtual = LocalDate.now();
        LocalDate proximaDataRendimento = obterProximaDataRendimento(ultimoRendimento);

        if (dataAtual.isAfter(proximaDataRendimento)) {
            int mesesRendimento = calcularDiferencaMeses(proximaDataRendimento, dataAtual);
            double rendimento = mesesRendimento * TAXA_RENDIMENTO;
            this.depositar(saldo * mesesRendimento * TAXA_RENDIMENTO);
        }
    }

    /*todo: conferir se essa funcao esta funcionando corretamente*/
    private int calcularDiferencaMeses(LocalDate dataInicial, LocalDate dataFinal) {
        return Period.between(dataInicial, dataFinal).getMonths() + 1;
    }


    public LocalDate obterProximaDataRendimento(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), 5).plusMonths(1);
    }


    @Override
    public Double getSaldo() {
        atualizarSaldo();
        return saldo;
    }


}
