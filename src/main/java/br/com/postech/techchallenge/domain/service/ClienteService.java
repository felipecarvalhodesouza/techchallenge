package br.com.postech.techchallenge.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.ports.ClienteOutputPort;

@Service
public class ClienteService {

	private final ClienteOutputPort port;
	
    @Autowired
    public ClienteService(ClienteOutputPort port) {
        this.port = port;
    }

    public Cliente registrarCliente(Cliente cliente) {
        return port.registrarCliente(cliente);
    }

	public Cliente getCliente(String descricao) {
		return port.getCliente(descricao);
	}
}
