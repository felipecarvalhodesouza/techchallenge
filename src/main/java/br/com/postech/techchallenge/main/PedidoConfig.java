package br.com.postech.techchallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.domain.repository.IPagamentoQueueAdapter;
import br.com.postech.techchallenge.infraestrutura.gateway.pedido.PedidoEntityMapper;
import br.com.postech.techchallenge.infraestrutura.gateway.pedido.PedidoRepositoryGateway;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;
import br.com.postech.techchallenge.infraestrutura.queue.PagamentoQueueAdapter;

@Configuration
public class PedidoConfig {

	@Bean
	PedidoInteractor createPedidoUseCase(PedidoGateway PedidoGateway,  ClienteGateway clienteGateway) {
		return new PedidoInteractor(PedidoGateway, clienteGateway);
	}

	@Bean
	PedidoGateway createPedidoGateway(PedidoRepository PedidoRepository, PedidoEntityMapper mapper, IPagamentoQueueAdapter pagamentoQueueAdapter) {
		return new PedidoRepositoryGateway(PedidoRepository, mapper, pagamentoQueueAdapter);
	}

	@Bean
	PedidoEntityMapper createPedidoEntityMapper() {
		return new PedidoEntityMapper();
	}
	
	@Bean
	IPagamentoQueueAdapter createIPagamentoQueueAdapter() {
		return new PagamentoQueueAdapter();	
	}
}
