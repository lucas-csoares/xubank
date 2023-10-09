package entities.conta;


import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class ContaCorrente extends Conta implements OperacoesConta {


    private final Double TAXA_MENSAL = 20.00;
    private Double saqueEspecial = 200.00;
    private LocalDate ultimoDesconto;

    public ContaCorrente(Cliente cliente, Double saldo) throws Exception {
        super(cliente, saldo);
        this.ultimoDesconto = dataRegistro;
    }

    @Override
    public Double getSaldo() {
        atualizarSaldo();
        return saldo;
    }

    public void setSaqueEspecial(Double saqueEspecial) {
        this.saqueEspecial = saqueEspecial;
    }

    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Tipo    : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro);
    }

    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo + saqueEspecial)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        if (valor > saldo) {
            saqueEspecial = saqueEspecial - saldo;
            saldo = 0.0;
        } else
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
        aplicarTaxaMensal();
    }

    private void aplicarTaxaMensal() {

        LocalDate dataAtual = LocalDate.now();
        long mesesDesdeUltimoDesconto = ChronoUnit.MONTHS.between(ultimoDesconto, dataAtual);

        if (mesesDesdeUltimoDesconto > 0) {
            double descontoTotal = TAXA_MENSAL * mesesDesdeUltimoDesconto;
            saldo -= descontoTotal;
            ultimoDesconto = dataAtual;
            setSaqueEspecial(200.00);
        }
    }

}
