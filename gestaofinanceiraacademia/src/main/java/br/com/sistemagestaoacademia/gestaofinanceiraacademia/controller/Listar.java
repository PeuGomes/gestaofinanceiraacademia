package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;


import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@Controller
@RequestMapping(path = "/cadastro")
public class Listar {
	//Lista para os clientes mas não esta sendo utilizada.
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping()
	public String cadastro(Model model) {
		List<Cliente> clientes = clienteRepository.findAll();
		model.addAttribute("clientes", clientes);
		return "clienteForm";
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/cadastro";
	}
}
