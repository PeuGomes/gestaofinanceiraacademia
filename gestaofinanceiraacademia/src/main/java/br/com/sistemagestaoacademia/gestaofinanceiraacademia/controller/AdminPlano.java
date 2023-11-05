package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovoPlano;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Modalidades;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Planos;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@Controller
public class AdminPlano {

    @Autowired
    private PlanoRepository planoRepository;

    @GetMapping("/admin/cadastrar-planos")
    public String mostrarFormularioCadastrarPlanos(Model model) {
        // Adiciona o DTO para o formulário
        model.addAttribute("planoDto", new RequisicaoNovoPlano());

        // Adiciona os planos do banco de dados
        List<Planos> planos = planoRepository.findAll();
        model.addAttribute("planos", planos);

        return "admin/cadastrar-planos";
    }

    @PostMapping("/admin/cadastrar-planos")
    public String cadastrarPlanos(@ModelAttribute("planoDto") RequisicaoNovoPlano planoDto) {
        // Converte o DTO para a entidade Planos.
        Planos plano = planoDto.toPlano();

        // Configura a modalidade usando modalidadeId
        if (planoDto.getModalidadeId() != null) {
            Modalidades modalidade = new Modalidades();
            modalidade.setId(planoDto.getModalidadeId());
            plano.setModalidade(modalidade);
        }
        // Salva o plano usando o repositório
        planoRepository.save(plano);

        // Redireciona para a página de sucesso ou para onde desejar
        return "redirect:/admin/cadastrar-planos?sucesso";
    }
}
