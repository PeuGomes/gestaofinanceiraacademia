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
@RequestMapping("clientes")
public class Atualizar {

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
	public String editarCliente(@RequestParam(name = "cpf", required = false) String cpf, Model model) {
		RequisicaoNovoCliente requisicaocliente = new RequisicaoNovoCliente();
		if (cpf != null) {
			Optional<Cliente> clienteOptional = clienteRepository.findByCpfIgnoreCase(cpf);
			if (clienteOptional.isPresent()) {
				requisicaocliente = new RequisicaoNovoCliente(clienteOptional.get());
			} else {
				model.addAttribute("error", "Cliente não encontrado pelo CPF");
			}
		}
		model.addAttribute("planos", planoRepository.findAll());
		model.addAttribute("requisicaocliente", requisicaocliente);

		return "clientes/atualizar";
	}

	@PostMapping("/atualizar")
	public String processarAtualizacao(@Valid RequisicaoNovoCliente requisicaocliente, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "clientes/atualizar";
		}

		Optional<Cliente> clienteOptional = clienteRepository.findByCpfIgnoreCase(requisicaocliente.getCpf());

		if (clienteOptional.isPresent()) {
			Cliente clienteExistente = clienteOptional.get();
			// Remova caracteres não numéricos do CPF e do telefone antes de salvar
			String cpfSemFormato = requisicaocliente.getCpf().replaceAll("\\D", "");
			String telefoneSemFormato = requisicaocliente.getTelefone().replaceAll("\\D", "");

			// Atualize os campos do cliente
			clienteExistente.setCpf(cpfSemFormato);
			clienteExistente.setNome(requisicaocliente.getNome());
			clienteExistente.setEmail(requisicaocliente.getEmail());
			clienteExistente.setTelefone(telefoneSemFormato);
			clienteExistente.setDataNascimento(requisicaocliente.getDataNascimento());
			clienteExistente.setCep(requisicaocliente.getCep());
			clienteExistente.setLogradouro(requisicaocliente.getLogradouro());
			clienteExistente.setBairro(requisicaocliente.getBairro());
			clienteExistente.setCidade(requisicaocliente.getCidade());
			clienteExistente.setEstado(requisicaocliente.getEstado());

			// Use o ID do plano fornecido no objeto RequisicaoNovoCliente
			Planos plano = planoRepository.findById(requisicaocliente.getIdPlano()).orElse(null);
			clienteExistente.setPlano(plano);

			clienteRepository.save(clienteExistente);

			redirectAttributes.addFlashAttribute("mensagem", "Cliente atualizado com sucesso!");

			return "redirect:/clientes/atualizar";
		} else {
			// Se o CPF não existir, adicione uma mensagem de erro ao modelo
			model.addAttribute("error", "Cliente não encontrado pelo CPF");

			// Adicione a lista de planos ao modelo
			List<Planos> planos = planoRepository.findAll();
			model.addAttribute("planos", planos);

			// Adicione a requisição ao modelo
			model.addAttribute("requisicaocliente", requisicaocliente);
			return "clientes/atualizar";
		}
	}
	@GetMapping("/buscar-cliente-por-cpf")
	@ResponseBody
	public Cliente buscarClientePorCpf(@RequestParam("cpf") String cpf) {
		return clienteRepository.findByCpfIgnoreCase(cpf).orElse(null);
	}
}

