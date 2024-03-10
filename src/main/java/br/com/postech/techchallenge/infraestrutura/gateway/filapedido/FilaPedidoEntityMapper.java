package br.com.postech.techchallenge.infraestrutura.gateway.filapedido;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.infraestrutura.persistence.filapedido.FilaPedidoEntity;

public class FilaPedidoEntityMapper {

	FilaPedidoEntity toEntity(FilaPedido filaPedidoDomain) {
		FilaPedidoEntity entity = new FilaPedidoEntity();
		BeanUtils.copyProperties(filaPedidoDomain, entity);
		return entity;
	}

	FilaPedido toDomainObject(FilaPedidoEntity entity) {
		FilaPedido filaPedido = new FilaPedido();
		BeanUtils.copyProperties(entity, filaPedido);
		return filaPedido;
	}
}
