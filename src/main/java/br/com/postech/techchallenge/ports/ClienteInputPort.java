package br.com.postech.techchallenge.ports;

import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.Cliente;

public interface ClienteInputPort {

	HttpEntity<Cliente> registrarCliente(Cliente cliente);

	HttpEntity<Cliente> getCliente(String descricao);
}
