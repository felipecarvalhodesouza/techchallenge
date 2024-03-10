package br.com.postech.techchallenge.infraestrutura.gateway.cliente;

import java.util.List;
import java.util.stream.Collectors;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteRepository;

public class ClienteRepositoryGateway implements ClienteGateway{

	private final ClienteRepository clienteRepository;
	private final ClienteEntityMapper mapper;
	
	public ClienteRepositoryGateway(ClienteRepository clienteRepository, ClienteEntityMapper mapper) {
		this.clienteRepository = clienteRepository;
		this.mapper = mapper;
	}

	@Override
	public Cliente buscarPorCpf(Cliente cliente) {
		ClienteEntity entity = clienteRepository.getByCpf(cliente.getCpf());
		if(entity != null) {
			return mapper.toDomainObject(entity);
		}
		return null;
	}

	@Override
	public Cliente registrar(Cliente cliente) {
		ClienteEntity entity = clienteRepository.save(mapper.toEntity(cliente));
		return mapper.toDomainObject(entity);
	}

	@Override
	public Cliente editar(Cliente cliente) {
		ClienteEntity entity = clienteRepository.save(mapper.toEntity(cliente));
		return mapper.toDomainObject(entity);
	}

	@Override
	public void remover(Cliente cliente) {
		clienteRepository.delete(mapper.toEntity(cliente));
	}

	@Override
	public List<Cliente> buscarTodos() {
		List<ClienteEntity> clientes = clienteRepository.findAll();
		return clientes.stream().map( entity -> mapper.toDomainObject(entity)).collect(Collectors.toList());
	}

	@Override
	public Cliente buscarPor(Long id) throws ClienteInexistenteException {
		ClienteEntity entity = clienteRepository.findById(id).orElseThrow(() -> new ClienteInexistenteException());
		return mapper.toDomainObject(entity);
	}
}
