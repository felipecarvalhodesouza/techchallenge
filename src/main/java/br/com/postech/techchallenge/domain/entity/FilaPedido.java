package br.com.postech.techchallenge.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;

public class FilaPedido implements Comparable<FilaPedido> {

	private Long id;
	@JsonIgnore
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

	@Override
	public int compareTo(FilaPedido o) {
		if(this.status == o.getStatus()) {
			return Long.valueOf(getCodigoPedido()).compareTo(Long.valueOf(o.getCodigoPedido())) * -1;
		}

		if(status == StatusPreparacao.FINALIZADO || o.getStatus() == StatusPreparacao.PRONTO) {
			return -1;
		}

		if(o.getStatus() == StatusPreparacao.FINALIZADO || status == StatusPreparacao.PRONTO) {
			return 1;
		}

		if(status == StatusPreparacao.EM_PREPARACAO && o.getStatus() == StatusPreparacao.RECEBIDO) {
			return 1;
		}

		return -1;
	}

}
