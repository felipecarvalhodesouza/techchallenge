package br.com.postech.techchallenge.infraestrutura.gateway.filapedido;

import java.util.List;
import java.util.stream.Collectors;

import br.com.postech.techchallenge.application.gateway.FilaPedidoGateway;
import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.infraestrutura.persistence.filapedido.FilaPedidoRepository;

public class FilaPedidoRepositoryGateway implements FilaPedidoGateway{

	private FilaPedidoRepository filaPedidoRepository;
	private FilaPedidoEntityMapper mapper;
	
	public FilaPedidoRepositoryGateway(FilaPedidoRepository filaPedidoRepository, FilaPedidoEntityMapper mapper) {
		this.filaPedidoRepository = filaPedidoRepository;
		this.mapper = mapper;
	}

	@Override
	public List<FilaPedido> getFilaPedido() {
		return filaPedidoRepository.getFilaPedido()
								   .stream()
								   .map( entity -> mapper.toDomainObject(entity))
								   .collect(Collectors.toList());
	}

	@Override
	public void enviaPara(FilaPedido filaPedido) {
		filaPedidoRepository.save(mapper.toEntity(filaPedido));
	}
}
