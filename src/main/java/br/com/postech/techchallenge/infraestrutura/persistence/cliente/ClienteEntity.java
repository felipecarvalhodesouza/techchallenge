package br.com.postech.techchallenge.infraestrutura.persistence.cliente;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_nome")
	private String nome;

	@Column(name = "ds_cpf")
	private String cpf;

	@Column(name = "ds_email")
	String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	List<PedidoEntity> pedidoList;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PedidoEntity> getPedidoList() {
		return pedidoList;
	}

	public void setPedidoList(List<PedidoEntity> pedidoList) {
		this.pedidoList = pedidoList;
	}
}
