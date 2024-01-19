package br.com.postech.techchallenge.adapters.persistence;

import org.springframework.stereotype.Repository;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.ports.RegistraClienteOutputPort;

@Repository
public class ClienteRepository implements RegistraClienteOutputPort {

	@Override
	public void clienteRegistradoComSucesso() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clienteRegistradoComFalha(String errorMessage) {
		throw new RuntimeException(errorMessage);
	}

	@Override
	public Cliente getCliente(String descricao) {
		Cliente cliente = new Cliente();
		cliente.setNome(descricao);
		cliente.setCpf(123456789090l);
		cliente.setEmail("email@provedor.com");
		return cliente;
	}

}
