package entities;

public class Conta {

    private Cliente titular;
    protected Double saldo;

    public Conta(Cliente titular) {
        this.titular = titular;
        saldo = 0.0;
    }

    public Cliente getTitular() {

        return titular;
    }

    public void setTitular(Cliente titular) {

        this.titular = titular;
    }

    public void sacar(double valor) throws Exception{
        if(valor <= this.getSaldo()) {
            this.saldo -= valor;
        } else
            throw new Exception("Saldo insuficiente para realizar o saque");
    }



    //todo: getSaltdo() é um método válido para consutar o saldo
    public Double getSaldo() {

        return saldo;
    }


    // todo: setar o saldo só pode ser feito por depositar() ou sacar(), por isso acesso private
    private void setSaldo(Double saldo) {

        this.saldo = saldo;
    }



    //todo: finalizar, obs.:  últimos 30 dias
//    public double consultarExtrato(){
//
//    }

    //todo: finalizar
    public double depositar(double valor){

        return saldo += valor;
    }


    //todo: finalizar
    public void transferir(Double valor, Conta conta) throws Exception {
        if(valor > this.getSaldo()) {
            this.saldo -= valor;
            conta.depositar(valor);
        } else
            throw new Exception("Saldo insuficiente para realizar a transferência");
    }



}
