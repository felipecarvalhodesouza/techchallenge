package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.model.TipoProduto;
import br.com.postech.techchallenge.ports.ProdutoOutputPort;

@Repository
public class ProdutoPortImpl implements ProdutoOutputPort {

	private final ProdutoJpaRepository produtoJpaRepository;

	@Autowired
	public ProdutoPortImpl(ProdutoJpaRepository produtoJpaRepository) {
		this.produtoJpaRepository = produtoJpaRepository;
	}

	@Override
	public Produto registrarProduto(Produto produto) {
		return produtoJpaRepository.save(produto);
	}

	@Override
	public Produto editarProduto(Produto produto) {
		return produtoJpaRepository.save(produto);
	}

	@Override
	public void removerProduto(Long id) {
		produtoJpaRepository.deleteById(id);
	}

	@Override
	public List<Produto> getTodosOsProdutos() {
		return produtoJpaRepository.findAll();
	}

	@Override
	public List<Produto> getTodosOsProdutosPor(TipoProduto tipoProduto) {
		return produtoJpaRepository.findAllByTipoProduto(tipoProduto);
	}

}
