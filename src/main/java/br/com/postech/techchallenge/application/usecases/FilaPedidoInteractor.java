package br.com.postech.techchallenge.application.usecases;

import java.util.Collections;
import java.util.List;

import br.com.postech.techchallenge.application.gateway.FilaPedidoGateway;
import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;

public class FilaPedidoInteractor {

	private final FilaPedidoGateway filaPedidoGateway;

	public FilaPedidoInteractor(FilaPedidoGateway filaPedidoGateway) {
		this.filaPedidoGateway = filaPedidoGateway;
	}

	public List<FilaPedido> getFilaPedido() {
		List<FilaPedido> filaPedido = filaPedidoGateway.getFilaPedido();
		Collections.sort(filaPedido);
		return filaPedido;
	}
	
	public FilaPedido getFilaPedidoPor(long id) {
		return filaPedidoGateway.getFilaPedidoPor(id);
	}

	public void avancarPedido(String pedidoId) throws PedidoInexistenteException {
		FilaPedido filaPedido = filaPedidoGateway.getFilaPedidoPor(Long.valueOf(pedidoId));
		
		if(filaPedido.getStatus() == StatusPreparacao.FINALIZADO) {
			throw new IllegalArgumentException("Pedidos finalizados não podem ser avançados");
		}
		
		filaPedido.setStatus(filaPedido.getStatus().getProximoStatus());
		filaPedidoGateway.editar(filaPedido);
		
	}
}