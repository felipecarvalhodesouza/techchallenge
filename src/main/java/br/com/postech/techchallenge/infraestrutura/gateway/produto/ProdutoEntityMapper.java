package br.com.postech.techchallenge.infraestrutura.gateway.produto;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.Produto;
import br.com.postech.techchallenge.infraestrutura.persistence.produto.ProdutoEntity;

public class ProdutoEntityMapper {

	public ProdutoEntity toEntity(Produto produtoDomain) {
		ProdutoEntity entity = new ProdutoEntity();
		BeanUtils.copyProperties(produtoDomain, entity);
		return entity;
	}

	public Produto toDomainObject(ProdutoEntity entity) {
		Produto produto = new Produto();
		BeanUtils.copyProperties(entity, produto);
		return produto;
	}
}
