package br.com.postech.techchallenge.ports;

import java.util.List;

import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.model.TipoProduto;

public interface ProdutoOutputPort {

	Produto registrarProduto(Produto produto);

	Produto editarProduto(Produto produto);

	void removerProduto(Long id);

	List<Produto> getTodosOsProdutos();

	List<Produto> getTodosOsProdutosPor(TipoProduto tipoProduto);
}
