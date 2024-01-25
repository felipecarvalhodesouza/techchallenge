package br.com.postech.techchallenge.adapters.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.postech.techchallenge.domain.model.FilaPedido;
import br.com.postech.techchallenge.ports.FilaPedidoOutputPort;

@Repository
public class FilaPedidoPortImpl implements FilaPedidoOutputPort {

	private FilaPedidoJpaRepository filaPedidoJpaRepository;
	
	@Autowired
	public FilaPedidoPortImpl(FilaPedidoJpaRepository filaPedidoJpaRepository) {
		this.filaPedidoJpaRepository = filaPedidoJpaRepository;
	}
	
	@Override
	public List<FilaPedido> getFilaPedido() {
		return filaPedidoJpaRepository.getFilaPedido();
	}

	@Override
	public void enviaPara(FilaPedido filaPedido) {
		filaPedidoJpaRepository.save(filaPedido);
	}
}
