package entities;


import interfaces.OperacoesConta;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class ContaCorrente extends Conta implements OperacoesConta {


    private final Double TAXA_MENSAL = 20.00;
    private static Double saqueEspecial = 200.00;

    private LocalDate ultimoDesconto;

    public ContaCorrente(Cliente titular, Double saldo) throws Exception {
        super(titular, saldo);
        if (!SaldoMinimo(saldo)) {
            throw new Exception("Saldo insuficiente para abrir a conta corrente");
        }
        this.saldo = saldo - TAXA_MENSAL;
        this.ultimoDesconto = dataRegistro;
    }

    public boolean SaldoMinimo(Double saldo) {
        return saldo >= TAXA_MENSAL;
    }


    public void sacar(double valor) throws IllegalArgumentException {
        updateBalanceByDate();
        if(valor <= this.getSaldo()) {
            this.saldo -= valor;
        } else if(valor <= this.getSaldo() + this.getsaqueEspecial()) {
            this.setSaldo(0.0);
            setSaqueEspecial(valor-this.getSaldo());
        } else
            throw new IllegalArgumentException("O saldo é insuficente para o saque");
    }

    @Override
    public Double getSaldo() {
        updateBalanceByDate();
        return saldo;
    }


    public void transferir(Double valor, Conta conta) throws IllegalArgumentException{
        updateBalanceByDate();
        if(valor <= this.getSaldo()) {
            this.saldo -= valor;
            conta.depositar(valor);
        } else
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
    }

    public void updateBalanceByDate() {
        LocalDate dataAtual = LocalDate.now();

        long mesesDesdeUltimoDesconto = ChronoUnit.MONTHS.between(ultimoDesconto, dataAtual);

        if (mesesDesdeUltimoDesconto > 0) {
            double descontoTotal = this.getTAXA_MENSAL() * mesesDesdeUltimoDesconto;
            saldo -= descontoTotal;
            ultimoDesconto = dataAtual;
            setSaqueEspecial(200.00);
        }
    }


//    public boolean monthLimit() {
//        LocalDate dataAtual = LocalDate.now();
//        return dataAtual.isAfter(ultimoDesconto) && dataAtual.isBefore(ultimoDesconto.plusDays(30));
//    }



    public Double getTAXA_MENSAL() {

        return TAXA_MENSAL;
    }

    public Double getsaqueEspecial() {

        return saqueEspecial;
    }

    public static void setSaqueEspecial(Double saqueEspecial) {
        saqueEspecial = saqueEspecial;
    }

}
