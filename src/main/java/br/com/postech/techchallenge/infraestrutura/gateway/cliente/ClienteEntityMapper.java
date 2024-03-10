package br.com.postech.techchallenge.infraestrutura.gateway.cliente;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.CPF;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;

public class ClienteEntityMapper {

	public ClienteEntity toEntity(Cliente clienteDomain) {
		ClienteEntity entity = new ClienteEntity();
		BeanUtils.copyProperties(clienteDomain, entity);

		if(clienteDomain.getCpf() != null) {
			entity.setCpf(Long.valueOf(clienteDomain.getCpf().getDocumento()));
		}

		return entity;
	}

	public Cliente toDomainObject(ClienteEntity entity) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(entity, cliente);
		
		try{ 
			if(entity.getCpf() > 0) {
				cliente.setCpf(new CPF(entity.getCpf()));
			}
		}catch (CpfInvalidoException e) {
			throw new RuntimeException(e);
		}
		
		return cliente;
	}
}
