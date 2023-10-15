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

    public VisaoDiretoria() {

    }

    public VisaoDiretoria(List<Cliente> clientes) {

        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {

        return clientes;
    }

    private void setClientes(List<Cliente> clientes) {

        this.clientes = clientes;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void addCliente(Cliente cliente) {

        this.getClientes().add(cliente);
    }


    public void removeCliente(Cliente cliente) {

        this.getClientes().remove(cliente);
    }

    public void addConta(Conta conta) {
        this.getContas().add(conta);
    }

    public void removeConta(Conta conta) {
        this.getContas().remove(conta);
    }

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

    private double saldoTotalDoCliente(Cliente cliente) {
        double saldoTotal = 0.0;
        for (Conta conta : cliente.getContas()) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;
    }

    public double calcSaldoMedioTodasAsContas() {
        double saldoTotal = 0.0;
        int totalContas = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += saldoTotalDoCliente(cliente);
            totalContas += cliente.getContas().size();
        }

        return saldoTotal / totalContas;
    }

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


    public Cliente encontrarClientePorCpfESenha() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();
        System.out.println("Digite a senha do cliente: ");
        String senha = sc.nextLine();

        for (Cliente cliente : this.getClientes()) {
            if (cliente.getCpf().equals(cpf) && cliente.getSenha().equals(senha)) {
                return cliente;
            }
        }

        throw new Exception("Cliente não encontrado.");
    }


    public void realizarOperacaoSaque(Conta conta, double valor) throws Exception {

        if(!(conta instanceof OperacoesConta))
            throw new Exception("Operação não suportada para este tipo de conta.");

        ((OperacoesConta) conta).sacar(valor);
    }


    public void realizarOperacaoTransferir(Conta contaOrigem, Conta contaDestino, double valor) throws Exception{

        if(!(contaOrigem instanceof OperacoesConta))
            throw new Exception("Operação não suportada para este tipo de conta.");


        ((OperacoesConta) contaOrigem).transferir(valor, contaDestino);
    }


    public Conta encontrarContaPorCPFId(String cpfTitular, int idConta) throws Exception {
        for (Conta conta : this.getContas()) {
            if (conta.getTitular().getCpf().equals(cpfTitular) && conta.getId() == idConta) {
                return conta;
            }
        }
        throw new Exception("Conta não encontrada!");
    }


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