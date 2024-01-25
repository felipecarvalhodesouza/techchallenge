package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postech.techchallenge.domain.model.Pedido;

public interface PedidoJpaRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> getByClienteId(long clienteId);
}
