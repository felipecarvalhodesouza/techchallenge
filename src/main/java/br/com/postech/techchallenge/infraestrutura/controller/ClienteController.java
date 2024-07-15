package br.com.postech.techchallenge.infraestrutura.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.ClienteInteractor;
import br.com.postech.techchallenge.domain.entity.CPF;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "1 - API de Clientes", description = "API responsável por todos os fluxos relacionados a Cliente")
public class ClienteController {

	ClienteInteractor clienteInteractor;
	
	public ClienteController(ClienteInteractor clienteInteractor) {
		this.clienteInteractor = clienteInteractor;
	}

	@Operation(summary = "Buscar um cliente", description ="Retorna os dados de um determinado cliente")
	@ApiResponse(responseCode = "200")
	@GetMapping
	public Cliente getClientePor(String cpf) throws CpfInvalidoException {
		Cliente cliente = new Cliente();
		cliente.setCpf(new CPF(cpf));
		return clienteInteractor.buscar(cliente);
	}

	@Operation(summary = "Registrar um cliente", description ="Deve enviar os dados necessários para guardar um cliente no sistema")
	@ApiResponse(responseCode = "201", description = "Cliente registrado com sucesso")
	@PostMapping
	public Cliente registrar(@RequestBody Cliente cliente) throws CpfDuplicadoException, IOException {
		return clienteInteractor.registrar(cliente);
	}

	@Operation(summary = "Editar um cliente", description ="Deve enviar os dados necessários para alterar o registro de um cliente")
	@ApiResponse(responseCode = "200", description = "Registro alterado com sucesso")
	@PutMapping
	public Cliente editar(@RequestBody Cliente cliente) throws ClienteInexistenteException {
		return clienteInteractor.editar(cliente);
	}

	@Operation(summary = "Remover um cliente", description ="Remover um cliente")
	@ApiResponse(responseCode = "204")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> removerCliente(@PathVariable Long id) throws ClienteInexistenteException {
		Cliente cliente = clienteInteractor.buscarPor(id);
		clienteInteractor.remover(cliente);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Retornar todos os clientes (ADM)", description ="Retorna todos os clientes cadastrados")
	@ApiResponse(responseCode = "200")
	@GetMapping(path = "/lista")
	public List<Cliente> getTodosOsClientes() {
		return clienteInteractor.buscarTodos();
	}
}
