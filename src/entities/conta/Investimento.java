package entities.conta;

import entities.cliente.Cliente;
import entities.conta.Conta;
import enums.TransacaoCategoria;
import interfaces.OperacoesConta;
import utils.DataHora;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public final class Investimento extends Conta implements OperacoesConta {

    private Double rendimentoDiario;

    private Double valorDoUltimoRendimento;

    private LocalDateTime dataDoUltimoRendimento;

    private final Double TAXA_SOBRE_RENDIMENTO_NO_SAQUE = 0.015;


    // DataHoraAtual o usuário insere para facilitar teste
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

    public void atualizarSaldo() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        Duration duracao = Duration.between(this.getDataDoUltimoRendimento(), dataHoraAtual);
        long diasPassados = duracao.toDays();

        while(!this.getDataDoUltimoRendimento().isAfter(dataHoraAtual)) {
            this.setRendimentoDiario(gerarRendimento());
            this.setValorDoUltimoRendimento(this.getSaldo() * this.getRendimentoDiario());
            this.saldo += this.getValorDoUltimoRendimento();
            dataDoUltimoRendimento = dataDoUltimoRendimento.plus(1, ChronoUnit.DAYS);
        }

    }

    private double gerarRendimento() {
        Random random = new Random();

        double numAleatorio = -1 + (random.nextDouble() * 2);

        return numAleatorio;
    }

    private double calcularImpostoSobreRendimento() {
        return valorDoUltimoRendimento * IMPOSTO_SOBRE_RENDIMENTO_NO_SAQUE;
    }

    private double calcularTaxaSobreRendimento() {
        return valorDoUltimoRendimento * TAXA_SOBRE_RENDIMENTO_NO_SAQUE;
    }


    public void sacar(double valor) throws Exception {
        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Saque de " + valor + " não permitido. Saldo insuficiente.");

        double imposto = calcularImpostoSobreRendimento();
        double taxa = calcularTaxaSobreRendimento();
        this.saldo -= (imposto + taxa + valor);
        cliente.addTransacao(new Transacao(TransacaoCategoria.SAQUE, valor, LocalDateTime.now()));
    }

    public void transferir(Double valor, Conta conta) throws Exception {
        atualizarSaldo();

        if (valor > saldo)
            throw new Exception("Transferencia de " + valor + " não permitido. Saldo insuficiente.");

        this.saldo -= valor;
        conta.depositar(valor);

        cliente.addTransacao(new Transacao(TransacaoCategoria.TRANSFERENCIA, valor, this, conta, LocalDateTime.now()));


    }

}
