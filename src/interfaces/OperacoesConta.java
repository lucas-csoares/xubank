package interfaces;

import entities.conta.Conta;

public interface OperacoesConta {
    void sacar(double valor) throws Exception;
    void transferir(Double valor, Conta conta) throws Exception;

    void atualizarSaldo();
}
