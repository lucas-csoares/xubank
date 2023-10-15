package utils;

import entities.cliente.Cliente;
import entities.cliente.ClienteGold;
import entities.cliente.ClienteVip;
import entities.conta.*;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * A classe Cadastro fornece métodos para realizar o cadastro de clientes e contas.
 */
public class Cadastro {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Realiza o cadastro de um cliente comum, Vip ou Gold.
     *
     * @return Uma instância da classe Cliente.
     * @throws Exception Se a opção de tipo de cliente fornecida for inválida.
     */
    public static Cliente cliente() throws Exception {

        System.out.println("Digite o nome do cliente:");
        String nome = sc.nextLine();
        System.out.println("Digite o CPF do cliente:");
        String cpf = sc.nextLine();
        System.out.println("Digite a senha do cliente:");
        String senha = sc.next();
        System.out.println("Escolha o tipo de cliente:");
        System.out.println("1. Comum");
        System.out.println("2. VIP");
        System.out.println("3. Gold");

        int tipoCliente = sc.nextInt();

        Cliente cliente;
        if (tipoCliente == 1) {
            cliente = new Cliente(nome, cpf, senha); //UPCASTING
        } else if (tipoCliente == 2) {
            cliente = new ClienteVip(nome, cpf, senha); //UPCASTING
        } else if (tipoCliente == 3) {
            cliente = new ClienteGold(nome, cpf, senha); //UPCASTING
        } else {
            throw new Exception("Dado inválido!");
        }
        System.out.println("Cliente cadastrado com sucesso!");

        return cliente;
    }

    /**
     * Realiza o cadastro de uma conta corrente para um cliente.
     *
     * @param cliente O cliente para o qual a conta será cadastrada.
     * @return Uma instância da classe ContaCorrente.
     * @throws Exception Se ocorrer um problema durante o cadastro.
     */
    public static Conta cadastrarContaCorrente(Cliente cliente) throws Exception {
        System.out.println("Digite o saldo inicial da Conta Corrente: ");
        double saldoInicial = sc.nextDouble();

        ContaCorrente conta = new ContaCorrente(cliente, saldoInicial, "14/10/2023");
        cliente.addConta(conta);

        System.out.println("Conta Corrente criada com sucesso!");

        return conta;
    }

    /**
     * Realiza o cadastro de uma conta poupança para um cliente.
     *
     * @param cliente O cliente para o qual a conta será cadastrada.
     * @return Uma instância da classe Poupanca.
     */
    public static Conta cadastrarPoupanca(Cliente cliente) {
        System.out.println("Digite o saldo inicial da Poupança: ");
        double saldoInicial = sc.nextDouble();

        Poupanca conta = new Poupanca(cliente, saldoInicial, LocalDateTime.now().toString());
        cliente.addConta(conta);

        System.out.println("Conta Poupança criada com sucesso!");

        return conta;
    }

    /**
     * Realiza o cadastro de uma conta de renda fixa para um cliente.
     *
     * @param cliente O cliente para o qual a conta será cadastrada.
     * @return Uma instância da classe RendaFixa.
     */
    public static Conta cadastrarRendaFixa(Cliente cliente) {
        System.out.println("Digite o saldo inicial da Renda Fixa: ");
        double saldoInicial = sc.nextDouble();

        System.out.println("Digite a taxa de rendimento (por exemplo, 0.05 para 5%): ");
        double rendimento = sc.nextDouble();

        RendaFixa conta = new RendaFixa(cliente, saldoInicial, rendimento, "14/10/2023");
        cliente.addConta(conta);

        System.out.println("Conta Renda Fixa criada com sucesso!");

        return conta;
    }

    /**
     * Realiza o cadastro de uma conta de investimento para um cliente.
     *
     * @param cliente O cliente para o qual a conta será cadastrada.
     * @return Uma instância da classe Investimento.
     */
    public static Conta cadastrarInvestimento(Cliente cliente) {
        System.out.println("Digite o saldo inicial do Investimento: ");
        double saldoInicial = sc.nextDouble();

        System.out.println("Digite a data e hora (no formato DD/MM/yyyy HH:mm:ss): ");
        String dataHoraAtual = sc.next();

        Investimento conta = new Investimento(cliente, saldoInicial, "14/10/2023", dataHoraAtual);
        cliente.addConta(conta);

        System.out.println("Conta Investimento criada com sucesso!");

        return conta;
    }
}
