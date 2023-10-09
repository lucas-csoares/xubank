import entities.cliente.ClienteVip;
import entities.conta.ContaCorrente;
import entities.conta.Poupanca;

public class Main {
    public static void main(String[] args) throws Exception {


        ClienteVip clienteVip = new ClienteVip("Guilherme", "111.222.333-44", "123456");
        ContaCorrente contaCorrente = new ContaCorrente(clienteVip, 100.0);

        ClienteVip clienteVip2 = new ClienteVip("Zentx", "111.222.333-44", "123456");
        Poupanca contaCorrente2 = new Poupanca(clienteVip2, 200.0);

        contaCorrente2.transferir(200.0, contaCorrente);
        clienteVip2.imprimir();
        clienteVip.imprimir();

//        contaCorrente2.transferir(100.0, contaCorrente);
//        contaCorrente2.depositar(200);
//        clienteVip2.consultarExtrato();
    }
}
