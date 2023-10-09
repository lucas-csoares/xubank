package entities.conta;

import entities.cliente.Cliente;
import entities.conta.Conta;
import interfaces.OperacoesConta;

public final class RendaFixa extends Conta implements OperacoesConta {

    private final Double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;

    private Double rendimento;

    public RendaFixa(Cliente titular, Double saldo) {
        super(titular, saldo);
        //this.rendimento = rendimento;
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


    public void sacar(double valor) throws IllegalArgumentException {

    }

    public void transferir(Double valor, Conta conta) throws IllegalArgumentException {

    }


    public void atualizarSaldo() {

    }




}
