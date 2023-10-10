import entities.cliente.ClienteVip;
import entities.conta.ContaCorrente;
import entities.conta.RendaFixa;

public class Main {
    public static void main(String[] args) throws Exception {


        ClienteVip clienteVip = new ClienteVip("Guilherme", "111.222.333-44", "123456");
        ContaCorrente contaCorrente = new ContaCorrente(clienteVip, 100.0);

        ClienteVip clienteVip2 = new ClienteVip("Zentx", "111.222.333-44", "123456");
        RendaFixa rendaFixa = new RendaFixa(clienteVip2, 200.0, 0.015);

        rendaFixa.sacar(50);
        clienteVip2.imprimir();

//        contaCorrente2.transferir(100.0, contaCorrente);
//        contaCorrente2.depositar(200);
//        clienteVip2.consultarExtrato();
    }
}
