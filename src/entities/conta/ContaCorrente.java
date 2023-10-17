package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class ContaCorrente extends Conta implements OperacoesConta {

    private final Double TAXA_MENSAL = 20.00;
    private Double saqueEspecial = 200.00;
    private LocalDate ultimoDesconto;


    // Este é um construtor para a classe `ContaCorrente`. Ele recebe três parâmetros: `cliente`,
    // `saldo` e `date`.
    /**
     * Construtor da classe ContaCorrente, utilizado para criar uma nova instância de conta corrente.
     *
     * @param cliente Cliente associado à conta corrente.
     * @param saldo   Saldo inicial da conta corrente.
     * @param date    Data de registro da conta corrente no formato "DD/MM/yyyy".
     * @throws Exception Lançada se houver um erro ao criar a conta corrente.
     */
    public ContaCorrente(Cliente cliente, Double saldo, String date) throws Exception {
        super(cliente, saldo, date);
        this.ultimoDesconto = dataRegistro;
    }

    /**
     * Define o valor do saque especial.
     *
     * @param saqueEspecial O parâmetro "saqueEspecial" é do tipo Double e representa o valor do saque especial.
     */
    public void setSaqueEspecial(Double saqueEspecial) {
        this.saqueEspecial = saqueEspecial;
    }

    /**
     * Imprime informações sobre a conta corrente, incluindo id, tipo, saldo e data de registro.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Tipo    : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    /**
     * Permite ao usuário sacar uma quantia especificada de dinheiro da conta corrente,
     * atualizando o saldo e registrando a transação.
     *
     * @param valor A quantia de dinheiro a ser sacada.
     * @throws Exception Lançada se houver um erro durante o saque.
     */
    public void sacar(double valor) throws Exception {

        atualizarSaldo();

        if (valor > saldo + saqueEspecial)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        if (valor > saldo) {
            saqueEspecial = saqueEspecial - saldo;
            saldo = 0.0;
        } else
            saldo -= valor;

        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    /**
     * Transfere uma quantia especificada de dinheiro da conta corrente para outra conta,
     * atualizando os saldos e registrando a transação.
     *
     * @param valor A quantia de dinheiro a ser transferida.
     * @param conta A conta de destino para a transferência.
     * @throws Exception Lançada se houver um erro durante a transferência.
     */
    public void transferir(Double valor, Conta conta) throws Exception {

        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferência de " + valor + " não permitida. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta, LocalDateTime.now()));
    }

    /**
     * Atualiza o saldo da conta corrente aplicando a taxa mensal, se necessário.
     */
    public void atualizarSaldo() {
        aplicarTaxaMensal();
    }

    /**
     * Aplica a taxa mensal ao saldo, atualizando a data do último desconto e o limite de saque especial.
     */
    private void aplicarTaxaMensal() {

        LocalDate dataAtual = LocalDate.now();
        long mesesDesdeUltimoDesconto = ChronoUnit.MONTHS.between(ultimoDesconto, dataAtual);

        if (mesesDesdeUltimoDesconto > 0) {
            double descontoTotal = TAXA_MENSAL * mesesDesdeUltimoDesconto;
            saldo -= descontoTotal;
            ultimoDesconto = dataAtual;
            setSaqueEspecial(200.00);
        }
    }
}
