package interfaces;

import entities.conta.Conta;

public interface OperacoesConta {

    double IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE = 0.15;

    void sacar(double valor) throws Exception;
    void transferir(Double valor, Conta conta) throws Exception;

    void atualizarSaldo();
}
