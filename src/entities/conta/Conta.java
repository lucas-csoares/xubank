package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import utils.DataHora;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Conta {

    private static int PROX_ID = 0;
    protected Cliente cliente;
    protected Double saldo;
    protected LocalDate dataRegistro;
    protected int id;

    /**
     * Construtor da classe Conta, utilizado para criar uma nova instância de conta.
     *
     * @param cliente Cliente associado à conta.
     * @param saldo   Saldo inicial da conta.
     * @param date    Data de registro da conta no formato "DD/MM/yyyy".
     */
    public Conta(Cliente cliente, Double saldo, String date) {
        cliente.addConta(this);
        this.cliente = cliente;
        this.saldo = saldo;
        this.id = PROX_ID++;
        this.dataRegistro = LocalDate.parse(date, DataHora.fmtData);
    }

    /**
     * Obtém o saldo atual da conta.
     *
     * @return Saldo atual da conta.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo da conta.
     *
     * @param saldo Novo saldo da conta.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtém o identificador único da conta.
     *
     * @return Identificador único da conta.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único da conta.
     *
     * @param id Novo identificador da conta.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o titular da conta, ou seja, o cliente associado.
     *
     * @return Cliente titular da conta.
     */
    public Cliente getTitular() {
        return cliente;
    }

    /**
     * Define o titular da conta, ou seja, o cliente associado.
     *
     * @param cliente Novo cliente titular da conta.
     */
    public void setTitular(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Imprime as informações básicas da conta, incluindo o identificador, saldo e data de registro.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    /**
     * Realiza um depósito na conta, adicionando o valor fornecido ao saldo e registrando a transação.
     *
     * @param valor Valor a ser depositado na conta.
     * @throws IllegalArgumentException Lançada se o valor de depósito fornecido for negativo.
     */
    public void depositar(double valor) {
        if (valor < 0)
            throw new IllegalArgumentException("Valor de depósito inválido");
        saldo += valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.DEPOSITO, valor, LocalDateTime.now()));
    }
}
