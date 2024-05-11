package br.com.postech.techchallenge.infraestrutura.persistence.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postech.techchallenge.domain.entity.enumeration.TipoProduto;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{

	List<ProdutoEntity> findAllByTipoProduto(TipoProduto tipoProduto);

	List<ProdutoEntity> getByNomeProduto(String nomeProduto);
}
