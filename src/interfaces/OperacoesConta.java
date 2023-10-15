package interfaces;

import entities.conta.Conta;

public interface OperacoesConta {

    // Imposto cobrado no momento de saque da conta.
    double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;

    /**
     * Saca um valor de uma determinada conta.
     *
     * @param valor valor a ser sacado.
     * @throws Exception Se o valor fornecido for inválido.
     */
    void sacar(double valor) throws Exception;

    /**
     * Transfere um valor de uma conta para outra.
     *
     * @param valor valor a ser transferido.
     * @param conta conta que recebera o depósito (valor transferido).
     * @throws Exception Caso o valor ou conta sejam inválidos.
     */
    void transferir(Double valor, Conta conta) throws Exception;

    /**
     * Atualiza o saldo de uma conta.
     */
    void atualizarSaldo();
}
