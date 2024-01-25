package br.com.postech.techchallenge.ports;

import java.util.List;

import br.com.postech.techchallenge.domain.model.Pedido;

public interface PedidoOutputPort {

	void inserir(Pedido pedido);
	
	Pedido getPedidoPor(Long pedidoId);
	
	List<Pedido> getPedidosPorCliente(long clienteId);
	
	void aprovarPagamento(Pedido pedido);
	
	void recusarPagamento(Pedido pedido);
}
