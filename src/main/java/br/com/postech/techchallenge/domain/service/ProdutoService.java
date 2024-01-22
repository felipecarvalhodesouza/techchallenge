package br.com.postech.techchallenge.domain.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.model.TipoProduto;
import br.com.postech.techchallenge.ports.ProdutoOutputPort;

@Service
public class ProdutoService {

	private final ProdutoOutputPort port;

	@Autowired
	public ProdutoService(ProdutoOutputPort port) {
		this.port = port;
	}

	public Produto registrarProduto(Produto produto) {
		return port.registrarProduto(produto);
	}

	public Produto editarProduto(Produto produto) {
		return port.editarProduto(produto);
	}

	public void removerProduto(Long id) {
		port.removerProduto(id);
	}

	public List<Produto> getTodosOsProdutos() {
		return port.getTodosOsProdutos();
	}

	public List<Produto> getTodosOsProdutosPor(String tipoProduto) {
		TipoProduto tipoProdutoEnum = TipoProduto.obterPorDescricao(tipoProduto);
		if (tipoProdutoEnum == null) {
			return Collections.emptyList();
		}
		return port.getTodosOsProdutosPor(tipoProdutoEnum);
	}
}
