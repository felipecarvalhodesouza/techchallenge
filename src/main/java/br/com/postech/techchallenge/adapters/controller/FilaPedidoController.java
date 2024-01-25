package br.com.postech.techchallenge.adapters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.model.FilaPedido;
import br.com.postech.techchallenge.domain.service.FilaPedidoService;
import br.com.postech.techchallenge.ports.FilaPedidoInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "4 - API de Fila de Pedidos", description = "API responsável pela exibição do status dos pedidos")
public class FilaPedidoController implements FilaPedidoInputPort {

	FilaPedidoService service;
	
	@Autowired
	public FilaPedidoController(FilaPedidoService service) {
		this.service = service;
	}
	
	@Override
	@GetMapping
	public HttpEntity<List<FilaPedido>> getFilaPedido() {
		return new ResponseEntity<>(service.getFilaPedido(), HttpStatus.OK);
	}
}