package br.com.postech.techchallenge.domain.entity;

import java.util.List;

public class Pedido {

	private Long id;
	private List<Produto> produtoList;
	private Cliente cliente;
	private double valorTotal;

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
}
