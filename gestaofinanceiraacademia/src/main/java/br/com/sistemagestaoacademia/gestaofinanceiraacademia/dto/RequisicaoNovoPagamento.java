package br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Pagamento;

import java.time.LocalDate;

public class RequisicaoNovoPagamento {

    //Dto de requisicaoNovoPagamento
    private double valor;
    private LocalDate vencimento;
    private LocalDate dataPagamento;
    private Long idCliente; // Adicionando o ID do Cliente

    // Construtores, getters e setters

    public RequisicaoNovoPagamento() {
        // Construtor vazio necessário para o Spring
    }

    public RequisicaoNovoPagamento(double valor, LocalDate vencimento, LocalDate dataPagamento, Long idCliente) {
        this.valor = valor;
        this.vencimento = vencimento;
        this.dataPagamento = dataPagamento;
        this.idCliente = idCliente;
    }

    // Getters e Setters

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Pagamento pagamentoCliente() {
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(dataPagamento);
        pagamento.setVencimento(vencimento);
        pagamento.setValor(valor);
        // Configura o Cliente associado ao pagamento
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        pagamento.setCliente(cliente);
        return pagamento;
    }
}
