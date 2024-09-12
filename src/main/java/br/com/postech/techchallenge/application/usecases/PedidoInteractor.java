package br.com.postech.techchallenge.application.usecases;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.List;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInvalidoException;

public class PedidoInteractor {

	private final PedidoGateway pedidoGateway;
	private final ClienteGateway clienteGateway;
	
	public PedidoInteractor(PedidoGateway pedidoGateway, ClienteGateway clienteGateway) {
		this.pedidoGateway = pedidoGateway;
		this.clienteGateway = clienteGateway;
	}

	public Pedido inserir(Pedido pedido) throws PedidoInvalidoException, ClienteInexistenteException, MalformedURLException, IOException {
		if(BigDecimal.valueOf(0.0d).equals(BigDecimal.valueOf(pedido.getValorTotal()))) {
			throw new PedidoInvalidoException();
		}
		
		pedido.setCliente(clienteGateway.buscarPor(pedido.getCliente().getId()));

		return pedidoGateway.inserir(pedido);
	}
	
	public List<Pedido> getPedidosPor(String emailUsuario) throws ClienteInexistenteException {
		Long idUsuario = clienteGateway.buscarPor(emailUsuario).getId();
		return pedidoGateway.getPedidosPor(String.valueOf(idUsuario));
	}

	public void delete(String pedidoId) throws PedidoInexistenteException {
		pedidoGateway.excluir(Long.valueOf(pedidoId));
	}

	public Pedido getPedidosPorId(String idPedido) throws NumberFormatException, PedidoInexistenteException {
		return pedidoGateway.getPedidoPor(Long.valueOf(idPedido));
	}
}
