package br.com.postech.techchallenge.ports;

import java.util.List;

import br.com.postech.techchallenge.domain.model.FilaPedido;

public interface FilaPedidoOutputPort {

	List<FilaPedido> getFilaPedido();
	
	void enviaPara(FilaPedido filaPedido);
}
