package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    protected static int TAXA_MENSAL = 0;
    protected static int FIDELIDADE_PONTOS = 0;
    protected static int ACUMULO_PONTOS = 0;
    protected static int SALDO_PARA_PONTOS = 0;

    private String nome;
    private String cpf;
    private String senha;
    private List<Conta> contas; //Java permite armazenar subtipos (subclasses) na lista

    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        contas = new ArrayList<>();
    }

    public Cliente() {

        this.nome = this.cpf = this.senha = "";
    }

    //todo: finalizar
    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getSenha() {

        return senha;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void addConta(Conta conta) {
        this.getContas().add(conta);
    }

    public void removeConta(Conta conta) {
        this.getContas().remove(conta);
    }


    //    public double consultarSaldo(){
//

//    }
    //todo: finalizar, obs.:  últimos 30 dias
//    public double consultarExtrato(){
//

//    }
    //todo: finalizar
//    public double depositar(){
//

//    }
    //todo: finalizar
//    public double sacar(){
//

//    }
    //todo: finalizar
//    public double transferir(){
//

//    }



}

