package br.com.postech.techchallenge.adapters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.model.Pedido;
import br.com.postech.techchallenge.domain.service.PedidoService;
import br.com.postech.techchallenge.domain.service.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.domain.service.exception.StatusPagamentoInvalidoException;
import br.com.postech.techchallenge.ports.PedidoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes/{id}/pedidos")
@Tag(name = "3 - API de Pedidos", description = "API responsável pela inserção de pedidos para um cliente")
public class PedidoController implements PedidoInputPort {

	PedidoService service;

	@Autowired
	public PedidoController(PedidoService service) {
		this.service = service;
	}

	@Operation(summary = "Inserir um pedido")
	@ApiResponse(responseCode = "200")
	@PostMapping
	@Override
	public HttpEntity<Object> inserir(Pedido pedido) throws PedidoInvalidoException {
		service.inserir(pedido);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Buscar todos os pedidos de um cliente")
	@ApiResponse(responseCode = "200")
	@GetMapping
	@Override
	public HttpEntity<List<Pedido>> getPedidosPorCliente(@PathVariable long id) {
		return new ResponseEntity<>(service.getPedidosPorCliente(id), HttpStatus.OK);
	}

	@Operation(summary = "Aprovar pagamento de um pedido")
	@PutMapping(path = "/{pedidoId}/aprovar")
	@Override
	public HttpEntity<Object> aprovarPagamento(String pedidoId) throws StatusPagamentoInvalidoException {
		service.aprovarPagamento(pedidoId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Recusar pagamento de um pedido")
	@PutMapping(path = "/{pedidoId}/recusar")
	@Override
	public HttpEntity<Object> recusarPagamento(String pedidoId) throws StatusPagamentoInvalidoException {
		service.recusarPagamento(pedidoId);
		return ResponseEntity.noContent().build();
	}
}