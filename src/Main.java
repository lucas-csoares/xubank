import entities.VisaoDiretoria;
import entities.cliente.Cliente;
import entities.conta.Conta;
import utils.Cadastro;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Criar conta comum");
            System.out.println("3. Criar conta corrente");
            System.out.println("4. Criar conta poupança");
            System.out.println("5. Criar conta renda fixa");
            System.out.println("6. Criar conta investimento");
            System.out.println("7. Exibir dados do cliente");
            System.out.println("8. Sacar");
            System.out.println("9. Transferir");
            System.out.println("10. Depositar");
            System.out.println("11. Consultar extrato");
            System.out.println("12. Consultar contas");
            System.out.println("13. Remover conta");
            System.out.println("14. Calcular custódia por tipo de conta");
            System.out.println("15. Calcular saldo médio de todas as contas");
            System.out.println("16. Total de clientes com saldo negativo");
            System.out.println("17. Cliente com maior saldo");
            System.out.println("18. Cliente com menor saldo");
            System.out.println("19. Sair");

            int opcao = sc.nextInt();
            sc.nextLine();
            Cliente cliente = null;
            Conta conta = null;
            VisaoDiretoria visaoDiretoria = new VisaoDiretoria();
            try {
                if(opcao >= 2 && opcao <= 12) {
                    cliente = visaoDiretoria.encontrarClientePorCpfESenha();
                    if (opcao >= 8) {
                        System.out.println("Digite o id da conta desejada");
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
                        /*Conta contaComum = Cadastro.cadastrarContaComum(cliente);
                        visaoDiretoria.addConta(contaComum);*/
                        break;
                    case 3:
                        Conta contaCorrente = Cadastro.cadastrarContaCorrente(cliente);
                        visaoDiretoria.addConta(contaCorrente);
                        break;
                    case 4:
                        Conta contaPoupanca = Cadastro.cadastrarPoupanca(cliente);
                        visaoDiretoria.addConta(contaPoupanca);
                        break;
                    case 5:
                        Conta contaRendaFixa = Cadastro.cadastrarRendaFixa(cliente);
                        visaoDiretoria.addConta(contaRendaFixa);
                        break;
                    case 6:
                        Conta contaInvestimento = Cadastro.cadastrarInvestimento(cliente);
                        visaoDiretoria.addConta(contaInvestimento);
                        break;
                    case 7:
                        cliente.imprimir();
                        break;
                    case 8:
                        System.out.print("Digite o valor do saque: ");
                        double valorSaque = sc.nextDouble();
                        sc.nextLine();
                        visaoDiretoria.realizarOperacaoSaque(conta, valorSaque);
                        break;
                    case 9:
                        System.out.print("Digite o valor da transferência: ");
                        double transferencia = sc.nextDouble();
                        sc.nextLine();
                        visaoDiretoria.realizarOperacaoTransferir(conta, transferencia);
                        break;
                    case 10:
                        // Lógica para depósito
                        break;
                    case 11:
                        // Lógica para consultar extrato
                        break;
                    case 12:
                        // Lógica para consultar contas
                        break;
                    case 13:
                        // Lógica para remover conta
                        break;
                    case 14:
                        System.out.println("Calculando custódia por tipo de conta...");
                        // Chame o método de cálculo de custódia aqui
                        break;
                    case 15:
                        System.out.println("Calculando saldo médio de todas as contas...");
                        // Chame o método de cálculo de saldo médio aqui
                        break;
                    case 16:
                        int totalClientesSaldoNegativo = visaoDiretoria.totalClientesComSaldoNegativo();
                        System.out.println("Total de clientes com saldo negativo: " + totalClientesSaldoNegativo);
                        break;
                    case 17:
                        System.out.println("Cliente com maior saldo:");
                        visaoDiretoria.clienteMaiorSaldo();
                        break;
                    case 18:
                        System.out.println("Cliente com menor saldo:");
                        visaoDiretoria.clienteMenorSaldo();
                        break;
                    case 19:
                        System.out.println("Saindo do sistema. Até logo!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }


        } //término while


    }





}
