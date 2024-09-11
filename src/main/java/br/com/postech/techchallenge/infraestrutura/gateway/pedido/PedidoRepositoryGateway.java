package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.repository.IPagamentoQueueAdapter;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;

public class PedidoRepositoryGateway implements PedidoGateway{

	private final PedidoRepository pedidoRepository;
	private PedidoEntityMapper mapper;
	private IPagamentoQueueAdapter pagamentoQueueAdapter;

	public PedidoRepositoryGateway(PedidoRepository pedidoRepository, PedidoEntityMapper mapper, IPagamentoQueueAdapter pagamentoQueueAdapter) {
		this.pedidoRepository = pedidoRepository;
		this.mapper = mapper;
		this.pagamentoQueueAdapter = pagamentoQueueAdapter;
	}
	
	@Override
	@Transactional
	public Pedido inserir(Pedido pedido) throws MalformedURLException, IOException {
		Pedido pedidoInserido = mapper.toDomainObject(pedidoRepository.save(mapper.toEntity(pedido)));
		try {
			pagamentoQueueAdapter.publicarPedidoRealizado(String.format("{\"id\": \"%s\", \"valorTotal\": \"%s\"}", String.valueOf(pedidoInserido.getId()), String.valueOf(pedido.getValorTotal())));
		} catch (IOException | TimeoutException e) {
			throw new RuntimeException("Erro ao inserir pedido.");
		}
		return pedidoInserido;
	}

	@Override
	@Transactional
	public List<Pedido> getPedidosPor(String usuarioId) {
		long clienteId = 1l;
		
		return pedidoRepository.getByClienteId(clienteId).stream()
														 .map(entity -> mapper.toDomainObject(entity))
														 .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Pedido getPedidoPor(long pedidoId) throws PedidoInexistenteException {
		PedidoEntity entity = pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoInexistenteException());
		return mapper.toDomainObject(entity);
	}

	
	@Override
	public void excluir(long pedidoId) throws PedidoInexistenteException {
		PedidoEntity entity = pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoInexistenteException());
		pedidoRepository.delete(entity);
	}
}
