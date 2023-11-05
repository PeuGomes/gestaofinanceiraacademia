package br.com.sistemagestaoacademia.gestaofinanceiraacademia.model;

import jakarta.persistence.*;

/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
@Entity
public class Planos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private String tipo;  // Adicione esta linha

    @ManyToOne
    @JoinColumn(name = "modalidade_id")  // Nome da coluna de chave estrangeira
    private Modalidades modalidade;

    public Planos() {
        // Construtor vazio necessário para o JPA
    }

    public Planos(Double valor, String tipo, Modalidades modalidade) {
        this.valor = valor;
        this.tipo = tipo;
        this.modalidade = modalidade;
    }

    // Getters e Setters
    // ...

    public Modalidades getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidades modalidade) {
        this.modalidade = modalidade;
    }
    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {  // Adicione este método
        return tipo;
    }

    public void setTipo(String tipo) {  // Adicione este método
        this.tipo = tipo;
    }
}
