package br.com.postech.techchallenge.domain.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cliente {

	private Long id;
	private String nome;
	private CPF cpf;
	private String email;
	private List<Pedido> pedidoList;

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

	@JsonIgnore
	public CPF getCpf() {
		return cpf;
	}

	public String getNumeroCpf() {
		return cpf.getDocumento();
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidoList() {
		return pedidoList;
	}

	public void setPedidoList(List<Pedido> pedidoList) {
		this.pedidoList = pedidoList;
	}
}
