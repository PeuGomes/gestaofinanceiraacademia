package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovoCliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Planos;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.PlanoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
public class AdminAtualizar {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlanoRepository planoRepository;

    // Adicione este método para inicializar o binder
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    @GetMapping("/atualizar")
    public String atualizarForm(@RequestParam(name = "cpf", required = false) String cpf, Model model) {
        RequisicaoNovoCliente requisicao = new RequisicaoNovoCliente();

        if (cpf != null) {
            Optional<Cliente> clienteOptional = clienteRepository.findByCpfIgnoreCase(cpf);
            if (clienteOptional.isPresent()) {
                requisicao = new RequisicaoNovoCliente(clienteOptional.get());
            } else {
                model.addAttribute("error", "Cliente não encontrado pelo CPF");
            }
        }

        model.addAttribute("planos", planoRepository.findAll());
        model.addAttribute("requisicao", requisicao);

        return "admin/atualizar";
    }

    @PostMapping("/atualizar")
    public String atualizarSubmit(@Valid RequisicaoNovoCliente requisicao, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/atualizar";
        }

        Optional<Cliente> clienteOptional = clienteRepository.findByCpfIgnoreCase(requisicao.getCpf());

        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            // Remova caracteres não numéricos do CPF e do telefone antes de salvar
            String cpfSemFormat = requisicao.getCpf().replaceAll("\\D", "");
            String telefoneSemFormato = requisicao.getTelefone().replaceAll("\\D", "");

            // Atualize os campos do cliente
            clienteExistente.setCpf(cpfSemFormat);
            clienteExistente.setNome(requisicao.getNome());
            clienteExistente.setEmail(requisicao.getEmail());
            clienteExistente.setTelefone(telefoneSemFormato);
            clienteExistente.setDataNascimento(requisicao.getDataNascimento());
            clienteExistente.setCep(requisicao.getCep());
            clienteExistente.setLogradouro(requisicao.getLogradouro());
            clienteExistente.setBairro(requisicao.getBairro());
            clienteExistente.setCidade(requisicao.getCidade());
            clienteExistente.setEstado(requisicao.getEstado());
            clienteExistente.setAtivo(requisicao.isAtivo());

            // Use o ID do plano fornecido no objeto RequisicaoNovoCliente
            Planos plano = planoRepository.findById(requisicao.getIdPlano()).orElse(null);
            clienteExistente.setPlano(plano);

            clienteRepository.save(clienteExistente);

            redirectAttributes.addFlashAttribute("mensagem", "Cliente atualizado com sucesso!");

            return "redirect:/admin/atualizar";
        } else {
            // Se o CPF não existir, adicione uma mensagem de erro ao modelo
            model.addAttribute("error", "Cliente não encontrado pelo CPF");

            // Adicione a lista de planos ao modelo
            List<Planos> planos = planoRepository.findAll();
            model.addAttribute("planos", planos);

            // Adicione a requisição ao modelo
            model.addAttribute("requisicao", requisicao);

            // Redirecione de volta para a página de atualização
            return "admin/atualizar";
        }
    }

    @GetMapping("/buscar-cliente-por-cpf")
    @ResponseBody
    public Cliente buscarClientePorCpf(@RequestParam("cpf") String cpf) throws IllegalStateException {
        return clienteRepository.findByCpfIgnoreCase(cpf).orElse(null);
    }
}

