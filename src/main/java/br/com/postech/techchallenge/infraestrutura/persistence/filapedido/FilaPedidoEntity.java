package br.com.postech.techchallenge.infraestrutura.persistence.filapedido;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.postech.techchallenge.domain.entity.enumeration.StatusPreparacao;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "fila_pedido")
public class FilaPedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@OneToOne
	@JsonIgnore
	private PedidoEntity pedido;

	@Enumerated(EnumType.STRING)
	private StatusPreparacao status = StatusPreparacao.RECEBIDO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

	public long getCodigoPedido() {
		return pedido.getId();
	}

	public StatusPreparacao getStatus() {
		return status;
	}

	public void setStatus(StatusPreparacao status) {
		this.status = status;
	}
	

}
