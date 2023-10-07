package entities;

import interfaces.SaqueConta;

public class Investimento extends Conta implements SaqueConta {

    private Double rendimento;

    private final Double TAXA_SOBRE_RENDIMENTO_NO_SAQUE = 0.015;

    private final  Double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;

    public Investimento(Cliente titular) {
        super(titular);
        rendimento = 0.0;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }

    public Double getTAXA_SOBRE_RENDIMENTO_NO_SAQUE() {
        return TAXA_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    public Double getIMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE() {
        return IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    @Override
    public double sacar(double valor) {
        return 0;
    }
}
