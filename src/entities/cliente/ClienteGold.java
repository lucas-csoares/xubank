package entities.cliente;

public class ClienteGold extends Cliente{

    public ClienteGold(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    public ClienteGold() {
        super();
    }

    static {
        TAXA_MENSAL = 10;
        FIDELIDADE_PONTOS = 10;
        ACUMULO_PONTOS = 10;
        SALDO_PARA_PONTOS = 1000;
    }

}
