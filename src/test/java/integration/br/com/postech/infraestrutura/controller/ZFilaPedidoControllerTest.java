package integration.br.com.postech.infraestrutura.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.TechchallengeApplication;
import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;
import br.com.postech.techchallenge.infraestrutura.controller.FilaPedidoController;
import br.com.postech.techchallenge.main.FilaPedidoConfig;

@SpringBootTest(classes = {TechchallengeApplication.class, FilaPedidoConfig.class})
class ZFilaPedidoControllerTest {
	
	@Autowired
	private FilaPedidoController filaPedidoController;

	@Test
	void testAvancarPedido() throws NumberFormatException, StatusPagamentoInvalidoException, PedidoInexistenteException {
		List<FilaPedido> filaPedido = filaPedidoController.getFilaPedido();
		String pedidoId = String.valueOf(filaPedido.get(filaPedido.size() - 1).getCodigoPedido());

		filaPedidoController.avancarPedido(pedidoId);
		assertEquals(StatusPreparacao.EM_PREPARACAO, filaPedidoController.buscarPedido(pedidoId).getStatus());
	}

}
