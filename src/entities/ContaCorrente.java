package entities;

import interfaces.SaqueConta;

class ContaCorrente extends Conta {


    private final Double TAXA_MENSAL = 20.00;
    private final Double SAQUE_ESPECIAL = 200.00;

    public ContaCorrente(Cliente titular) {
        super(titular);
    }

    @Override
    public void sacar(double valor) {

    }

    public Double getTAXA_MENSAL() {
        return TAXA_MENSAL;
    }

    public Double getSAQUE_ESPECIAL() {
        return SAQUE_ESPECIAL;
    }
}
