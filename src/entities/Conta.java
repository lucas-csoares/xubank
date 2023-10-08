package entities;

import java.time.LocalDate;

public class Conta {

    protected Cliente titular;
    protected Double saldo;

    protected LocalDate dataRegistro;


    public Conta(Cliente titular, Double saldo) {
        titular.addConta(this);
        this.titular = titular;
        this.saldo = saldo;
        dataRegistro = LocalDate.now();
    }

    public void depositar(double valor) throws IllegalArgumentException {
        if(valor > 0)
            saldo += valor;
        else
            throw new IllegalArgumentException("Valor de depósito inválido");
    }


    //todo: finalizar, obs.:  últimos 30 dias
//    public double consultarExtrato(){
//
//    }


    public void setTitular(Cliente titular) {

        this.titular = titular;
    }



    //todo: getSaltdo() substitui o "consultarSaldo()"
    public Double getSaldo() {

        return saldo;
    }

    public void setSaldo(Double saldo) {

        this.saldo = saldo;
    }

    public Cliente getTitular() {

        return titular;
    }


}
