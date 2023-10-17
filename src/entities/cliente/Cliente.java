package entities.cliente;

import entities.conta.Conta;
import entities.conta.Transacao;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    // Propriedades estáticas compartilhadas por todas as instâncias da classe Cliente
    protected static int TAXA_MENSAL = 0;
    protected static int FIDELIDADE_PONTOS = 0;
    protected static int ACUMULO_PONTOS = 0;
    protected static int SALDO_PARA_PONTOS = 0;
    private static int PROX_ID = 0;

    // Propriedades da classe Cliente
    private final int id;
    private final LocalDate dataCriacao;
    private String nome;
    private String cpf;
    private String senha;
    private List<Conta> contas;
    private List<Transacao> transacoes;

    /**
     * Construtor para criar uma instância de Cliente com nome, CPF e senha.
     *
     * @param nome  Nome do cliente.
     * @param cpf   CPF do cliente.
     * @param senha Senha do cliente.
     */
    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.id = PROX_ID++;
        this.dataCriacao = LocalDate.now();
        contas = new ArrayList<>();
        transacoes = new ArrayList<>();
    }

    /**
     * Construtor vazio que cria uma instância de Cliente com valores padrão.
     */
    public Cliente() {
        this.nome = this.cpf = this.senha = "";
        this.id = PROX_ID++;
        this.dataCriacao = LocalDate.now();
        contas = new ArrayList<>();
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

    /**
     * Adiciona uma conta à lista de contas do cliente.
     *
     * @param conta Conta a ser adicionada.
     */
    public void addConta(Conta conta) {
        this.getContas().add(conta);
    }

    /**
     * Remove uma conta da lista de contas do cliente.
     *
     * @param conta Conta a ser removida.
     */
    public void removerConta(Conta conta) {
        this.getContas().remove(conta);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    /**
     * Adiciona uma transação à lista de transações do cliente.
     *
     * @param transacao Transação a ser adicionada.
     */
    public void addTransacao(Transacao transacao) {
        this.getTransacoes().add(transacao);
    }

    /**
     * Imprime informações do cliente, incluindo seu id, nome, CPF, senha, contas e transações.
     */
    public void imprimir() {
        System.out.println("-------------------------");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Cliente : %1s\n", this.nome);
        System.out.printf("CPF     : %1s\n", this.cpf);
        System.out.printf("Senha   : %1s\n", this.senha);
        System.out.printf("Taxa    : %1s\n", calcularTaxaMensal());
        System.out.printf("Pontos  : %1s\n", calcularPontos());
        System.out.print("Contas  : \n");
        this.contas.forEach(Conta::imprimir);
        System.out.println("-------------------------");
    }

    /**
     * Consulta o saldo de uma conta específica do cliente.
     *
     * @throws Exception Se o id da conta fornecido for inválido.
     */
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

    /**
     * Obtém uma conta específica com base no id fornecido.
     *
     * @param id Id da conta a ser obtida.
     * @return A conta correspondente ao id ou null se não encontrada.
     */
    public Conta obterConta(int id) {
        return this.contas.stream()
                .filter(conta -> conta.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Consulta o extrato de transações do cliente.
     */
    public void consultarExtrato() {
        System.out.println("Extrato da conta:");
        this.transacoes.forEach(Transacao::imprimir);
    }

    /**
     * Imprime informacoes das contas cadastradas
     */
    public void infoContas() {
        for (Conta conta : this.getContas()) {
            conta.imprimir();
        }
    }

    /**
     * Calcula o valor da taxa mensal com base na diferença de meses entre a data de criação
     * e a data atual, multiplicado pela taxa mensal.
     *
     * @return O valor da taxa mensal.
     */
    public long calcularTaxaMensal() {
        return TAXA_MENSAL * obterMesesDesdeCriacao();
    }

    /**
     * Calcula quantos meses se passaram desde a criação da conta
     *
     * @return meses passados desde a criação da conta
     */
    public long obterMesesDesdeCriacao() {
        LocalDate dataAtual = LocalDate.now();
        return Period.between(this.dataCriacao, dataAtual).toTotalMonths();
    }

    /**
     * Calcula quantos pontos o cliente possui
     *
     * @return pontos acumulados
     */
    public long calcularPontos() {

        int total = 0;

        for (Conta conta : contas)
            total += conta.getSaldo();

        return (long) (total / SALDO_PARA_PONTOS) * ACUMULO_PONTOS + FIDELIDADE_PONTOS * obterMesesDesdeCriacao();
    }
}

