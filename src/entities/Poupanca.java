package entities;

import interfaces.AccountTransaction;

import java.time.LocalDate;

public class Poupanca extends Conta implements AccountTransaction {

    private final double TAXA_RENDIMENTO = 0.0005;

    private LocalDate ultimoRendimento;

    public Poupanca(Cliente titular, Double saldo) {
        super(titular, saldo);
        this.saldo = saldo;
        this.ultimoRendimento = proximoDia5DoMesAtual();
    }

    private LocalDate proximoDia5DoMesAtual() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate proximoDia5 = LocalDate.of(dataAtual.getYear(), dataAtual.getMonthValue(), 5);

        if (dataAtual.getDayOfMonth() >= 5) {
            proximoDia5 = proximoDia5.plusMonths(1);
        }

        return proximoDia5;
    }


        public void updateBalanceByDate() {
            LocalDate dataAtual = LocalDate.now();
            LocalDate ultimoRendimentoCopy = ultimoRendimento;

            while (ultimoRendimentoCopy.isBefore(dataAtual)) {
                ultimoRendimentoCopy = ultimoRendimentoCopy.plusMonths(1);


                if (ultimoRendimentoCopy.getDayOfMonth() == 5) {
                    double rendimento = this.getSaldo() * TAXA_RENDIMENTO;
                    this.depositar(rendimento);
                }
            }

            ultimoRendimento = proximoDia5DoMesAtual();
        }


        public void sacar(double valor) throws IllegalArgumentException {
            updateBalanceByDate();
            if(valor <= this.getSaldo()) {
                saldo -= valor;
            } else
                throw new IllegalArgumentException("O saldo é insuficente para o saque");

        }

        public void transferir(Double valor, Conta conta) throws IllegalArgumentException {
            updateBalanceByDate();
            if(valor <= this.getSaldo()) {
                this.saldo -= valor;
                conta.depositar(valor);
            } else
                throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
        }


    @Override
    public Double getSaldo() {
        updateBalanceByDate();
        return saldo;
    }


}
