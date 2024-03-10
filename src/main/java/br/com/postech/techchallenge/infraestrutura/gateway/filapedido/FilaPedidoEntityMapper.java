package br.com.postech.techchallenge.infraestrutura.gateway.filapedido;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.FilaPedido;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.infraestrutura.gateway.pedido.PedidoEntityMapper;
import br.com.postech.techchallenge.infraestrutura.persistence.filapedido.FilaPedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;

public class FilaPedidoEntityMapper {

	FilaPedidoEntity toEntity(FilaPedido filaPedidoDomain) {
		FilaPedidoEntity entity = new FilaPedidoEntity();
		BeanUtils.copyProperties(filaPedidoDomain, entity);

		if(filaPedidoDomain.getPedido() != null) {
			entity.setPedido(toEntity(filaPedidoDomain.getPedido()));
		}

		return entity;
	}

	FilaPedido toDomainObject(FilaPedidoEntity entity) {
		FilaPedido filaPedido = new FilaPedido();
		BeanUtils.copyProperties(entity, filaPedido);
		
		if(entity.getPedido() != null) {
			filaPedido.setPedido(toDomainObject(entity.getPedido()));
		}
		
		return filaPedido;
	}
	
	Pedido toDomainObject(PedidoEntity entity) {
		return new PedidoEntityMapper().toDomainObject(entity);
	}
	
	PedidoEntity toEntity(Pedido pedido) {
		return new PedidoEntityMapper().toEntity(pedido);
	}
}
