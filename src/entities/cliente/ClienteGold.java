package entities.cliente;

public class ClienteGold extends Cliente {

    // Propriedades estáticas compartilhadas por todas as instâncias da classe ClienteGold
    static {
        TAXA_MENSAL = 10;
        FIDELIDADE_PONTOS = 10;
        ACUMULO_PONTOS = 10;
        SALDO_PARA_PONTOS = 1000;
    }

    /**
     * Construtor para criar uma instância de ClienteGold com nome, CPF e senha.
     *
     * @param nome  Nome do cliente.
     * @param cpf   CPF do cliente.
     * @param senha Senha do cliente.
     */
    public ClienteGold(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    /**
     * Construtor vazio que cria uma instância de ClienteGold com valores padrão.
     */
    public ClienteGold() {
        super();
    }
}
