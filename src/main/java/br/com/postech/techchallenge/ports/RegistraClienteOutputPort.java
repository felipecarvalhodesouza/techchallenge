package br.com.postech.techchallenge.ports;

import br.com.postech.techchallenge.domain.model.Cliente;

public interface RegistraClienteOutputPort {

	void clienteRegistradoComSucesso();

	void clienteRegistradoComFalha(String errorMessage) throws Exception;
	
	Cliente getCliente(String descricao);
}
