package br.com.postech.techchallenge.infraestrutura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.entity.exception.StatusPagamentoInvalidoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes/{id}/pedidos")
@Tag(name = "3 - API de Pedidos", description = "API responsável pela inserção de pedidos para um cliente")
public class PedidoController {

	PedidoInteractor pedidoInteractor;

	@Autowired
	public PedidoController(PedidoInteractor service) {
		this.pedidoInteractor = service;
	}

	@Operation(summary = "Inserir um pedido")
	@ApiResponse(responseCode = "200")
	@PostMapping
	public Pedido inserir(@PathVariable String id, @RequestBody Pedido pedido) throws PedidoInvalidoException, ClienteInexistenteException {
		return pedidoInteractor.inserir(pedido);
	}

	@Operation(summary = "Buscar todos os pedidos de um cliente")
	@ApiResponse(responseCode = "200")
	@GetMapping
	public List<Pedido> getPedidosPorCliente(@PathVariable long id) {
		return pedidoInteractor.getPedidosPorCliente(id);
	}

	@Operation(summary = "Aprovar pagamento de um pedido")
	@PutMapping(path = "/{pedidoId}/aprovar")
	@ApiResponse(responseCode = "200")
	public void aprovarPagamento(@PathVariable String id, @PathVariable String pedidoId) throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		pedidoInteractor.aprovarPagamento(pedidoId);
	}

	@Operation(summary = "Recusar pagamento de um pedido")
	@PutMapping(path = "/{pedidoId}/recusar")
	@ApiResponse(responseCode = "200")
	public void recusarPagamento(@PathVariable String id, @PathVariable String pedidoId) throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		pedidoInteractor.recusarPagamento(pedidoId);
	}
	
	@Operation(summary = "Consultar o status do pagamento de um pedido")
	@PutMapping(path = "/{pedidoId}/statusPagamento")
	@ApiResponse(responseCode = "200")
	public String getStatusPagamentoPedido(@PathVariable String id, @PathVariable String pedidoId) throws StatusPagamentoInvalidoException, NumberFormatException, PedidoInexistenteException {
		return pedidoInteractor.getStatusPagamentoPedido(pedidoId).getDescricao();
	}
}