package br.com.postech.techchallenge.infraestrutura.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.ProdutoInteractor;
import br.com.postech.techchallenge.domain.entity.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "2 - API de Produtos", description = "API responsável pelo gerenciamento e listagem de Produtos")
public class ProdutoController {

	ProdutoInteractor produtoInteractor;

	public ProdutoController(ProdutoInteractor produtoInteractor) {
		this.produtoInteractor = produtoInteractor;
	}

	@Operation(summary = "Registrar um produto")
	@ApiResponse(responseCode = "201", description = "Produto registrado com sucesso")
	@PostMapping
	public Produto registrar(@RequestBody Produto produto) {
		return produtoInteractor.registrar(produto);
	}

	@Operation(summary = "Editar um produto")
	@ApiResponse(responseCode = "200", description = "Registro alterado com sucesso")
	@PutMapping
	public Produto editar(@RequestBody Produto produto) {
		return produtoInteractor.editar(produto);
	}

	@Operation(summary = "Remover um produto", description = "Remover um cliente existente")
	@ApiResponse(responseCode = "204")
	@DeleteMapping(path = "/{id}")
	public void removerProduto(@PathVariable Long id) {
		produtoInteractor.remover(id);
	}

	@Operation(summary = "Retornar todos os produtos para listagem")
	@ApiResponse(responseCode = "200")
	@GetMapping(path = "/lista")
	public List<Produto> getTodosOsProdutos() {
		return produtoInteractor.getTodosOsProdutos();
	}

	@Operation(summary = "Retornar todos os produtos para listagem de um determinado tipo")
	@ApiResponse(responseCode = "200")
	@GetMapping(path = "/lista/{tipoProduto}")
	public List<Produto> getTodosOsProdutosPor(@PathVariable String tipoProduto) {
		return produtoInteractor.getTodosOsProdutosPor(tipoProduto);
	}
	
	@Operation(summary = "Retornar produto por nome")
	@ApiResponse(responseCode = "200")
	@GetMapping(path = "/")
	public Produto getProdutoPorNome(String nomeProduto) {
		return produtoInteractor.getProdutoPorNome(nomeProduto);
	}
}
