package br.com.postech.techchallenge.application.usecases;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.application.gateway.ProdutoGateway;
import br.com.postech.techchallenge.domain.entity.Produto;
import br.com.postech.techchallenge.domain.entity.enumeration.TipoProduto;

@Service
public class ProdutoInteractor {

	private final ProdutoGateway produtoGateway;

	public ProdutoInteractor(ProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}

	public Produto registrar(Produto produto) {
		return produtoGateway.registrar(produto);
	}

	public Produto editar(Produto produto) {
		return produtoGateway.editar(produto);
	}

	public void remover(Long id) {
		produtoGateway.remover(id);
	}

	public List<Produto> getTodosOsProdutos() {
		return produtoGateway.getTodosOsProdutos();
	}

	public List<Produto> getTodosOsProdutosPor(String tipoProduto) {
		TipoProduto tipoProdutoEnum = TipoProduto.obterPorDescricao(tipoProduto);
		if (tipoProdutoEnum == null) {
			return Collections.emptyList();
		}
		return produtoGateway.getTodosOsProdutosPor(tipoProdutoEnum);
	}
}
