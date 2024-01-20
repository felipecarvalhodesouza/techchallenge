package br.com.postech.techchallenge.adapters.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.ports.ClienteOutputPort;

@Repository
public class ClientePortImpl implements ClienteOutputPort {

	private final ClienteJpaRepository clienteJpaRepository;

	@Autowired
	public ClientePortImpl(ClienteJpaRepository clienteJpaRepository) {
		this.clienteJpaRepository = clienteJpaRepository;
	}

	@Override
	public Cliente getCliente(String descricao) {
		return clienteJpaRepository.getByNomeOrEmail(descricao, descricao);
	}

	@Override
	public Cliente registrarCliente(Cliente cliente) {
		return clienteJpaRepository.save(cliente);
	}
}
