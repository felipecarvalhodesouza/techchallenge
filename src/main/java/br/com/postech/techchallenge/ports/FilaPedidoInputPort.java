package br.com.postech.techchallenge.ports;

import java.util.List;

import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.FilaPedido;

public interface FilaPedidoInputPort {

	HttpEntity<List<FilaPedido>> getFilaPedido();
}
