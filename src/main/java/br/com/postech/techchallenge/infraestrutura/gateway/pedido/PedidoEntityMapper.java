package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.Produto;
import br.com.postech.techchallenge.infraestrutura.gateway.cliente.ClienteEntityMapper;
import br.com.postech.techchallenge.infraestrutura.gateway.produto.ProdutoEntityMapper;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.produto.ProdutoEntity;

public class PedidoEntityMapper {

	public PedidoEntity toEntity(Pedido pedidoDomain) {
		PedidoEntity entity = new PedidoEntity();
		BeanUtils.copyProperties(pedidoDomain, entity);

		if (pedidoDomain.getProdutoList() != null) {
			List<ProdutoEntity> produtos = pedidoDomain.getProdutoList()
													   .stream()
													   .map(this::toEntity)
													   .collect(Collectors.toList());
			entity.setProdutoList(produtos);
		}

		if (pedidoDomain.getCliente() != null) {
			entity.setCliente(toEntity(pedidoDomain.getCliente()));
		}

		return entity;
	}

	public Pedido toDomainObject(PedidoEntity entity) {
		Pedido pedido = new Pedido();
		BeanUtils.copyProperties(entity, pedido);

		if (entity.getProdutoList() != null) {
			List<Produto> produtos = entity.getProdutoList().stream().map(this::toDomain).collect(Collectors.toList());
			pedido.setProdutoList(produtos);
		}

		if (entity.getCliente() != null) {
			pedido.setCliente(toDomain(entity.getCliente()));
		}

		return pedido;
	}

	private Produto toDomain(ProdutoEntity entity) {
		return new ProdutoEntityMapper().toDomainObject(entity);
	}

	private Cliente toDomain(ClienteEntity entity) {
		return new ClienteEntityMapper().toDomainObject(entity);
	}

	private ProdutoEntity toEntity(Produto produto) {
		return new ProdutoEntityMapper().toEntity(produto);
	}

	private ClienteEntity toEntity(Cliente cliente) {
		return new ClienteEntityMapper().toEntity(cliente);
	}
}
