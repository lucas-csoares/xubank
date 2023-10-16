/**
 * A classe VisaoDiretoria representa a visão da diretoria do sistema bancário,
 * responsável por gerenciar clientes, contas e realizar operações administrativas.
 */
package entities;

import entities.cliente.Cliente;
import entities.conta.Conta;
import interfaces.OperacoesConta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VisaoDiretoria {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();

    /**
     * Construtor padrão da classe VisaoDiretoria.
     */
    public VisaoDiretoria() {
    }

    /**
     * Construtor que permite inicializar a lista de clientes.
     *
     * @param clientes Lista de clientes a ser definida.
     */
    public VisaoDiretoria(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Obtém a lista de clientes.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Define a lista de clientes.
     *
     * @param clientes Lista de clientes a ser definida.
     */
    private void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Obtém a lista de contas.
     *
     * @return Lista de contas.
     */
    public List<Conta> getContas() {
        return contas;
    }

    /**
     * Define a lista de contas.
     *
     * @param contas Lista de contas a ser definida.
     */
    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    /**
     * Adiciona um cliente à lista de clientes.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) {
        this.getClientes().add(cliente);
    }

    /**
     * Remove um cliente da lista de clientes.
     *
     * @param cliente Cliente a ser removido.
     */
    public void removeCliente(Cliente cliente) {
        this.getClientes().remove(cliente);
    }

    /**
     * Adiciona uma conta à lista de contas.
     *
     * @param conta Conta a ser adicionada.
     */
    public void addConta(Conta conta) {
        this.getContas().add(conta);
    }

    /**
     * Remove uma conta da lista de contas.
     *
     * @param conta Conta a ser removida.
     */
    public void removeConta(Conta conta) {
        this.getContas().remove(conta);
    }

    /**
     * Calcula a custódia total para um determinado tipo de conta.
     *
     * @param tipoConta Tipo de conta para calcular a custódia.
     */
    public void calcCustodiaPorTipoDeConta(Conta tipoConta) {
        double saldoTotal = 0.0;
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                if (conta.getClass() == tipoConta.getClass()) {
                    saldoTotal += conta.getSaldo();
                }
            }
        }
        System.out.printf("Total de saldo para %s: %.2f\n", tipoConta.getClass().getSimpleName(), saldoTotal);
    }

    /**
     * Calcula o saldo total de um cliente.
     *
     * @param cliente Cliente para calcular o saldo total.
     * @return Saldo total do cliente.
     */
    private double saldoTotalDoCliente(Cliente cliente) {
        double saldoTotal = 0.0;
        for (Conta conta : cliente.getContas()) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;
    }

    /**
     * Calcula o saldo médio de todas as contas.
     *
     * @return Saldo médio de todas as contas.
     */
    public double calcSaldoMedioTodasAsContas() {
        double saldoTotal = 0.0;
        int totalContas = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += saldoTotalDoCliente(cliente);
            totalContas += cliente.getContas().size();
        }

        return saldoTotal / totalContas;
    }

    /**
     * Obtém o total de clientes com saldo negativo.
     *
     * @return Total de clientes com saldo negativo.
     */
    public int totalClientesComSaldoNegativo() {
        int count = 0;
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                if (conta.getSaldo() < 0) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * Exibe as informações do cliente com o maior saldo.
     */
    public void clienteMaiorSaldo() {
        Cliente clienteComMaiorSaldo = null;
        double maiorSaldo = Double.MIN_VALUE;
        for (Cliente cliente : clientes) {
            double saldoTotal = saldoTotalDoCliente(cliente);
            if (saldoTotal > maiorSaldo) {
                maiorSaldo = saldoTotal;
                clienteComMaiorSaldo = cliente;
            }
        }

        assert clienteComMaiorSaldo != null;
        clienteComMaiorSaldo.imprimir();
    }

    /**
     * Exibe as informações do cliente com o menor saldo.
     */
    public void clienteMenorSaldo() {
        Cliente clienteComMenorSaldo = null;
        double menorSaldo = Double.MAX_VALUE;
        for (Cliente cliente : clientes) {
            double saldoTotal = saldoTotalDoCliente(cliente);
            if (saldoTotal < menorSaldo) {
                menorSaldo = saldoTotal;
                clienteComMenorSaldo = cliente;
            }
        }

        assert clienteComMenorSaldo != null;
        clienteComMenorSaldo.imprimir();
    }

    /**
     * Encontra um cliente com base no CPF e senha fornecidos.
     *
     * @return Cliente encontrado.
     * @throws Exception Lança uma exceção se o cliente não for encontrado.
     */
    public Cliente encontrarClientePorCpfESenha() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();
        System.out.println("Digite a senha do cliente: ");
        String senha = sc.nextLine();

        for (Cliente cliente : this.getClientes()) {
            if (cliente.getCpf().equals(cpf) && cliente.getSenha().equals(senha)) {
                cliente.imprimir();
                return cliente;
            }
        }

        throw new Exception("Cliente não encontrado.");
    }

    /**
     * Realiza a operação de saque em uma conta.
     *
     * @param conta Conta na qual o saque será realizado.
     * @param valor Valor a ser sacado.
     * @throws Exception Lança uma exceção se a operação não for suportada para o tipo de conta.
     */
    public void realizarOperacaoSaque(Conta conta, double valor) throws Exception {
        if (!(conta instanceof OperacoesConta))
            throw new Exception("Operação não suportada para este tipo de conta.");

        ((OperacoesConta) conta).sacar(valor);
    }

    /**
     * Realiza a operação de transferência entre duas contas.
     *
     * @param contaOrigem  Conta de origem.
     * @param contaDestino Conta de destino.
     * @param valor        Valor a ser transferido.
     * @throws Exception Lança uma exceção se a operação não for suportada para o tipo de conta.
     */
    public void realizarOperacaoTransferir(Conta contaOrigem, Conta contaDestino, double valor) throws Exception {
        if (!(contaOrigem instanceof OperacoesConta))
            throw new Exception("Operação não suportada para este tipo de conta.");

        ((OperacoesConta) contaOrigem).transferir(valor, contaDestino);
    }

    /**
     * Encontra uma conta com base no CPF do titular e no ID da conta.
     *
     * @param cpfTitular CPF do titular da conta.
     * @param idConta    ID da conta.
     * @return Conta encontrada.
     * @throws Exception Lança uma exceção se a conta não for encontrada.
     */
    public Conta encontrarContaPorCPFId(String cpfTitular, int idConta) throws Exception {
        for (Conta conta : this.getContas()) {
            if (conta.getTitular().getCpf().equals(cpfTitular) && conta.getId() == idConta) {
                return conta;
            }
        }
        throw new Exception("Conta não encontrada!");
    }

    /**
     * Realiza a transferência entre contas.
     *
     * @param contaOrigem Conta de origem.
     * @param scanner     Scanner para entrada de dados.
     * @throws Exception Lança uma exceção se a operação não for suportada para o tipo de conta.
     */
    public void realizarTransferenciaEntreContas(Conta contaOrigem, Scanner scanner) throws Exception {
        System.out.print("Digite o valor da transferência: ");
        double valorTransferencia = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o CPF do titular da conta de destino: ");
        String cpfDestino = scanner.nextLine();

        System.out.print("Digite o ID da conta de destino: ");
        int idContaDestino = scanner.nextInt();
        scanner.nextLine();

        // Encontre a conta de destino com base no CPF e no ID.
        Conta contaDestino = encontrarContaPorCPFId(cpfDestino, idContaDestino);

        realizarOperacaoTransferir(contaOrigem, contaDestino, valorTransferencia);
        System.out.println("Transferência concluída com sucesso.");
    }
}
