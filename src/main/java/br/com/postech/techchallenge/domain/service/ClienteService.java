package br.com.postech.techchallenge.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.service.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.service.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.service.exception.CpfInvalidoException;
import br.com.postech.techchallenge.domain.util.ValidadorCPF;
import br.com.postech.techchallenge.ports.ClienteOutputPort;

@Service
public class ClienteService {

	private final ClienteOutputPort port;

	@Autowired
	public ClienteService(ClienteOutputPort port) {
		this.port = port;
	}

	public Cliente getCliente(String cpf) {
		return port.getClientePor(cpf);
	}

	public Cliente registrarCliente(Cliente cliente) throws CpfInvalidoException, CpfDuplicadoException {
		if (!ValidadorCPF.validarCPF(String.valueOf(cliente.getCpf()))) {
			throw new CpfInvalidoException();
		}

		if (port.getClientePor(String.valueOf(cliente.getCpf())) != null) {
			throw new CpfDuplicadoException();
		}

		return port.registrarCliente(cliente);
	}

	public Cliente editarCliente(Cliente cliente) throws ClienteInexistenteException {
		Cliente clientePersistido = port.getClientePor(cliente.getId());
		if(clientePersistido == null) {
			throw new ClienteInexistenteException();
		}

		return port.editarCliente(cliente);
	}

	public void removerCliente(Long id) {
		port.removerCliente(id);
	}
}
