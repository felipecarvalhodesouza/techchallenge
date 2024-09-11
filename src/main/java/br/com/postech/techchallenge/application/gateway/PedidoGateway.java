package br.com.postech.techchallenge.application.gateway;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInvalidoException;

public interface PedidoGateway {

	Pedido inserir(Pedido pedido) throws PedidoInvalidoException, MalformedURLException, IOException;

	List<Pedido> getPedidosPor(String idUsuario);

	Pedido getPedidoPor(long pedidoId) throws PedidoInexistenteException;
	
	void excluir(long pedidoId) throws PedidoInexistenteException;
}
