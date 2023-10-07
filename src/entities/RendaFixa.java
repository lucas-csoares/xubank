package entities;

import interfaces.SaqueConta;

public class RendaFixa extends Conta implements SaqueConta {

    private final Double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;

    private Double rendimento;

    public RendaFixa(Cliente titular, Double rendimento) {
        super(titular);
        this.rendimento = rendimento;
    }


    public Double getIMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE() {
        return IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }

    @Override
    public double sacar(double valor) {
        return 0;
    }




}
