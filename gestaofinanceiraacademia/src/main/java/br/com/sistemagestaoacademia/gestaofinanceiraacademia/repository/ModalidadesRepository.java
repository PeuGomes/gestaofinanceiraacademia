package br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository;


import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Modalidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */

@Repository
public interface ModalidadesRepository extends JpaRepository<Modalidades, Long> {

    //Repository Modalidades

}