package br.com.postech.techchallenge.adapters.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.model.Pedido;
import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.model.StatusPagamento;
import br.com.postech.techchallenge.domain.service.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.service.exception.StatusPagamentoInvalidoException;

@SpringBootTest
class PedidoControllerTest {
	
    @Autowired
    private ClienteController clienteController;
	@Autowired
	private ProdutoController produtoController;
	@Autowired
	private PedidoController pedidoController;
	

	@Test
	void inserirTest() throws PedidoInvalidoException {
		
		Cliente cliente = clienteController.getClientePor("12345678909").getBody();
		List<Produto> produtos = produtoController.getTodosOsProdutos().getBody();
		produtos.remove(produtos.size() - 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		
		assertThrows(PedidoInvalidoException.class, () -> pedidoController.inserir(pedido));

		pedido.setProdutoList(produtos);
		pedidoController.inserir(pedido);
	}
	
	@Test
	void getPedidosPorClienteTest() throws PedidoInvalidoException {
		List<Pedido> pedidos = pedidoController.getPedidosPorCliente(1l).getBody();

		assertEquals(1, pedidos.size());
		assertEquals(StatusPagamento.PENDENTE, pedidos.get(0).getStatusPagamento());
		assertEquals(139.63d,  pedidos.get(0).getValorTotal());
	}
	
	@Test
	void aprovarPagamentoTest() throws StatusPagamentoInvalidoException {
		pedidoController.aprovarPagamento("1");
		assertEquals(StatusPagamento.APROVADO,
					 pedidoController.getPedidosPorCliente(1l).getBody().get(0).getStatusPagamento());
		
		assertThrows(StatusPagamentoInvalidoException.class, () -> pedidoController.recusarPagamento("1"));
	}

}
