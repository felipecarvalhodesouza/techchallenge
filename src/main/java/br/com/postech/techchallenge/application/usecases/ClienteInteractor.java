package br.com.postech.techchallenge.application.usecases;

import java.util.List;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import br.com.postech.techchallenge.domain.util.ValidadorCPF;

public class ClienteInteractor {

	private final ClienteGateway clienteGateway;

	public ClienteInteractor(ClienteGateway clienteGateway) {
		this.clienteGateway = clienteGateway;
	}

	public Cliente buscar(Cliente cliente){
		return clienteGateway.buscarPorCpf(cliente);
	}
	
	public Cliente buscarPor(Long id) throws ClienteInexistenteException {
		return clienteGateway.buscarPor(id);
	}
	
	public Cliente registrar(Cliente cliente) throws CpfInvalidoException, CpfDuplicadoException {
		if (!ValidadorCPF.validarCPF(String.valueOf(cliente.getCpf()))) {
			throw new CpfInvalidoException();
		}

		if (clienteGateway.buscarPorCpf(cliente) != null) {
			throw new CpfDuplicadoException();
		}

		return clienteGateway.registrar(cliente);
	}
	
	public Cliente editar(Cliente cliente) throws ClienteInexistenteException {
		clienteGateway.buscarPor(cliente.getId());
		return clienteGateway.editar(cliente);
	}

	public List<Cliente> buscarTodos(){
		return clienteGateway.buscarTodos();
	}

	public void remover(Cliente cliente){
		clienteGateway.remover(cliente);
	}
	
}
