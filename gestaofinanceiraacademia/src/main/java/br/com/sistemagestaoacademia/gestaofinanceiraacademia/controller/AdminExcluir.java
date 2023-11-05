package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@Controller
@RequestMapping("/admin")
public class AdminExcluir {
    //Responsavel por Excluir o cliente.
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/excluir")
    public String excluirClienteForm() {
        return "admin/excluir";
    }

    @PostMapping("/excluir")
    public String excluirCliente(@RequestParam("cpf") String cpf, Model model) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfIgnoreCase(cpf);

        if (optionalCliente.isPresent()) {
            clienteRepository.delete(optionalCliente.get());
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", "Cliente não encontrado");
        }

        return "admin/excluir";
    }
}
