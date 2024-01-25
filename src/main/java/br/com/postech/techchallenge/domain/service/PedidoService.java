package br.com.postech.techchallenge.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.domain.model.FilaPedido;
import br.com.postech.techchallenge.domain.model.Pedido;
import br.com.postech.techchallenge.domain.model.StatusPagamento;
import br.com.postech.techchallenge.domain.service.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.service.exception.StatusPagamentoInvalidoException;
import br.com.postech.techchallenge.ports.FilaPedidoOutputPort;
import br.com.postech.techchallenge.ports.PedidoOutputPort;

@Service
public class PedidoService {

	private final PedidoOutputPort port;
	private final FilaPedidoOutputPort filaPedidoPort;

	@Autowired
	public PedidoService(PedidoOutputPort port, FilaPedidoOutputPort filaPedidoPort) {
		this.port = port;
		this.filaPedidoPort = filaPedidoPort;
	}

	public void inserir(Pedido pedido) throws PedidoInvalidoException {
		if(BigDecimal.valueOf(0.0d).equals(BigDecimal.valueOf(pedido.getValorTotal()))) {
			throw new PedidoInvalidoException();
		}

		port.inserir(pedido);
	}
	
	public List<Pedido> getPedidosPorCliente(long clienteId) {
		return port.getPedidosPorCliente(clienteId);
	}

	public void aprovarPagamento(String pedidoId) throws StatusPagamentoInvalidoException {
		Pedido pedido = port.getPedidoPor(Long.valueOf(pedidoId));
		validarStatusPagamento(pedido);
		port.aprovarPagamento(pedido);
		
		FilaPedido filaPedido = new FilaPedido();
		filaPedido.setPedido(pedido);
		filaPedidoPort.enviaPara(filaPedido);
	}

	public void recusarPagamento(String pedidoId) throws StatusPagamentoInvalidoException {
		Pedido pedido = port.getPedidoPor(Long.valueOf(pedidoId));
		validarStatusPagamento(pedido);
		port.recusarPagamento(pedido);
	}

	private void validarStatusPagamento(Pedido pedido) throws StatusPagamentoInvalidoException {
		if(pedido.getStatusPagamento() != StatusPagamento.PENDENTE) {
			throw new StatusPagamentoInvalidoException();
		}
	}
}
