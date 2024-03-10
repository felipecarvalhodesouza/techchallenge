package br.com.postech.techchallenge.application.gateway;

import java.util.List;

import br.com.postech.techchallenge.domain.entity.FilaPedido;

public interface FilaPedidoGateway {

	List<FilaPedido> getFilaPedido();

	void enviaPara(FilaPedido filaPedido);
	
	FilaPedido getFilaPedidoPor(long id);
	
	FilaPedido editar(FilaPedido filaPedido);
}
