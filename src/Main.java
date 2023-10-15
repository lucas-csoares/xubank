import entities.VisaoDiretoria;
import entities.cliente.Cliente;
import entities.conta.Conta;
import utils.Cadastro;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); //todo: USE PONTO DECIMAL PARA VALORES DE PONTO FLUTUANTE.
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        Conta conta = null;
        VisaoDiretoria visaoDiretoria = new VisaoDiretoria();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Criar conta corrente");
            System.out.println("3. Criar conta poupança");
            System.out.println("4. Criar conta renda fixa");
            System.out.println("5. Criar conta investimento");
            System.out.println("6. Exibir dados do cliente");
            System.out.println("7. Sacar");
            System.out.println("8. Transferir");
            System.out.println("9. Depositar");
            System.out.println("10 Consultar extrato");
            System.out.println("11. Consultar contas");
            System.out.println("12. Consultar saldo");
            System.out.println("13. Calcular custódia por tipo de conta");
            System.out.println("14. Calcular saldo médio de todas as contas");
            System.out.println("15. Total de clientes com saldo negativo");
            System.out.println("16. Cliente com maior saldo");
            System.out.println("17. Cliente com menor saldo");
            System.out.println("18. Sair");
            System.out.print("Opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();
            try {
                if (opcao > 1 && opcao < 13) {
                    cliente = visaoDiretoria.encontrarClientePorCpfESenha();
                    if (opcao > 6 && opcao < 10 || opcao == 12) {
                        System.out.print("\nDigite o id da sua conta: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        conta = cliente.obterConta(id);
                    }
                }

                switch (opcao) {
                    case 1:
                        cliente = Cadastro.cliente();
                        visaoDiretoria.addCliente(cliente);
                        break;
                    case 2:
                        Conta contaCorrente = Cadastro.cadastrarContaCorrente(cliente);
                        visaoDiretoria.addConta(contaCorrente);
                        break;
                    case 3:
                        Conta contaPoupanca = Cadastro.cadastrarPoupanca(cliente);
                        visaoDiretoria.addConta(contaPoupanca);
                        break;
                    case 4:
                        Conta contaRendaFixa = Cadastro.cadastrarRendaFixa(cliente);
                        visaoDiretoria.addConta(contaRendaFixa);
                        break;
                    case 5:
                        Conta contaInvestimento = Cadastro.cadastrarInvestimento(cliente);
                        visaoDiretoria.addConta(contaInvestimento);
                        break;
                    case 6:
                        cliente.imprimir();
                        break;
                    case 7:
                        System.out.print("\nDigite o valor do saque: ");
                        double valorSaque = sc.nextDouble();
                        sc.nextLine();
                        visaoDiretoria.realizarOperacaoSaque(conta, valorSaque);
                        break;
                    case 8:
                        visaoDiretoria.realizarTransferenciaEntreContas(conta, sc);
                        break;
                    case 9:
                        System.out.print("\nDigite o valor do depósito: ");
                        double valorDeposito = sc.nextDouble();
                        sc.nextLine();
                        conta.depositar(valorDeposito);
                        break;
                    case 10:
                        cliente.consultarExtrato();
                        break;
                    case 11:
                        cliente.infoContas();
                        break;
                    case 12:
                        cliente.consultarSaldo();
                        break;
                    case 13:
                        System.out.print("\nDigite o cpf do titular da conta: ");
                        String cpfTitular = sc.nextLine();
                        System.out.print("Digite o id da conta: ");
                        int idConta = sc.nextInt();
                        sc.nextLine();
                        Conta contaDesejada = visaoDiretoria.encontrarContaPorCPFId(cpfTitular, idConta);
                        System.out.println("Calculando custódia por tipo de conta...");
                        visaoDiretoria.calcCustodiaPorTipoDeConta(contaDesejada);
                        break;
                    case 14:
                        System.out.println("\nCalculando saldo médio de todas as contas...");
                        double media = visaoDiretoria.calcSaldoMedioTodasAsContas();
                        System.out.println("\nSaldo médio de todas as contas: " + media);
                        break;
                    case 15:
                        int totalClientesSaldoNegativo = visaoDiretoria.totalClientesComSaldoNegativo();
                        System.out.println("\nTotal de clientes com saldo negativo: " + totalClientesSaldoNegativo);
                        break;
                    case 16:
                        System.out.println("\nCliente com maior saldo:");
                        visaoDiretoria.clienteMaiorSaldo();
                        break;
                    case 17:
                        System.out.println("\nCliente com menor saldo:");
                        visaoDiretoria.clienteMenorSaldo();
                        break;
                    case 18:
                        System.out.println("\nSaindo do sistema. Até logo!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("A execução foi interrompida devido a uma InterruptedException: " + e.getMessage());
            }


        }
    }
}
