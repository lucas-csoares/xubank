package entities;

import interfaces.SaqueConta;

public class Poupanca extends Conta implements SaqueConta {

    private final double TAXA_RENDIMENTO = 0.0005;

    public Poupanca(Cliente titular) {
        super(titular);
    }

    @Override
    public double sacar(double valor) {
        return 0;
    }
}
