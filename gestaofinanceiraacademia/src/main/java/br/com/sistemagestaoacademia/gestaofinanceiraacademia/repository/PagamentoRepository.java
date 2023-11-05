package br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    //Repository Pagamento

    List<Pagamento> findByClienteId(Long clienteId);

    List<Pagamento> findByCliente(Cliente cliente);
}

