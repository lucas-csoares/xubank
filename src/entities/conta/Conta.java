package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import utils.DataHora;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Conta {

    private static int PROX_ID = 0;

    protected Cliente cliente;
    protected Double saldo;
    protected LocalDate dataRegistro;
    protected int id;

    // O construtor `public Conta(Cliente cliente, Double saldo, String date)` está inicializando uma nova
    // instância da classe `Conta`.
    public Conta(Cliente cliente, Double saldo, String date) {
        cliente.addConta(this);
        this.cliente = cliente;
        this.saldo = saldo;
        this.id = PROX_ID++;
        dataRegistro = LocalDate.parse(date, DataHora.fmtData);
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

    /**
     * A função "imprimir" imprime o id, saldo e data de registro de um objeto.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    /**
     * A função "depositar" adiciona um valor fornecido ao saldo da conta e registra a transação.
     * 
     * @param valor O parâmetro "valor" representa a quantia de dinheiro a ser depositada em uma
     * conta.
     */
    public void depositar(double valor) {
        if (valor < 0)
            throw new IllegalArgumentException("Valor de depósito inválido");
        saldo += valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.DEPOSITO, valor, LocalDateTime.now()));
    }

}
