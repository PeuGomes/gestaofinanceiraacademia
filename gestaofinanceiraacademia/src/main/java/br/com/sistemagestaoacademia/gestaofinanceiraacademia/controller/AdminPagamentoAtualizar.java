package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovoPagamento;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Pagamento;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.PagamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminPagamentoAtualizar {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/admin/pagamentos/{pagamentoId}/atualizar")
    public String exibirPagamentoParaAtualizacao(@PathVariable Long pagamentoId, Model model) {
        Optional<Pagamento> optionalPagamento = Optional.of(pagamentoRepository.getOne(pagamentoId));


        if (optionalPagamento.isPresent()) {
            Pagamento pagamento = optionalPagamento.get();
            RequisicaoNovoPagamento requisicaoAtualizacao = new RequisicaoNovoPagamento();
            requisicaoAtualizacao.setIdCliente(pagamento.getCliente().getId());
            requisicaoAtualizacao.setValor(pagamento.getValor());
            requisicaoAtualizacao.setDataPagamento(pagamento.getDataPagamento());
            requisicaoAtualizacao.setVencimento(pagamento.getVencimento());

            model.addAttribute("requisicaoAtualizacao", requisicaoAtualizacao);
            model.addAttribute("clientes", clienteRepository.findAll());
            return "admin/atualizarPagamento";
        } else {
            // Trate a situação em que o pagamento não é encontrado
            return "redirect:/admin/pagamentos";
        }
    }

    @PostMapping("/admin/pagamentos/atualizar")
    public String atualizarPagamento(@ModelAttribute("requisicaoAtualizacao") @Valid RequisicaoNovoPagamento requisicao,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Lógica para lidar com erros de validação, se necessário
            return "admin/atualizarPagamento";
        }

        List<Pagamento> pagamentos = pagamentoRepository.findByClienteId(requisicao.getIdCliente());

        if (!pagamentos.isEmpty()) {
            Pagamento pagamentoExistente = pagamentos.get(0);

            // Atualiza os campos do pagamento existente com os novos valores
            pagamentoExistente.setValor(requisicao.getValor());
            pagamentoExistente.setDataPagamento(requisicao.getDataPagamento());
            pagamentoExistente.setVencimento(requisicao.getVencimento());

            pagamentoRepository.save(pagamentoExistente);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Pagamento atualizado com sucesso.");
            return "redirect:/admin/pagamentos";
        } else {
            // Trate a situação em que o pagamento não é encontrado
            return "redirect:/admin/pagamentos";
        }
    }





}
