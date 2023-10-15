package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class ContaCorrente extends Conta implements OperacoesConta {

    // Estas linhas declaram e inicializam variáveis de instância na classe `ContaCorrente`.
    private final Double TAXA_MENSAL = 20.00;
    private Double saqueEspecial = 200.00;
    private LocalDate ultimoDesconto;


    public ContaCorrente(Cliente cliente, Double saldo, String date){

    // Este é um construtor para a classe `ContaCorrente`. Ele recebe três parâmetros: `cliente`,
    // `saldo` e `date`.
    public ContaCorrente(Cliente cliente, Double saldo, String date) throws Exception {
        super(cliente, saldo, date);
        this.ultimoDesconto = dataRegistro;
    }

    /**
     * A função define o valor da variável "saqueEspecial".
     * 
     * @param saqueEspecial O parâmetro "saqueEspecial" é do tipo Double e representa o valor do
     * saque especial.
     */
    public void setSaqueEspecial(Double saqueEspecial) {
        this.saqueEspecial = saqueEspecial;
    }

    /**
     * A função "imprimir" imprime informações sobre um objeto, incluindo seu id, tipo, saldo e data de
     * registro.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Tipo    : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    /**
     * A função "sacar" permite a um usuário sacar uma quantia especificada de dinheiro de sua conta,
     * atualizando o saldo e registrando a transação.
     * 
     * @param valor O parâmetro "valor" representa a quantia de dinheiro que o usuário deseja sacar de sua conta.
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
     * A função "transferir" transfere uma quantia especificada de dinheiro de uma conta para outra,
     * atualizando os saldos e registrando a transação.
     * 
     * @param valor A quantia de dinheiro a ser transferida.
     * @param conta O parâmetro "conta" é um objeto do tipo "Conta", que representa uma conta bancária.
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
     * A função "atualizarSaldo" aplica uma taxa mensal ao saldo.
     */
    public void atualizarSaldo() {
        aplicarTaxaMensal();
    }

    /**
     * A função "aplicarTaxaMensal" calcula e aplica um desconto mensal ao saldo, atualiza a data do
     * último desconto e define um limite de saque especial.
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
