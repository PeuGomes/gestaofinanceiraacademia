package br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository;



import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpfIgnoreCase(String cpf);

}
