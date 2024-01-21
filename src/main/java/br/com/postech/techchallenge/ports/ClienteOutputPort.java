package br.com.postech.techchallenge.ports;

import java.util.List;

import br.com.postech.techchallenge.domain.model.Cliente;

public interface ClienteOutputPort {

	Cliente getClientePor(String cpf);

	Cliente registrarCliente(Cliente cliente);
	
	Cliente editarCliente(Cliente cliente);
	
	void removerCliente(Long id);
	
	List<Cliente> getTodosOsClientes();

}
