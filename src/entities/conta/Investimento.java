package entities.conta;

import entities.cliente.Cliente;
import entities.conta.Conta;
import interfaces.OperacoesConta;

public final class Investimento extends Conta implements OperacoesConta {

    private Double rendimento;

    private final Double TAXA_SOBRE_RENDIMENTO_NO_SAQUE = 0.015;

    private final  Double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;

    public Investimento(Cliente titular, Double saldo) {
        super(titular, saldo);
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


    public void sacar(double valor) throws IllegalArgumentException {

    }

    public void transferir(Double valor, Conta conta) throws IllegalArgumentException {

    }


    public void atualizarSaldo() {

    }
}
