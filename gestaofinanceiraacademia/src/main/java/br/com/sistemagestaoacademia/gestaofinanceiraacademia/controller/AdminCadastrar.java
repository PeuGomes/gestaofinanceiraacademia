package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovoCliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Planos;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.PlanoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */

@Controller
@RequestMapping("/admin")
public class AdminCadastrar {

    private final ClienteRepository clienteRepository;
    private final PlanoRepository planoRepository;

    @Autowired
    public AdminCadastrar(ClienteRepository clienteRepository, PlanoRepository planoRepository) {
        this.clienteRepository = clienteRepository;
        this.planoRepository = planoRepository;
    }

    @GetMapping("/cadastrar")
    public String cadastrarForm(Model model) {
        model.addAttribute("requisicao", new RequisicaoNovoCliente());
        // Adicione a lista de planos ao modelo
        List<Planos> planos = planoRepository.findAll();
        model.addAttribute("planos", planos);
        return "admin/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarSubmit(@Valid RequisicaoNovoCliente requisicao, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/cadastrar";
        }

        // Remover caracteres não numéricos do CPF.
        String cpfSemFormato = requisicao.getCpf().replaceAll("\\D", "");
        Optional<Cliente> clienteExistente = clienteRepository.findByCpfIgnoreCase(cpfSemFormato);
        if (clienteExistente.isPresent()) {
            redirectAttributes.addFlashAttribute("error","Já existe um cliente cadastrado com este CPF");
            return "redirect:/admin/cadastrar";
        }

        // Remover caracteres não numéricos do telefone
        String telefoneSemFormato = requisicao.getTelefone().replaceAll("\\D", "");

        // Verificar se o telefone tem 11 dígitos
        if (telefoneSemFormato.length() != 11) {
            result.rejectValue("telefone", "error.requisicao", "O telefone deve conter 11 dígitos numéricos");
            return "admin/cadastrar";
        }

        requisicao.setCpf(cpfSemFormato);
        requisicao.setTelefone(telefoneSemFormato);

        // Use o ID do plano fornecido no objeto RequisicaoNovoCliente
        Planos plano = planoRepository.findById(requisicao.getIdPlano()).orElse(null);

        Cliente cliente = requisicao.cadastroCliente();
        cliente.setPlano(plano);

        clienteRepository.save(cliente);

        redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");

        return "redirect:/admin/cadastrar";
    }
}



