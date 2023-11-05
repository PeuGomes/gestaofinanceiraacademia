package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovaModalidade;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Modalidades;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ModalidadesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@Controller
@RequestMapping("/admin")
public class AdminModalidades {

    private final ModalidadesRepository modalidadesRepository;

    @Autowired
    public AdminModalidades(ModalidadesRepository modalidadesRepository) {
        this.modalidadesRepository = modalidadesRepository;
    }

    @GetMapping("/adicionar")
    public String formularioAdicaoModalidade(Model model) {
        model.addAttribute("requisicaoNovaModalidade", new RequisicaoNovaModalidade());
        return "admin/formulario";  // Corrigido o nome da página Thymeleaf
    }

    @PostMapping("/adicionar")
    public String adicionarModalidade(@Valid RequisicaoNovaModalidade requisicao, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/formulario";
        }

        Modalidades novaModalidade = requisicao.toModalidade();
        modalidadesRepository.save(novaModalidade);

        redirectAttributes.addFlashAttribute("mensagem", "Modalidade adicionada com sucesso!");

        return "redirect:/admin/dashboard";
    }
}
