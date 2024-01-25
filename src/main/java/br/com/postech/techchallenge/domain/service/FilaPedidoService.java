package br.com.postech.techchallenge.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.domain.model.FilaPedido;
import br.com.postech.techchallenge.ports.FilaPedidoOutputPort;

@Service
public class FilaPedidoService {

	private final FilaPedidoOutputPort port;

	@Autowired
	public FilaPedidoService(FilaPedidoOutputPort port) {
		this.port = port;
	}

	public List<FilaPedido> getFilaPedido() {
		List<FilaPedido> filaPedido = port.getFilaPedido();
		
		return filaPedido;
	}
}