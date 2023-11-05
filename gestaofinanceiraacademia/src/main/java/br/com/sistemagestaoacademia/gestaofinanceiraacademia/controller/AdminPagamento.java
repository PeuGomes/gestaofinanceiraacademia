package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;



import br.com.sistemagestaoacademia.gestaofinanceiraacademia.Service.SeuServicoDePdf;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto.RequisicaoNovoPagamento;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Pagamento;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.ClienteRepository;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.repository.PagamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPagamento {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository; // Certifique-se de ter um repositório para clientes
    @Autowired
    private SeuServicoDePdf seuServicoDePdf;


    // Mapeamento para exibir a página de cadastro de pagamentos
    @GetMapping("/pagamentos")
    public String listarPagamento(Model model) {
        model.addAttribute("requisicao", new RequisicaoNovoPagamento());

        // Adiciona a lista de clientes ao modelo
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            List<Pagamento> pagamentosCliente = pagamentoRepository.findByCliente(cliente);

            if (pagamentosCliente != null) {
                cliente.setPagamentos(pagamentosCliente);
            }
        }
        model.addAttribute("clientes", clientes);

        // Adicione a lista de planos ao modelo
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        model.addAttribute("pagamentos", pagamentos);

        return "admin/pagamentos";
    }

    @GetMapping("/pagamentos/{pagamentoId}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long pagamentoId) {
        // Lógica para obter os dados necessários para o PDF
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElse(null);

        if (pagamento != null) {
            // Crie um serviço ou método para gerar o PDF com base no pagamento
            byte[] pdfContent = seuServicoDePdf.gerarPdf(pagamento);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "comprovante.pdf");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mapeamento para processar o formulário de cadastro de pagamentos

    @Transactional
    @PostMapping("/pagamentos")
    public String cadastrarPagamento(@Valid RequisicaoNovoPagamento requisicao, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            // Adiciona a lista de clientes ao modelo
            List<Cliente> clientes = clienteRepository.findAll();
            for (Cliente cliente : clientes) {
                cliente.setPagamentos(pagamentoRepository.findByCliente(cliente));

            }
            model.addAttribute("clientes", clientes);

            // Adiciona a lista de pagamentos ao modelo
            List<Pagamento> pagamentos = pagamentoRepository.findAll();
            model.addAttribute("pagamentos", pagamentos);

            // Adiciona mensagem de erro
            model.addAttribute("mensagemErro", "Erro no formulário. Verifique os campos.");

            return "admin/pagamentos";
        }

        // Lógica para verificar se o cliente já fez um pagamento
        Cliente cliente = clienteRepository.findById(requisicao.getIdCliente()).orElse(null);
        List<Pagamento> pagamentosDoCliente = pagamentoRepository.findByCliente(cliente);

        if (!pagamentosDoCliente.isEmpty()) {
            // Cliente já fez um pagamento, adicione uma mensagem de erro
            redirectAttributes.addFlashAttribute("mensagemErro", "Pagamento já efetuado por este cliente.");
        } else {
            // Lógica para processar e salvar o pagamento
            Pagamento pagamento = requisicao.pagamentoCliente();
            pagamentoRepository.save(pagamento);

            // Adiciona o pagamento à lista de pagamentos do cliente
            assert cliente != null;
            cliente.getPagamentos().add(pagamento);
            clienteRepository.save(cliente);

            redirectAttributes.addFlashAttribute("mensagemSucesso", "Pagamento efetuado com sucesso.");
        }

        return "redirect:/admin/pagamentos";
    }


}