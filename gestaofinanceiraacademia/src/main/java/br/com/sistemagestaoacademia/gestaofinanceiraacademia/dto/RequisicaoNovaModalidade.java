package br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto;


import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Modalidades;

import io.micrometer.common.lang.NonNull;


/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */

public class RequisicaoNovaModalidade {

    private Long id;
    @NonNull
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    public Modalidades toModalidade() {
        Modalidades modalidade = new Modalidades();
        modalidade.setDescricao(descricao);
        return modalidade;
    }


}
