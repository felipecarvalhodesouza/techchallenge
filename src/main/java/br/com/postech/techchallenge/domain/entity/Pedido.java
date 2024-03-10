package br.com.postech.techchallenge.domain.entity;

import java.util.List;

import br.com.postech.techchallenge.domain.entity.enumeration.StatusPagamento;

public class Pedido {

	private Long id;
	private List<Produto> produtoList;
	private Cliente cliente;
	private double valorTotal;
	private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.valorTotal = 0;
		this.produtoList = produtoList;
		
		if (produtoList != null) {
			produtoList.stream().map(Produto::getValor).forEach(valor -> valorTotal += valor);
		}
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
}
