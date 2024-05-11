package integration.br.com.postech.infraestrutura.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.TechchallengeApplication;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;
import br.com.postech.techchallenge.infraestrutura.controller.PedidoController;
import br.com.postech.techchallenge.main.PedidoConfig;

@SpringBootTest(classes = {TechchallengeApplication.class, PedidoConfig.class})
class ZZCleanUpTest {
	
	@Autowired
	private PedidoController pedidoController;

	@Test
	void removerDadosTesteIntegracao() throws NumberFormatException, StatusPagamentoInvalidoException, PedidoInexistenteException {
		List<Pedido> pedidos = pedidoController.getPedidosPorCliente(1l);
		
		for (Pedido pedido : pedidos) {
			pedidoController.delete(null, pedido);
		}
	}

}
