package br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    //Repository Admin
    Optional<Admin> findByNomeAndSenha(String nome, String senha);


}
