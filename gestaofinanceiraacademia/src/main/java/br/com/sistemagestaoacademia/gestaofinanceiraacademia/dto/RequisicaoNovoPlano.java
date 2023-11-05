package br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto;


import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Modalidades;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Planos;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
public class RequisicaoNovoPlano {

    //Dto de requisicaoNovoPlano
    private Long id;
    private Double valor;

    private String tipo;

    // Adicione este campo para representar modalidade_id
    private Long modalidadeId;
    public Long getModalidadeId() {
        return modalidadeId;
    }

    public void setModalidadeId(Long modalidadeId) {
        this.modalidadeId = modalidadeId;
    }

    // Getter e Setter para 'tipo'
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public Planos toPlano() {
        Planos plano = new Planos();
        plano.setId(id);
        plano.setValor(valor);
        plano.setTipo(tipo);

        // Configura a modalidade usando modalidadeId
        if (modalidadeId != null) {
            Modalidades modalidade = new Modalidades();
            modalidade.setId(modalidadeId);
            plano.setModalidade(modalidade);
        }

        return plano;
    }
}
