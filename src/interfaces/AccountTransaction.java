package interfaces;

import entities.Conta;

public interface AccountTransaction {
    void sacar(double valor) throws IllegalArgumentException;
    void transferir(Double valor, Conta conta) throws IllegalArgumentException;

    void updateBalanceByDate();
}
