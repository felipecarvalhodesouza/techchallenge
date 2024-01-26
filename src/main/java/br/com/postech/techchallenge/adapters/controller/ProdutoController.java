package br.com.postech.techchallenge.adapters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.service.ProdutoService;
import br.com.postech.techchallenge.ports.ProdutoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "2 - API de Produtos", description = "API responsável pelo gerenciamento e listagem de Produtos")
public class ProdutoController implements ProdutoInputPort {

	ProdutoService service;

	@Autowired
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@Operation(summary = "Registrar um produto")
	@ApiResponse(responseCode = "201", description = "Produto registrado com sucesso")
	@Override
	@PostMapping
	public HttpEntity<Produto> registrarProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(service.registrarProduto(produto), HttpStatus.CREATED);
	}

	@Operation(summary = "Editar um produto")
	@ApiResponse(responseCode = "200", description = "Registro alterado com sucesso")
	@Override
	@PutMapping
	public HttpEntity<Produto> editarProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(service.editarProduto(produto), HttpStatus.OK);
	}

	@Operation(summary = "Remover um produto", description = "Remover um cliente existente")
	@ApiResponse(responseCode = "204")
	@DeleteMapping(path = "/{id}")
	@Override
	public HttpEntity<Object> removerProduto(@PathVariable Long id) {
		service.removerProduto(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Retornar todos os produtos para listagem")
	@ApiResponse(responseCode = "200")
	@Override
	@GetMapping(path = "/lista")
	public HttpEntity<List<Produto>> getTodosOsProdutos() {
		return new ResponseEntity<>(service.getTodosOsProdutos(), HttpStatus.OK);
	}

	@Operation(summary = "Retornar todos os produtos para listagem de um determinado tipo")
	@ApiResponse(responseCode = "200")
	@Override
	@GetMapping(path = "/lista/{tipoProduto}")
	public HttpEntity<List<Produto>> getTodosOsProdutosPor(@PathVariable String tipoProduto) {
		return new ResponseEntity<>(service.getTodosOsProdutosPor(tipoProduto), HttpStatus.OK);
	}
}
