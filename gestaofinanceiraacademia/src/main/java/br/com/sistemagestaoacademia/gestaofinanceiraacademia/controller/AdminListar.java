package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Pagamento;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@Controller
@RequestMapping("/admin")
public class AdminListar {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;


    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        model.addAttribute("pagamentos", pagamentos);
        return "admin/listarClientes";
    }
}
