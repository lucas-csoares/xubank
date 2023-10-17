package entities.conta;

import enums.TransacaoCategoria;
import utils.DataHora;

import java.time.LocalDateTime;

public class Transacao {

    private final TransacaoCategoria categoria;
    private final double valor;
    private Conta contaOrigem;
    private Conta contaDestino;
    private final LocalDateTime dataHoraAtual;

    /**
     * Construtor para transações sem contas de origem e destino (depósito, saque).
     *
     * @param categoria      Categoria da transação.
     * @param valor          Valor da transação.
     * @param dataHoraAtual  Data e hora da transação.
     */
    public Transacao(TransacaoCategoria categoria, double valor, LocalDateTime dataHoraAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.dataHoraAtual = dataHoraAtual;
    }

    /**
     * Construtor para transações com contas de origem e destino (transferência).
     *
     * @param categoria      Categoria da transação.
     * @param valor          Valor da transação.
     * @param contaOrigem    Conta de origem da transação.
     * @param contaDestino   Conta de destino da transação.
     * @param dataHoraAtual  Data e hora da transação.
     */
    public Transacao(TransacaoCategoria categoria, double valor, Conta contaOrigem, Conta contaDestino,
                     LocalDateTime dataHoraAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.dataHoraAtual = dataHoraAtual;
    }

    /**
     * Imprime os detalhes da transação com base na categoria (depósito, saque ou transferência).
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

    /**
     * Imprime os detalhes de uma transação de transferência.
     */
    private void imprimirTransferencia() {
        System.out.println("---");
        System.out.println("Data            : " + this.dataHoraAtual.format(DataHora.fmt));
        System.out.printf("Transação        : %1s\n", TransacaoCategoria.TRANSFERENCIA);
        System.out.printf("Valor transferido: %1s\n", valor);
        System.out.printf("Conta origem(id) : %1s\n", contaOrigem.getId());
        System.out.printf("Conta destino(id): %1s\n", contaDestino.getId());
    }

    /**
     * Imprime os detalhes de uma transação de saque.
     */
    private void imprimirSaque() {
        System.out.println("---");
        System.out.println("Data            : " + this.dataHoraAtual.format(DataHora.fmt));
        System.out.printf("Transação       : %1s\n", TransacaoCategoria.SAQUE);
        System.out.printf("Valor sacado    : %1s", valor);
    }

    /**
     * Imprime os detalhes de uma transação de depósito.
     */
    private void imprimirDeposito() {
        System.out.println("---");
        System.out.println("Data            : " + this.dataHoraAtual.format(DataHora.fmt));
        System.out.printf("Transação       : %1s\n", TransacaoCategoria.DEPOSITO);
        System.out.printf("Valor depositado: %1s", valor);
    }
}
