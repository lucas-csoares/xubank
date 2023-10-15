package entities.conta;

import enums.TransacaoCategoria;
import utils.DataHora;
import java.time.LocalDateTime;

public class Transacao {

    private TransacaoCategoria categoria;
    private double valor;
    private Conta contaOrigem;
    private Conta contaDestino;

    private LocalDateTime dataHoraAtual;

    // O trecho de código está definindo um construtor para a classe `Transacao`.
    public Transacao(TransacaoCategoria categoria, double valor, LocalDateTime dataHoraAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.dataHoraAtual = dataHoraAtual;
    }

    // O trecho de código está definindo um construtor para a classe `Transacao`. Este construtor recebe
    // vários parâmetros: `categoria` (do tipo `TransacaoCategoria`), `valor` (do tipo `double`),
    // `contaOrigem` (do tipo `Conta`), `contaDestino` (do tipo `Conta`) e `dataHoraAtual` (do tipo
    // `LocalDateTime`).
    public Transacao(TransacaoCategoria categoria, double valor, Conta contaOrigem, Conta contaDestino,
                     LocalDateTime dataHoraAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.dataHoraAtual = dataHoraAtual;
    }

    /**
     * A função "imprimir" usa uma declaração switch para determinar a categoria de uma transação e
     * chama o método de impressão correspondente.
     */
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

    // O trecho de código está definindo três métodos privados: `imprimirTransferencia()`,
    // `imprimirSaque()` e `imprimirDeposito()`. Esses métodos são responsáveis por imprimir os
    // detalhes de uma transação com base em sua categoria.
    private void imprimirTransferencia() {
        System.out.println("---");
        System.out.println("Data            : " + this.dataHoraAtual.format(DataHora.fmt));
        System.out.printf("Transação        : %1s\n", TransacaoCategoria.TRANSFERENCIA);
        System.out.printf("Valor transferido: %1s\n", valor);
        System.out.printf("Conta origem(id) : %1s\n", contaOrigem.getId());
        System.out.printf("Conta destino(id): %1s\n", contaDestino.getId());
    }

    private void imprimirSaque() {
        System.out.println("---");
        System.out.println("Data            : " + this.dataHoraAtual.format(DataHora.fmt));
        System.out.printf("Transação       : %1s\n", TransacaoCategoria.SAQUE);
        System.out.printf("Valor sacado    : %1s", valor);
    }

    private void imprimirDeposito() {
        System.out.println("---");
        System.out.println("Data            : " + this.dataHoraAtual.format(DataHora.fmt));
        System.out.printf("Transação       : %1s\n", TransacaoCategoria.DEPOSITO);
        System.out.printf("Valor depositado: %1s", valor);
    }
}
