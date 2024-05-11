package br.com.postech.techchallenge.application.gateway;

import java.util.List;

import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;

public interface PedidoGateway {

	Pedido inserir(Pedido pedido) throws PedidoInvalidoException;

	List<Pedido> getPedidosPorCliente(long clienteId);

	void aprovarPagamento(Pedido pedido) throws StatusPagamentoInvalidoException;

	void recusarPagamento(Pedido pedido) throws StatusPagamentoInvalidoException;

	Pedido getPedidoPor(long pedidoId) throws PedidoInexistenteException;
	
	String getStatusPedido(long pedidoId) throws PedidoInexistenteException;
	
	void excluir(long pedidoId) throws PedidoInexistenteException;
}
