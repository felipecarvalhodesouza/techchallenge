package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.postech.techchallenge.domain.model.FilaPedido;

public interface FilaPedidoJpaRepository extends JpaRepository<FilaPedido, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM fila_pedido f WHERE f.status <> 'FINALIZADO'")
	public List<FilaPedido> getFilaPedido();

}
