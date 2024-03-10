package br.com.postech.techchallenge.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;

class FilaPedidoTest {

	@Test
	public void testCompareTo() {
		int result = getFilaPedido(StatusPreparacao.RECEBIDO, 100).compareTo(getFilaPedido(StatusPreparacao.RECEBIDO, 200));
		assertTrue(result > 0);
		
		result = getFilaPedido(StatusPreparacao.FINALIZADO, 100).compareTo(getFilaPedido(StatusPreparacao.RECEBIDO, 100));
		assertTrue(result < 0);
		
		result = getFilaPedido(StatusPreparacao.EM_PREPARACAO, 100).compareTo(getFilaPedido(StatusPreparacao.PRONTO, 100));
		assertTrue(result < 0);

		result = getFilaPedido(StatusPreparacao.EM_PREPARACAO, 100).compareTo(getFilaPedido(StatusPreparacao.RECEBIDO, 100));
		assertTrue(result > 0);

		result = getFilaPedido(StatusPreparacao.PRONTO, 300).compareTo(getFilaPedido(StatusPreparacao.RECEBIDO, 400));
		assertTrue(result > 0); 

		result = getFilaPedido(StatusPreparacao.EM_PREPARACAO, 500).compareTo(getFilaPedido(StatusPreparacao.RECEBIDO, 600));
		assertTrue(result > 0);
		
		result = getFilaPedido(StatusPreparacao.EM_PREPARACAO, 500).compareTo(getFilaPedido(StatusPreparacao.PRONTO, 600));
		assertTrue(result < 0);

		result = getFilaPedido(StatusPreparacao.PRONTO, 700).compareTo(getFilaPedido(StatusPreparacao.FINALIZADO, 800));
		assertTrue(result > 0);
	}
	
	private FilaPedido getFilaPedido(StatusPreparacao status, long codigoPedido) {
		FilaPedido fila = new FilaPedido();
		fila.setPedido(getPedido(codigoPedido));
		fila.setStatus(status);
		return fila;
	}
	
	private Pedido getPedido(long codigoPedido) {
		Pedido pedido = new Pedido();
		pedido.setId(codigoPedido);
		return pedido;
	}

}
