package br.com.postech.techchallenge.application.gateway;

import java.util.List;

import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;

public interface PedidoGateway {

	Pedido inserir(Pedido pedido) throws PedidoInvalidoException;

	List<Pedido> getPedidosPor(String idUsuario);

	Pedido getPedidoPor(long pedidoId) throws PedidoInexistenteException;
	
	void excluir(long pedidoId) throws PedidoInexistenteException;
}
