package entities.cliente;

public class ClienteVip extends Cliente {

    // Propriedades estáticas compartilhadas por todas as instâncias da classe ClienteVip
    static {
        TAXA_MENSAL = 30;
        FIDELIDADE_PONTOS = 35;
        ACUMULO_PONTOS = 30;
        SALDO_PARA_PONTOS = 2000;
    }

    /**
     * Construtor para criar uma instância de ClienteVip com nome, CPF e senha.
     *
     * @param nome  Nome do cliente.
     * @param cpf   CPF do cliente.
     * @param senha Senha do cliente.
     */
    public ClienteVip(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    /**
     * Construtor vazio que cria uma instância de ClienteVip com valores padrão.
     */
    public ClienteVip() {
        super();
    }
}
