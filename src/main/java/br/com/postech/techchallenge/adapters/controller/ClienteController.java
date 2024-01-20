package br.com.postech.techchallenge.adapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.service.ClienteService;
import br.com.postech.techchallenge.ports.ClienteInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements ClienteInputPort {

	@Autowired
	ClienteService service;

	@Override
	@PostMapping
	public HttpEntity<Cliente> registrarCliente(Cliente cliente) {
		return null;
	}

	@Operation(summary = "Buscar um cliente", description ="Retorna os dados de um determinado cliente")
	@ApiResponse(responseCode = "200", description = "Sucesso")
	@GetMapping
	@Override
	public HttpEntity<Cliente> getCliente(String descricao) {
		return new ResponseEntity<Cliente>(service.getCliente(descricao), HttpStatusCode.valueOf(200));
	}
}
