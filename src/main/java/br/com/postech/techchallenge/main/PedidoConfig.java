package br.com.postech.techchallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.postech.techchallenge.application.gateway.FilaPedidoGateway;
import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.infraestrutura.gateway.pedido.PedidoEntityMapper;
import br.com.postech.techchallenge.infraestrutura.gateway.pedido.PedidoRepositoryGateway;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;

@Configuration
public class PedidoConfig {

	@Bean
	PedidoInteractor createPedidoUseCase(PedidoGateway PedidoGateway, FilaPedidoGateway filaPedidoGateway) {
		return new PedidoInteractor(PedidoGateway, filaPedidoGateway);
	}

	@Bean
	PedidoGateway PedidoGateway(PedidoRepository PedidoRepository, PedidoEntityMapper mapper) {
		return new PedidoRepositoryGateway(PedidoRepository, mapper);
	}

	@Bean
	PedidoEntityMapper PedidoEntityMapper() {
		return new PedidoEntityMapper();
	}
}
