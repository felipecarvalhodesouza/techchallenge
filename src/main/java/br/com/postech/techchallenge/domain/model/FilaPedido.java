package br.com.postech.techchallenge.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "fila_pedido")
public class FilaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@OneToOne
	@JsonIgnore
	private Pedido pedido;

	@Enumerated(EnumType.STRING)
	private StatusPreparacao status = StatusPreparacao.RECEBIDO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public long getCodigoPedido() {
		return pedido.getId();
	}

	public StatusPreparacao getStatus() {
		return status;
	}

	public void setStatus(StatusPreparacao status) {
		this.status = status;
	}
	

}
