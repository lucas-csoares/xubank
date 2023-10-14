import entities.cliente.ClienteVip;
import entities.conta.ContaCorrente;
import entities.conta.RendaFixa;

public class Main {
    public static void main(String[] args) throws Exception {

        // Conta corrente
        ClienteVip clienteVip = new ClienteVip("Guilherme", "111.222.333-44", "123456");
        ContaCorrente contaCorrente = new ContaCorrente(clienteVip, 100.0, "12/10/2024");


        // Conta renda fixa
        ClienteVip clienteVip2 = new ClienteVip("Zentx", "111.222.333-44", "123456");
        RendaFixa rendaFixa = new RendaFixa(clienteVip2, 200.0, 0.015,"12/10/2024");


        // Conta poupança


        // Conta investimento


        //Operações


        rendaFixa.sacar(50);
        clienteVip2.imprimir();

//        contaCorrente2.transferir(100.0, contaCorrente);
//        contaCorrente2.depositar(200);
//        clienteVip2.consultarExtrato();
    }
}
