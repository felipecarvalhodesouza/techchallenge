package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.infraestrutura.helper.SQSHelper;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;

public class PedidoRepositoryGateway implements PedidoGateway{

	private final PedidoRepository pedidoRepository;
	private PedidoEntityMapper mapper;
	private final SQSHelper sqsHelper;

	public PedidoRepositoryGateway(PedidoRepository pedidoRepository, PedidoEntityMapper mapper, SQSHelper sqsHelper) {
		this.pedidoRepository = pedidoRepository;
		this.mapper = mapper;
		this.sqsHelper = sqsHelper;
	}
	
	@Override
	public Pedido inserir(Pedido pedido) {
		Pedido pedidoInserido = mapper.toDomainObject(pedidoRepository.save(mapper.toEntity(pedido)));
		
		// Enviar mensagem para serviço de pagamento
		sqsHelper.enviarMensagem(pedidoInserido);
		
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
