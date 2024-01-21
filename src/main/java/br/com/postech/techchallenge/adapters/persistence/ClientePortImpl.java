package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

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
	public Cliente getClientePor(String cpf) {
		return clienteJpaRepository.getByCpf(Long.valueOf(cpf));
	}

	@Override
	public Cliente registrarCliente(Cliente cliente) {
		return clienteJpaRepository.save(cliente);
	}

	@Override
	public Cliente editarCliente(Cliente cliente) {
		return clienteJpaRepository.save(cliente);
	}

	@Override
	public void removerCliente(Long id) {
		clienteJpaRepository.deleteById(id);
	}

	@Override
	public List<Cliente> getTodosOsClientes() {
		return clienteJpaRepository.findAll();
	}

	@Override
	public Cliente getClientePor(Long id) {
		return clienteJpaRepository.findById(id).orElse(null);
	}
}
