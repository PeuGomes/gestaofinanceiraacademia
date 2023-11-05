package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovoAdmin;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Admin;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.AdminRepository;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("requisicaoNovoAdmin", new RequisicaoNovoAdmin());
        return "admin/login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute RequisicaoNovoAdmin requisicaoNovoAdmin, RedirectAttributes redirectAttributes) {
        String nome = requisicaoNovoAdmin.getNome();
        String senha = requisicaoNovoAdmin.getSenha();

        // Validar se o nome de usuário e a senha foram fornecidos.
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Nome de usuário e senha são obrigatórios");
            return "redirect:/admin/login";
        }

        Optional<Admin> adminOptional = adminRepository.findByNomeAndSenha(nome, senha);

        if (adminOptional.isPresent()) {
            // Admin autenticado, faça o que for necessário
            redirectAttributes.addFlashAttribute("mensagem", "Login realizado com sucesso para o usuário " + nome);
            return "redirect:/admin/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Nome ou senha incorretos");
            return "redirect:/admin/login";
        }
    }


    @GetMapping("/dashboard")
    public String dashboard(org.springframework.ui.Model model) {
        String nomeAdmin = "Fabio"; // substitua com o nome do admin
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("Fabio", nomeAdmin);
        model.addAttribute("clientes", clientes);
        return "admin/dashboard";
    }
    @GetMapping("/clientes/editar")
    public String editarClientePorCpf(@RequestParam String cpf, Model model) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpfIgnoreCase(cpf);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            model.addAttribute("cliente", cliente);
            return "atualizar.html";
        } else {
            model.addAttribute("erro", "Cliente não encontrado pelo CPF");
            return "admin/dashboard";
        }
    }

}
