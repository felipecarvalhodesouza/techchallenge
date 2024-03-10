package br.com.postech.techchallenge.infraestrutura.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.FilaPedidoInteractor;
import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "4 - API de Fila de Pedidos", description = "API responsável pela exibição do status dos pedidos")
public class FilaPedidoController {

	FilaPedidoInteractor filaPedidoInteractor;

	public FilaPedidoController(FilaPedidoInteractor filaPedidoInteractor) {
		this.filaPedidoInteractor = filaPedidoInteractor;
	}
	
	@GetMapping
	public List<FilaPedido> getFilaPedido() {
		return filaPedidoInteractor.getFilaPedido();
	}
	
	@Operation(summary = "Buscar um pedido da fila")
	@GetMapping(path = "/{pedidoId}")
	@ApiResponse(responseCode = "200")
	public FilaPedido buscarPedido(@PathVariable String pedidoId) throws PedidoInexistenteException {
		return filaPedidoInteractor.getFilaPedidoPor(Long.valueOf(pedidoId));
	}

	@Operation(summary = "Avancar o status de preparação de um pedido da fila")
	@PutMapping(path = "clientes/{id}/{pedidoId}/avancar")
	@ApiResponse(responseCode = "200")
	public void avancarPedido(@PathVariable String id, @PathVariable String pedidoId) throws PedidoInexistenteException {
		filaPedidoInteractor.avancarPedido(pedidoId);
	}
}