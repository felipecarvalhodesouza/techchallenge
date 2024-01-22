package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.model.TipoProduto;

public interface ProdutoJpaRepository extends JpaRepository<Produto, Long>{

	List<Produto> findAllByTipoProduto(TipoProduto tipoProduto);
}
