package br.com.postech.techchallenge.ports;

import java.util.List;

import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.service.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.service.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.service.exception.CpfInvalidoException;

public interface ClienteInputPort {

	HttpEntity<Cliente> getClientePor(String cpf);

	HttpEntity<Cliente> registrarCliente(Cliente cliente) throws CpfInvalidoException, CpfDuplicadoException;
	
	HttpEntity<Cliente> editarCliente(Cliente cliente) throws ClienteInexistenteException;
	
	HttpEntity<Object> removerCliente(Long id);
	
	HttpEntity<List<Cliente>> getTodosOsClientes();
}
