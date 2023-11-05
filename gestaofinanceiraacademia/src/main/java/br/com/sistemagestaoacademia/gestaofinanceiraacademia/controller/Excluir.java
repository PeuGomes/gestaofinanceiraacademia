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
@RequestMapping("/clientes")
public class Excluir {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/excluir")
    public String excluirClienteForm() {
        return "clientes/excluir";
    }

    @PostMapping("/excluir")
    public String excluirCliente(@RequestParam("cpf") String cpf, Model model) {
        // Encontrar o cliente pelo CPF usando Optional
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfIgnoreCase(cpf);

        if (optionalCliente.isPresent()) {
            // Excluir o cliente se encontrado
            clienteRepository.delete(optionalCliente.get());
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", "Cliente não encontrado");
        }

        return "clientes/excluir";
    }
}




