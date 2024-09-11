package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.infraestrutura.helper.HttpHelper;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;

public class PedidoRepositoryGateway implements PedidoGateway{

	private final PedidoRepository pedidoRepository;
	private PedidoEntityMapper mapper;
	private final HttpHelper httpHelper;

	public PedidoRepositoryGateway(PedidoRepository pedidoRepository, PedidoEntityMapper mapper, HttpHelper httpHelper) {
		this.pedidoRepository = pedidoRepository;
		this.mapper = mapper;
		this.httpHelper = httpHelper;
	}
	
	@Override
	public Pedido inserir(Pedido pedido) throws MalformedURLException, IOException {
		Pedido pedidoInserido = mapper.toDomainObject(pedidoRepository.save(mapper.toEntity(pedido)));
		
		// Enviar mensagem para serviço de pagamento
		httpHelper.sendPostRequest(String.format("{\"id\": \"%s\", \"valorTotal\": \"%s\"}", String.valueOf(pedido.getId()), String.valueOf(pedido.getValorTotal())), new URL("http://a510a9de72f064953aaf7628714fffa0-1431959075.us-east-1.elb.amazonaws.com:8082/pagamentos"));
		
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
