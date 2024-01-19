package br.com.postech.techchallenge.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.ports.RegistraClienteOutputPort;

@Service
public class ClienteService {

	@Autowired
	private RegistraClienteOutputPort port;

	public Cliente getCliente(String descricao) {
		return port.getCliente(descricao);
	}
}
