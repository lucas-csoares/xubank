package entities;

import entities.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class VisaoDiretoria {
    private List<Cliente> clientes = new ArrayList<>();

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

    public void addCliente(Cliente cliente){

        this.getClientes().add(cliente);
    }

    public void removeCliente(Cliente cliente) {

        this.getClientes().remove(cliente);
    }

    // todo: saldo total de todas as contas abertas pelos clientes

    /*public void calcCustodiaPorTipoDeConta(Conta conta) {

    }*/

    /*public double calcSaldoMedioTodasAsContas() {

    }*/


    /*public int totalClientesComSaldoNegativo() {

    }*/

    /*public Cliente clienteMaiorSaldo() {

    }*/

/*    public Cliente clienteMenorSaldo() {

    }*/







}
