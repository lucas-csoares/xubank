package entities;

import entities.cliente.Cliente;
import entities.conta.Conta;
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

    public void addCliente(Cliente cliente) {

        this.getClientes().add(cliente);
    }

    public void removeCliente(Cliente cliente) {

        this.getClientes().remove(cliente);
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

    public double calcSaldoMedioTodasAsContas() {
        double saldoTotal = 0.0;
        int totalContas = 0;
        double media = 0.0;
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                saldoTotal += conta.getSaldo();
                totalContas++;
            }
        }

        if (totalContas > 0) {
            media = saldoTotal / totalContas;
        }

        return media;
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
        double saldoTotal = 0.0;
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                saldoTotal += conta.getSaldo();
                if (saldoTotal > maiorSaldo) {
                    maiorSaldo = saldoTotal;
                    clienteComMaiorSaldo = cliente;
                }
            }
        }

        clienteComMaiorSaldo.imprimir();
    }

    public void clienteMenorSaldo() {
        Cliente clienteComMenorSaldo = null;
        double menorSaldo = Double.MAX_VALUE;
        double saldoTotal = 0.0;
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                saldoTotal += conta.getSaldo();
                if (saldoTotal < menorSaldo) {
                    menorSaldo = saldoTotal;
                    clienteComMenorSaldo = cliente;
                }
            }
        }

        clienteComMenorSaldo.imprimir();
    }
}
