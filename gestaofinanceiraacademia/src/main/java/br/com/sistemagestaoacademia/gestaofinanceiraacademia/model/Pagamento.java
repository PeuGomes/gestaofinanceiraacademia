package br.com.sistemagestaoacademia.gestaofinanceiraacademia.model;

import jakarta.persistence.*;

import java.time.LocalDate;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
@Entity
public class Pagamento {

    //Entidade Pagamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    private LocalDate vencimento;
    private LocalDate dataPagamento;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

// Construtores, getters e setters



    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
