package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.enumeration.StatusPagamento;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;

public class PedidoRepositoryGateway implements PedidoGateway{

	private final PedidoRepository pedidoRepository;
	private PedidoEntityMapper mapper;

	public PedidoRepositoryGateway(PedidoRepository pedidoRepository, PedidoEntityMapper mapper) {
		this.pedidoRepository = pedidoRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Pedido inserir(Pedido pedido) {
		PedidoEntity entity = pedidoRepository.save(mapper.toEntity(pedido));
		return mapper.toDomainObject(entity);
	}

	@Override
	@Transactional
	public List<Pedido> getPedidosPorCliente(long clienteId) {
		return pedidoRepository.getByClienteId(clienteId).stream()
														 .map(entity -> mapper.toDomainObject(entity))
														 .collect(Collectors.toList());
	}

	@Override
	public void aprovarPagamento(Pedido pedido) {
		pedido.setStatusPagamento(StatusPagamento.APROVADO);
		pedidoRepository.save(mapper.toEntity(pedido));
	}

	@Override
	public void recusarPagamento(Pedido pedido) {
		pedido.setStatusPagamento(StatusPagamento.RECUSADO);
		pedidoRepository.save(mapper.toEntity(pedido));
	}

	@Override
	@Transactional
	public Pedido getPedidoPor(long pedidoId) throws PedidoInexistenteException {
		PedidoEntity entity = pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoInexistenteException());
		return mapper.toDomainObject(entity);
	}
}
