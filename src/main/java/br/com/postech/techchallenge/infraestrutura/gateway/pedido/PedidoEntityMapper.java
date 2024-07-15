package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.infraestrutura.gateway.cliente.ClienteEntityMapper;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;

public class PedidoEntityMapper {

	public PedidoEntity toEntity(Pedido pedidoDomain) {
		PedidoEntity entity = new PedidoEntity();
		BeanUtils.copyProperties(pedidoDomain, entity);

		if (pedidoDomain.getCliente() != null) {
			entity.setCliente(toEntity(pedidoDomain.getCliente()));
		}

		return entity;
	}

	public Pedido toDomainObject(PedidoEntity entity) {
		Pedido pedido = new Pedido();
		BeanUtils.copyProperties(entity, pedido);

		if (entity.getCliente() != null) {
			pedido.setCliente(toDomain(entity.getCliente()));
		}

		return pedido;
	}

	private Cliente toDomain(ClienteEntity entity) {
		return new ClienteEntityMapper().toDomainObject(entity);
	}

	private ClienteEntity toEntity(Cliente cliente) {
		return new ClienteEntityMapper().toEntity(cliente);
	}
}
