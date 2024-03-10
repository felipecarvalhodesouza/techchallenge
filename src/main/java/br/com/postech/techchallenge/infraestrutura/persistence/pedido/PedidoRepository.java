package br.com.postech.techchallenge.infraestrutura.persistence.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{

	List<PedidoEntity> getByClienteId(long clienteId);
}
