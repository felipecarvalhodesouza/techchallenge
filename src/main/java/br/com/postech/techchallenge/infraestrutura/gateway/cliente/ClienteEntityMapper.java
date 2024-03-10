package br.com.postech.techchallenge.infraestrutura.gateway.cliente;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;

public class ClienteEntityMapper {

	public ClienteEntity toEntity(Cliente clienteDomain) {
		ClienteEntity entity = new ClienteEntity();
		BeanUtils.copyProperties(clienteDomain, entity);
		return entity;
	}

	public Cliente toDomainObject(ClienteEntity entity) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(entity, cliente);
		return cliente;
	}
}
