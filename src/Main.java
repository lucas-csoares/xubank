import entities.cliente.ClienteVip;
import entities.conta.ContaCorrente;

public class Main {
    public static void main(String[] args) throws Exception {


        ClienteVip clienteVip = new ClienteVip("Guilherme", "111.222.333-44", "123456");
        ContaCorrente contaCorrente = new ContaCorrente(clienteVip, 100.0);

        ClienteVip clienteVip2 = new ClienteVip("Guilherme", "111.222.333-44", "123456");
        ContaCorrente contaCorrente2 = new ContaCorrente(clienteVip2, 200.0);

        contaCorrente2.transferir(100.0, contaCorrente);
        contaCorrente2.depositar(20);

        clienteVip2.consultarExtrato();

    }
}
