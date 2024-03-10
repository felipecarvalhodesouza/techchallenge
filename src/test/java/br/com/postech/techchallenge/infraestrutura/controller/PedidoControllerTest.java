package br.com.postech.techchallenge.infraestrutura.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.Produto;
import br.com.postech.techchallenge.domain.entity.enumeration.StatusPagamento;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoControllerTest {
	
    @Autowired
    private ClienteController clienteController;
	@Autowired
	private ProdutoController produtoController;
	@Autowired
	private PedidoController pedidoController;

	@Test @Order(1)
	void inserirTest() throws PedidoInvalidoException, CpfInvalidoException, ClienteInexistenteException {
		
		Cliente cliente = clienteController.getClientePor("12345678909");
		List<Produto> produtos = produtoController.getTodosOsProdutos();
		produtos.remove(produtos.size() - 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		
		assertThrows(PedidoInvalidoException.class, () -> pedidoController.inserir(null, pedido));

		pedido.setProdutoList(produtos);
		pedidoController.inserir(null, pedido);
		
		pedido.setId(null);
		pedidoController.inserir(null, pedido);
	}

	@Test  @Order(2)
	void getPedidosPorClienteTest() throws PedidoInvalidoException {
		List<Pedido> pedidos = pedidoController.getPedidosPorCliente(1l);

		assertEquals(2, pedidos.size());
		assertEquals(StatusPagamento.PENDENTE, pedidos.get(0).getStatusPagamento());
		assertEquals(139.63d,  pedidos.get(0).getValorTotal());
	}
	
	@Test  @Order(3)
	void aprovarPagamentoTest() throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		pedidoController.aprovarPagamento(null, "1");
		assertEquals(StatusPagamento.APROVADO,
					 pedidoController.getPedidosPorCliente(1l).get(0).getStatusPagamento());
		
		assertThrows(StatusPagamentoInvalidoException.class, () -> pedidoController.recusarPagamento(null, "1"));
	}
	
	@Test  @Order(4)
	void recusarPagamentoTest() throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		pedidoController.recusarPagamento(null, "2");
		assertEquals(StatusPagamento.RECUSADO,
					 pedidoController.getPedidosPorCliente(1l).get(1).getStatusPagamento());
	}
	
	@Test  @Order(5)
	void getStatusPagamentoTest() throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		String statusPagamentoPedido = pedidoController.getStatusPagamentoPedido(null, "2");
		assertEquals(StatusPagamento.RECUSADO.getDescricao(), statusPagamentoPedido);
	}
}
