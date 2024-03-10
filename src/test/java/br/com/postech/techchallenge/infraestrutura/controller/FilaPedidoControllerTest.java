package br.com.postech.techchallenge.infraestrutura.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;

@SpringBootTest
class FilaPedidoControllerTest {
	
	@Autowired
	private FilaPedidoController filaPedidoController;

	@Test
	void testAvancarPedido() throws NumberFormatException, StatusPagamentoInvalidoException, PedidoInexistenteException {
		filaPedidoController.avancarPedido("1");
		assertEquals(StatusPreparacao.EM_PREPARACAO, filaPedidoController.buscarPedido("1").getStatus());
	}

}
