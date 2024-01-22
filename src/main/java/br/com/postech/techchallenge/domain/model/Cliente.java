package br.com.postech.techchallenge.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_nome")
	private String nome;

	@Column(name = "ds_cpf", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
	private long cpf;

	@Column(name = "ds_email")
	String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	List<Pedido> pedidoList;

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

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
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
