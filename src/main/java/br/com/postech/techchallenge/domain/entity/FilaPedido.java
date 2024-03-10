package br.com.postech.techchallenge.domain.entity;

import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;

public class FilaPedido {

	private Long id;
	private Pedido pedido;
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
