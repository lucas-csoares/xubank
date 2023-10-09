package entities.cliente;

public class ClienteVip extends Cliente{

    public ClienteVip(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    public ClienteVip() {
        super();
    }

    static {
        TAXA_MENSAL = 30;
        FIDELIDADE_PONTOS = 35;
        ACUMULO_PONTOS = 30;
        SALDO_PARA_PONTOS = 2000;
    }

}
