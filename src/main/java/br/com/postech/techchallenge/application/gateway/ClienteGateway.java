package br.com.postech.techchallenge.application.gateway;

import java.io.IOException;
import java.util.List;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.exception.ClienteInexistenteException;

public interface ClienteGateway {

	Cliente buscarPorCpf(Cliente cliente);
	
	Cliente buscarPor(String email) throws ClienteInexistenteException;

	Cliente buscarPor(Long id) throws ClienteInexistenteException;

	Cliente registrar(Cliente cliente) throws IOException;

	Cliente editar(Cliente cliente);

	void remover(Cliente cliente);

	List<Cliente> buscarTodos();
}
