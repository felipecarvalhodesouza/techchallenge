package br.com.postech.techchallenge.infraestrutura.persistence.filapedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilaPedidoRepository extends JpaRepository<FilaPedidoEntity, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM fila_pedido f WHERE f.status <> 'FINALIZADO'")
	public List<FilaPedidoEntity> getFilaPedido();
	
	public FilaPedidoEntity findByPedidoId(long id);

}
