package br.com.sistemagestaoacademia.gestaofinanceiraacademia.Service;

import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Pagamento;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class SeuServicoDePdf {

    public byte[] gerarPdf(Pagamento pagamento) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Rectangle pageSize = new Rectangle(240, 400); // Tamanho personalizado
            Document document = new Document(pageSize);
            PdfWriter.getInstance(document, baos);

            document.open();

            // Adicionando Fonte para o Nome da Academia
            Font fontNomeAcademia = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Paragraph nomeAcademia = new Paragraph("Academia Hardcore", fontNomeAcademia);
            nomeAcademia.setAlignment(Paragraph.ALIGN_LEFT); // Centralizando
            document.add(nomeAcademia);

            Font fontAcademiaCnpj = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Paragraph cnpjAcademia = new Paragraph("CNPJ: 51.299.857/0001-95", fontAcademiaCnpj);
            cnpjAcademia.setAlignment(Paragraph.ALIGN_LEFT); // Centralizando
            document.add(cnpjAcademia);

            // Adicionando Endereço, Email e Contato
            Font fontInformacoes = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            document.add(new Paragraph("Endereço: Av. Rio Branco, 185 - Centro, Rio de Janeiro - RJ, 20040-007", fontInformacoes));
            document.add(new Paragraph("Email: contato@hardcore.com", fontInformacoes));
            document.add(new Paragraph("Contato: (11) 1234-5678", fontInformacoes));

            // Adicionando Fonte Padrão para o Comprovante de Pagamento
            Font fontComprovante = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Comprovante de Pagamento", fontComprovante));
            document.add(new Paragraph("ID do Pagamento: " + pagamento.getId(), fontComprovante));
            document.add(new Paragraph("Tipo de Plano: " + pagamento.getCliente().getPlano().getTipo(), fontComprovante));
            document.add(new Paragraph("Descrição: " + Objects.requireNonNullElse(pagamento.getCliente().getPlano().getModalidade().getDescricao(), "Cliente não encontrado"), fontComprovante));
            document.add(new Paragraph("Valor do Plano: " + pagamento.getCliente().getPlano().getValor(), fontComprovante));
            document.add(new Paragraph("Valor Pago: " + pagamento.getValor(), fontComprovante));
            document.add(new Paragraph("Nome do Cliente: " + Objects.requireNonNullElse(pagamento.getCliente().getNome(), "Cliente não encontrado"), fontComprovante));
            document.add(new Paragraph("Data de Pagamento: " + formatarData(pagamento.getDataPagamento()), fontComprovante));
            document.add(new Paragraph("Data de Vencimento: " + formatarData(pagamento.getVencimento()), fontComprovante));

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace(); // Trate a exceção de forma adequada em um ambiente de produção
            return new byte[0];
        }
    }

    private String formatarData(LocalDate data) {
        return data != null ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Data não disponível";
    }
}
