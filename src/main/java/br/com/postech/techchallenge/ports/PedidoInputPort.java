package br.com.postech.techchallenge.ports;

import java.util.List;

import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.Pedido;
import br.com.postech.techchallenge.domain.service.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.service.exception.StatusPagamentoInvalidoException;

public interface PedidoInputPort {

	HttpEntity<Object> inserir(Pedido pedido) throws PedidoInvalidoException;

	HttpEntity<List<Pedido>> getPedidosPorCliente(long clienteId);

	HttpEntity<Object> aprovarPagamento(String pedidoId) throws StatusPagamentoInvalidoException;

	HttpEntity<Object> recusarPagamento(String pedidoId) throws StatusPagamentoInvalidoException;
}
