package br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Admin;
import io.micrometer.common.lang.NonNull;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
public class RequisicaoNovoAdmin {
    //Dto de requisicaoNovoAdmin
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getSenha() {
        return senha;
    }

    public void setSenha(@NonNull String senha) {
        this.senha = senha;
    }
    public Admin toAdmin(){
        Admin admin = new Admin();
        admin.setNome(nome);
        admin.setSenha(senha);

        return admin;
    }
}
