package br.com.postech.techchallenge.adapters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.service.ClienteService;
import br.com.postech.techchallenge.domain.service.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.service.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.service.exception.CpfInvalidoException;
import br.com.postech.techchallenge.ports.ClienteInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "1 - API de Clientes", description = "API responsável por todos os fluxos relacionados a Cliente")
public class ClienteController implements ClienteInputPort {

	ClienteService service;
	
	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@Operation(summary = "Buscar um cliente", description ="Retorna os dados de um determinado cliente")
	@ApiResponse(responseCode = "200")
	@GetMapping
	@Override
	public HttpEntity<Cliente> getClientePor(String cpf) {
		return new ResponseEntity<>(service.getCliente(cpf), HttpStatus.OK);
	}

	@Operation(summary = "Registrar um cliente", description ="Deve enviar os dados necessários para guardar um cliente no sistema")
	@ApiResponse(responseCode = "201", description = "Cliente registrado com sucesso")
	@Override
	@PostMapping
	public HttpEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) throws CpfInvalidoException, CpfDuplicadoException {
		return new ResponseEntity<>(service.registrarCliente(cliente), HttpStatus.CREATED);
	}

	@Operation(summary = "Editar um cliente", description ="Deve enviar os dados necessários para alterar o registro de um cliente")
	@ApiResponse(responseCode = "200", description = "Registro alterado com sucesso")
	@Override
	@PutMapping
	public HttpEntity<Cliente> editarCliente(Cliente cliente) throws ClienteInexistenteException {
		return new ResponseEntity<>(service.editarCliente(cliente), HttpStatus.OK);
	}

	@Operation(summary = "Remover um cliente", description ="Remover um cliente existente")
	@ApiResponse(responseCode = "204")
	@DeleteMapping
	@Override
	public ResponseEntity<Object> removerCliente(Long id) {
		service.removerCliente(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Retornar todos os clientes (ADM)", description ="Retorna todos os clientes cadastrados")
	@ApiResponse(responseCode = "200")
	@Override
	@GetMapping(path = "/lista")
	public HttpEntity<List<Cliente>> getTodosOsClientes() {
		return new ResponseEntity<>(service.getTodosOsClientes(), HttpStatus.OK);
	}
}
