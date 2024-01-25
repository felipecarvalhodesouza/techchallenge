package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.postech.techchallenge.domain.model.Pedido;
import br.com.postech.techchallenge.domain.model.StatusPagamento;
import br.com.postech.techchallenge.ports.PedidoOutputPort;

@Repository
public class PedidoPortImpl implements PedidoOutputPort {

	private final PedidoJpaRepository pedidoJpaRepository;

	@Autowired
	public PedidoPortImpl(PedidoJpaRepository pedidoJpaRepository) {
		this.pedidoJpaRepository = pedidoJpaRepository;
	}
	
	@Override
	public void inserir(Pedido pedido) {
		pedidoJpaRepository.save(pedido);
	}

	@Override
	public List<Pedido> getPedidosPorCliente(long clienteId) {
		return pedidoJpaRepository.getByClienteId(clienteId);
	}

	@Override
	public void aprovarPagamento(Pedido pedido) {
		pedido.setStatusPagamento(StatusPagamento.APROVADO);
		pedidoJpaRepository.save(pedido);
	}

	@Override
	public void recusarPagamento(Pedido pedido) {
		pedido.setStatusPagamento(StatusPagamento.RECUSADO);
		pedidoJpaRepository.save(pedido);
	}

	@Override
	public Pedido getPedidoPor(Long pedidoId) {
		return pedidoJpaRepository.findById(pedidoId).orElse(null);
	}
}
