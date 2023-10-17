package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public final class Poupanca extends Conta implements OperacoesConta {

    private final double TAXA_RENDIMENTO = 0.005;
    private LocalDate ultimoRendimento;

    /**
     * Construtor para a classe Poupanca.
     *
     * @param cliente Cliente associado à conta poupança.
     * @param saldo   Saldo inicial da conta poupança.
     * @param date    Data de criação da conta poupança.
     */
    public Poupanca(Cliente cliente, Double saldo, String date) {
        super(cliente, saldo, date);
        this.saldo = saldo;
        this.ultimoRendimento = dataRegistro;
    }

    /**
     * Imprime informações sobre a conta poupança, incluindo id, tipo, saldo e data de registro.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Tipo    : %1s\n", this.getClass().getSimpleName());
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro.format(DataHora.fmtData));
    }

    /**
     * Permite ao usuário sacar uma quantia especificada da conta poupança, atualizando o saldo e
     * registrando a transação.
     *
     * @param valor A quantia de dinheiro a ser sacada.
     * @throws Exception Se o saque não for permitido devido a saldo insuficiente.
     */
    public void sacar(double valor) throws Exception {
        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        saldo -= valor;
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    /**
     * Transfere uma quantia especificada de dinheiro de uma conta poupança para outra conta,
     * atualizando os saldos e registrando a transação.
     *
     * @param valor A quantia de dinheiro a ser transferida.
     * @param conta A conta de destino da transferência.
     * @throws Exception Se a transferência não for permitida devido a saldo insuficiente.
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
     * Aplica o rendimento mensal da conta poupança.
     */
    public void atualizarSaldo() {
        aplicarRendimento();
    }

    /**
     * Calcula e aplica o rendimento mensal da conta poupança se a data atual for posterior à próxima
     * data de rendimento.
     */
    private void aplicarRendimento() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate proximaDataRendimento = obterProximaDataRendimento(ultimoRendimento);

        if (dataAtual.isAfter(proximaDataRendimento)) {
            int mesesRendimento = calcularDiferencaMeses(proximaDataRendimento, dataAtual);
            this.depositar(saldo * mesesRendimento * TAXA_RENDIMENTO);
            ultimoRendimento = dataAtual;
        }
    }

    /**
     * Calcula a diferença em meses entre duas datas.
     *
     * @param dataInicial A data inicial.
     * @param dataFinal   A data final.
     * @return A diferença em meses.
     */
    private int calcularDiferencaMeses(LocalDate dataInicial, LocalDate dataFinal) {
        return Period.between(dataInicial, dataFinal).getMonths() + 1;
    }

    /**
     * Retorna a próxima data de rendimento com base na data fornecida.
     *
     * @param data A data fornecida.
     * @return A próxima data de rendimento.
     */
    public LocalDate obterProximaDataRendimento(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), 5).plusMonths(1);
    }
}
