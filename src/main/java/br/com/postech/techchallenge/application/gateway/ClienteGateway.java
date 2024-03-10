package br.com.postech.techchallenge.application.gateway;

import java.util.List;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;

public interface ClienteGateway {

	Cliente buscarPorCpf(Cliente cliente);

	Cliente buscarPor(Long id) throws ClienteInexistenteException;

	Cliente registrar(Cliente cliente);

	Cliente editar(Cliente cliente);

	void remover(Cliente cliente);

	List<Cliente> buscarTodos();
}
