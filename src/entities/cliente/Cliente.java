package entities.cliente;

import entities.conta.Conta;
import entities.conta.Transacao;
import enums.TransacaoCategoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    protected static int TAXA_MENSAL = 0;
    protected static int FIDELIDADE_PONTOS = 0;
    protected static int ACUMULO_PONTOS = 0;
    protected static int SALDO_PARA_PONTOS = 0;
    private static int PROX_ID = 0;

    private final int id;
    private String nome;
    private String cpf;
    private String senha;
    private List<Conta> contas;
    private List<Transacao> transacoes;

    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.id = PROX_ID++;
        contas = new ArrayList<>();
        transacoes = new ArrayList<>();
    }

    public Cliente() {
        this.nome = this.cpf = this.senha = "";
        this.id = PROX_ID++;
        transacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void addConta(Conta conta) {
        this.getContas().add(conta);
    }

    public void removerConta(Conta conta) {
        this.getContas().remove(conta);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public void addTransacao(Transacao transacao) {
        this.getTransacoes().add(transacao);
    }

    public void imprimir() {
        System.out.println("-------------------------");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Cliente : %1s\n", this.nome);
        System.out.printf("CPF     : %1s\n", this.cpf);
        System.out.printf("Senha   : %1s\n", this.senha);
        System.out.print("Contas  : \n");
        this.contas.forEach(Conta::imprimir);
        System.out.println("-------------------------");
    }

    public void consultarSaldo() throws Exception {

        Scanner scanner = new Scanner(System.in);

        contas.forEach(conta -> {
            System.out.println("#####");
            System.out.printf("Id      : %1s\n", conta.getId());
            System.out.printf("Tipo    : %1s\n", conta.getClass().getSimpleName());
        });
        System.out.print("\nInforme o id da conta deseja consultar o saldo: ");

        int idConta = scanner.nextInt();
        Conta contaEscolhida = obterConta(idConta);

        if (contaEscolhida != null)
            contaEscolhida.imprimir();
        else
            throw new Exception("id invalido!");
    }

    private Conta obterConta(int id) {
        return this.contas.stream()
                .filter(conta -> conta.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void consultarExtrato() {
        System.out.println("Extrato da conta:");
        this.transacoes.forEach(Transacao::imprimir);
    }

}

