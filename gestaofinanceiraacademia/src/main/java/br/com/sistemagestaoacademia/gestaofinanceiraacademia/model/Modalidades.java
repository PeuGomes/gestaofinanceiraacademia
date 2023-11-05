package br.com.sistemagestaoacademia.gestaofinanceiraacademia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
@Entity
public class Modalidades {
    //Entidade Modalidades
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    // Construtores, getters e setters

    public Modalidades() {
        // Construtor vazio necessário para o JPA
    }

    public Modalidades(String descricao) {
        this.descricao = descricao;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
