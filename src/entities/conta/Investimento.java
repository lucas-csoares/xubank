package entities.conta;

import entities.cliente.Cliente;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public final class Investimento extends Conta implements OperacoesConta {

    // Essas são variáveis de instância privadas e métodos getter/setter para a classe `Investimento`.
    private Double rendimentoDiario;

    private Double valorDoUltimoRendimento;

    private LocalDateTime dataDoUltimoRendimento;

    private final Double TAXA_SOBRE_RENDIMENTO_NO_SAQUE = 0.015;

    // O usuário insere a DataHoraAtual para facilitar o teste.
    public Investimento(Cliente titular, Double saldo, String date, String dataHoraAtual) {
        super(titular, saldo, date);
        rendimentoDiario = 0.0;
        this.dataDoUltimoRendimento = LocalDateTime.parse(dataHoraAtual, DataHora.fmt);
    }

    public Double getRendimentoDiario() {
        return rendimentoDiario;
    }

    public void setRendimentoDiario(Double rendimentoDiario) {
        this.rendimentoDiario = rendimentoDiario;
    }

    public Double getTAXA_SOBRE_RENDIMENTO_NO_SAQUE() {

        return TAXA_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    public LocalDateTime getDataDoUltimoRendimento() {
        return dataDoUltimoRendimento;
    }

    public void setDataDoUltimoRendimento(LocalDateTime dataHora) {
        this.dataDoUltimoRendimento = dataHora;
    }

    public Double getValorDoUltimoRendimento() {
        return valorDoUltimoRendimento;
    }

    public void setValorDoUltimoRendimento(Double valorDoUltimoRendimento) {
        this.valorDoUltimoRendimento = valorDoUltimoRendimento;
    }

    /**
     * A função "atualizarSaldo" calcula os juros diários sobre o saldo e atualiza o saldo
     * conforme necessário.
     */
    public void atualizarSaldo() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        Duration duracao = Duration.between(this.getDataDoUltimoRendimento(), dataHoraAtual);
        long diasPassados = duracao.toDays();

        while (!this.getDataDoUltimoRendimento().isAfter(dataHoraAtual)) {
            this.setRendimentoDiario(gerarRendimento());
            this.setValorDoUltimoRendimento(this.getSaldo() * this.getRendimentoDiario());
            this.saldo += this.getValorDoUltimoRendimento();
            dataDoUltimoRendimento = dataDoUltimoRendimento.plus(1, ChronoUnit.DAYS);
        }
    }

    /**
     * A função gera um número aleatório entre -1 e 1.
     * 
     * @return O método retorna um valor double aleatório entre -1 e 1.
     */
    private double gerarRendimento() {
        Random random = new Random();

        double numAleatorio = -1 + (random.nextDouble() * 2);

        return numAleatorio;
    }

    /**
     * A função calcula o imposto sobre o último rendimento.
     * 
     * @return O método retorna o valor calculado do imposto sobre o último rendimento.
     */
    private double calcularImpostoSobreRendimento() {
        return valorDoUltimoRendimento * IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    /**
     * A função calcula a taxa sobre os últimos ganhos.
     * 
     * @return O método retorna o valor da taxa calculada sobre os últimos ganhos.
     */
    private double calcularTaxaSobreRendimento() {
        return valorDoUltimoRendimento * TAXA_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    /**
     * A função "sacar" subtrai uma quantia especificada do saldo, levando em consideração impostos e
     * taxas, e adiciona um registro de transação ao histórico do cliente.
     * 
     * @param valor O parâmetro "valor" representa a quantia de dinheiro que o usuário deseja
     * sacar de sua conta.
     */
    public void sacar(double valor) throws Exception {
        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        double imposto = calcularImpostoSobreRendimento();
        double taxa = calcularTaxaSobreRendimento();
        this.saldo -= (imposto + taxa + valor);
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    /**
     * A função transfere uma quantia especificada de dinheiro de uma conta para outra, atualizando
     * os saldos e registrando a transação.
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
     * A função "imprimir" imprime o id, saldo e dataRegistro de um objeto.
     */
    public void imprimir() {
        System.out.println("#####");
        System.out.printf("Id      : %1s\n", this.id);
        System.out.printf("Saldo   : %1s\n", this.saldo);
        System.out.printf("Registro: %1s\n", this.dataRegistro.format(DataHora.fmtData));
    }
}
