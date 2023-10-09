package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;

import java.time.LocalDate;

public class Conta {

    private static int PROX_ID = 0;

    protected Cliente cliente;
    protected Double saldo;
    protected LocalDate dataRegistro;
    protected int id;

    public Conta(Cliente cliente, Double saldo) {
        cliente.addConta(this);
        this.cliente = cliente;
        this.saldo = saldo;
        this.id = PROX_ID++;
        dataRegistro = LocalDate.now();
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {

        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getTitular() {
        return cliente;
    }

    public void setTitular(Cliente cliente) {
        this.cliente = cliente;
    }

    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro);
    }

    public void depositar(double valor) {
        if (valor < 0)
            throw new IllegalArgumentException("Valor de depósito inválido");
        saldo += valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.DEPOSITO, valor));
    }

}
