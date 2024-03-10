package br.com.postech.techchallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.postech.techchallenge.application.gateway.FilaPedidoGateway;
import br.com.postech.techchallenge.application.usecases.FilaPedidoInteractor;
import br.com.postech.techchallenge.infraestrutura.gateway.filapedido.FilaPedidoEntityMapper;
import br.com.postech.techchallenge.infraestrutura.gateway.filapedido.FilaPedidoRepositoryGateway;
import br.com.postech.techchallenge.infraestrutura.persistence.filapedido.FilaPedidoRepository;

@Configuration
public class FilaPedidoConfig {

	@Bean
	FilaPedidoInteractor createFilaPedidoUseCase(FilaPedidoGateway FilaPedidoGateway) {
		return new FilaPedidoInteractor(FilaPedidoGateway);
	}

	@Bean
	FilaPedidoGateway FilaPedidoGateway(FilaPedidoRepository FilaPedidoRepository, FilaPedidoEntityMapper mapper) {
		return new FilaPedidoRepositoryGateway(FilaPedidoRepository, mapper);
	}

	@Bean
	FilaPedidoEntityMapper FilaPedidoEntityMapper() {
		return new FilaPedidoEntityMapper();
	}
}
