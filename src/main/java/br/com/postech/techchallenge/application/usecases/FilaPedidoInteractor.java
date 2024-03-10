package br.com.postech.techchallenge.application.usecases;

import java.util.List;

import br.com.postech.techchallenge.application.gateway.FilaPedidoGateway;
import br.com.postech.techchallenge.domain.entity.FilaPedido;

public class FilaPedidoInteractor {

	private final FilaPedidoGateway filaPedidoGateway;

	public FilaPedidoInteractor(FilaPedidoGateway filaPedidoGateway) {
		this.filaPedidoGateway = filaPedidoGateway;
	}

	public List<FilaPedido> getFilaPedido() {
		return filaPedidoGateway.getFilaPedido();
	}
}