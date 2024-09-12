package br.com.postech.techchallenge.infraestrutura.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInvalidoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "2 - API de Pedidos", description = "API responsável pela inserção de pedidos para um cliente")
public class PedidoController {

	PedidoInteractor pedidoInteractor;

	@Autowired
	public PedidoController(PedidoInteractor service) {
		this.pedidoInteractor = service;
	}

	@Operation(summary = "Inserir um pedido")
	@ApiResponse(responseCode = "200")
	@PostMapping
	public Pedido inserir(@RequestBody Pedido pedido) throws PedidoInvalidoException, ClienteInexistenteException, MalformedURLException, IOException {
		return pedidoInteractor.inserir(pedido);
	}

	@Operation(summary = "Buscar um pedido pelo id")
	@ApiResponse(responseCode = "200")
	@GetMapping(path = "/{idPedido}")
	public Pedido getPedido(@PathVariable String idPedido) throws Exception {
		return pedidoInteractor.getPedidosPorId(idPedido);
	}
	
	@Operation(summary = "Excluir um pedido")
	@ApiResponse(responseCode = "200")
	@DeleteMapping(path = "/{idPedido}")
	public void delete(@PathVariable String idPedido) throws PedidoInexistenteException {
		pedidoInteractor.delete(idPedido);
	}
}