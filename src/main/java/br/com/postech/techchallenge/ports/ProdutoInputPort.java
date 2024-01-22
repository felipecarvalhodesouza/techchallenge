package br.com.postech.techchallenge.ports;

import java.util.List;

import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.Produto;

public interface ProdutoInputPort {

	HttpEntity<Produto> registrarProduto(Produto produto);

	HttpEntity<Produto> editarProduto(Produto produto);

	HttpEntity<Object> removerProduto(Long id);

	HttpEntity<List<Produto>> getTodosOsProdutos();
	
	HttpEntity<List<Produto>> getTodosOsProdutosPor(String tipoProduto);
}
