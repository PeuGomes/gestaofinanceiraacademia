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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */

@Controller
@RequestMapping("clientes")
public class Cadastrar {

	private final ClienteRepository clienteRepository;
	private final PlanoRepository planoRepository;

	@Autowired
	public Cadastrar(ClienteRepository clienteRepository, PlanoRepository planoRepository) {
		this.clienteRepository = clienteRepository;
		this.planoRepository = planoRepository;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@GetMapping("formulario")
	public String formulario(Model model) {
		model.addAttribute("requisicaoCliente", new RequisicaoNovoCliente());
		// Adicione a lista de planos ao modelo
		List<Planos> planos = planoRepository.findAll();
		model.addAttribute("planos", planos);
		return "clientes/formulario";
	}

	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoCliente requisicaoCliente, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "clientes/formulario";
		}

		// Remover caracteres não numéricos do CPF
		String cpfSemFormato = requisicaoCliente.getCpf().replaceAll("\\D", "");
		Optional<Cliente> clienteExistente = clienteRepository.findByCpfIgnoreCase(cpfSemFormato);
		if (clienteExistente.isPresent()) {
			redirectAttributes.addFlashAttribute("error", "Já existe um cliente cadastrado com este CPF");
			System.out.println("Redirecionando para /clientes/formulario");
			return "redirect:/clientes/formulario";
		}



		// Remover caracteres não numéricos do telefone
		String telefoneSemFormato = requisicaoCliente.getTelefone().replaceAll("\\D", "");

		requisicaoCliente.setCpf(cpfSemFormato);
		requisicaoCliente.setTelefone(telefoneSemFormato);

		// Use o ID do plano fornecido no objeto RequisicaoNovoCliente
		Planos plano = planoRepository.findById(requisicaoCliente.getIdPlano()).orElse(null);

		Cliente cliente = requisicaoCliente.cadastroCliente();
		cliente.setPlano(plano);

		clienteRepository.save(cliente);

		redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");

		return "redirect:/clientes/formulario";
	}
}



