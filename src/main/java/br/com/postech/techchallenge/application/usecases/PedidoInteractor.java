package br.com.postech.techchallenge.application.usecases;

import java.math.BigDecimal;
import java.util.List;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.application.gateway.FilaPedidoGateway;
import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.enumeration.StatusPagamento;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;

public class PedidoInteractor {

	private final PedidoGateway pedidoGateway;
	private final FilaPedidoGateway filaPedidoGateway;
	private final ClienteGateway clienteGateway;
	
	public PedidoInteractor(PedidoGateway pedidoGateway, FilaPedidoGateway filaPedidoGateway, ClienteGateway clienteGateway) {
		this.pedidoGateway = pedidoGateway;
		this.filaPedidoGateway = filaPedidoGateway;
		this.clienteGateway = clienteGateway;
	}

	public Pedido inserir(Pedido pedido) throws PedidoInvalidoException, ClienteInexistenteException {
		if(BigDecimal.valueOf(0.0d).equals(BigDecimal.valueOf(pedido.getValorTotal()))) {
			throw new PedidoInvalidoException();
		}
		
		pedido.setCliente(clienteGateway.buscarPor(pedido.getCliente().getId()));

		return pedidoGateway.inserir(pedido);
	}
	
	public List<Pedido> getPedidosPorCliente(long clienteId) {
		return pedidoGateway.getPedidosPorCliente(clienteId);
	}

	public void aprovarPagamento(String pedidoId) throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		Pedido pedido = pedidoGateway.getPedidoPor(Long.valueOf(pedidoId));
		
		if(pedido == null) {
			return;
		}
		
		validarStatusPagamento(pedido);
		pedidoGateway.aprovarPagamento(pedido);
		
		FilaPedido filaPedido = new FilaPedido();
		filaPedido.setPedido(pedido);
		filaPedidoGateway.enviaPara(filaPedido);
	}

	public void recusarPagamento(String pedidoId) throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		Pedido pedido = pedidoGateway.getPedidoPor(Long.valueOf(pedidoId));
		
		if(pedido == null) {
			return;
		}
		validarStatusPagamento(pedido);
		pedidoGateway.recusarPagamento(pedido);
	}
	
	public StatusPagamento getStatusPagamentoPedido(String pedidoId) throws PedidoInexistenteException {
		Pedido pedido = pedidoGateway.getPedidoPor(Long.valueOf(pedidoId));
		return pedido.getStatusPagamento();
	}

	private void validarStatusPagamento(Pedido pedido) throws StatusPagamentoInvalidoException {
		if(pedido.getStatusPagamento() != StatusPagamento.PENDENTE) {
			throw new StatusPagamentoInvalidoException();
		}
	}
}
