package br.com.postech.techchallenge.infraestrutura.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.main.security.CognitoUserHelper;
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

	@Operation(summary = "Buscar todos os pedidos de um cliente")
	@ApiResponse(responseCode = "200")
	@GetMapping
	public List<Pedido> getPedidos(@RequestHeader("Authorization") String token) throws Exception {
		return pedidoInteractor.getPedidosPor(new CognitoUserHelper().getUserEmail(token));
	}
	
	@Operation(summary = "Excluir um pedido")
	@ApiResponse(responseCode = "200")
	@DeleteMapping(path = "/{idPedido}")
	public void delete(@PathVariable String idPedido) throws PedidoInexistenteException {
		pedidoInteractor.delete(idPedido);
	}
}