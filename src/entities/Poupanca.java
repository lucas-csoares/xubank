package entities;

import interfaces.SaqueConta;

public class Poupanca extends Conta {

    private final double TAXA_RENDIMENTO = 0.0005;

    public Poupanca(Cliente titular) {
        super(titular);
    }

    @Override
    public void sacar(double valor) {

    }
}
