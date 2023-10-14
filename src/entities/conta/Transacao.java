package entities.conta;

import enums.TransacaoCategoria;

import java.time.LocalDate;

public class Transacao {

    private TransacaoCategoria categoria;
    private double valor;
    private Conta contaOrigem;
    private Conta contaDestino;

    private LocalDate dataAtual;

    public Transacao(TransacaoCategoria categoria, double valor, LocalDate dataAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.dataAtual = dataAtual;
    }

    public Transacao(TransacaoCategoria categoria, double valor, Conta contaOrigem, Conta contaDestino,
                     LocalDate dataAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.dataAtual = dataAtual;
    }

    public void imprimir() {
        switch (categoria) {
            case DEPOSITO -> {
                imprimirDeposito();
            }
            case SAQUE -> {
                imprimirSaque();
            }
            case TRANSFERENCIA -> {
                imprimirTransferencia();
            }
        }
    }

    private void imprimirTransferencia() {
        System.out.println("---");
        System.out.println("Data            :" + this.dataAtual.format(Conta.fmt));
        System.out.printf("Transacao        : %1s\n", TransacaoCategoria.TRANSFERENCIA);
        System.out.printf("Valor transferido: %1s\n", valor);
        System.out.printf("Conta origem(id) : %1s\n", contaOrigem.getId());
        System.out.printf("Conta destino(id): %1s\n", contaDestino.getId());
    }

    private void imprimirSaque() {
        System.out.println("---");
        System.out.println("Data            :" + this.dataAtual.format(Conta.fmt));
        System.out.printf("Transacao       : %1s\n", TransacaoCategoria.SAQUE);
        System.out.printf("Valor sacado    : %1s", valor);
    }

    private void imprimirDeposito() {
        System.out.println("---");
        System.out.println("Data            :" + this.dataAtual.format(Conta.fmt));
        System.out.printf("Transacao       : %1s\n", TransacaoCategoria.DEPOSITO);
        System.out.printf("Valor depositado: %1s", valor);
    }
}
