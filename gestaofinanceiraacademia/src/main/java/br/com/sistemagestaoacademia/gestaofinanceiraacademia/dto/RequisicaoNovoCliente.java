package br.com.sistemagestaoacademia.gestaofinanceiraacademia.dto;


import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Cliente;
import br.com.sistemagestaoacademia.gestaofinanceiraacademia.model.Planos;
import io.micrometer.common.lang.NonNull;

import java.time.LocalDate;
/**
 * @author Pedro Ivo Barreto Gomes
 * Created on ${DATE}.
 */
public class RequisicaoNovoCliente {
	//Dto de requisicaoNovoCliente
	private Long id;
	@NonNull
	private String nome;
	@NonNull
	private String cpf;
	@NonNull
	private LocalDate dataNascimento;

	private String email;

	private String telefone;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;

	private Long idPlano;  // Adicione esta linha

	public RequisicaoNovoCliente() {
		// Construtor vazio
	}
	public RequisicaoNovoCliente(Cliente cliente) {
		// Construtor sem argumentos
	}
	// Getter e Setter para 'idPlano'
	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	private boolean ativo; // Adiciona o atributo ativo

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cliente cadastroCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDataNascimento(dataNascimento);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setCep(cep);
		cliente.setLogradouro(logradouro);
		cliente.setBairro(bairro);
		cliente.setCidade(cidade);
		cliente.setEstado(estado);

		// Adicione a associação do Plano ao Cliente
		Planos plano = new Planos();
		cliente.setPlano(plano);

		return cliente;
	}

	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		// Lógica para copiar os dados da requisição para o cliente
		return cliente;
	}


}
