package br.com.postech.techchallenge.ports;

import br.com.postech.techchallenge.domain.model.Cliente;

public interface ClienteOutputPort {

	Cliente registrarCliente(Cliente cliente);

	Cliente getCliente(String descricao);
}
