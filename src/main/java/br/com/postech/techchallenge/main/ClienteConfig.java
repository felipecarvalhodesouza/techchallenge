package br.com.postech.techchallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.application.usecases.ClienteInteractor;
import br.com.postech.techchallenge.infraestrutura.gateway.cliente.ClienteEntityMapper;
import br.com.postech.techchallenge.infraestrutura.gateway.cliente.ClienteRepositoryGateway;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteRepository;

@Configuration
public class ClienteConfig {

	@Bean
	ClienteInteractor createClienteUseCase(ClienteGateway clienteGateway) {
		return new ClienteInteractor(clienteGateway);
	}

	@Bean
	ClienteGateway clienteGateway(ClienteRepository clienteRepository, ClienteEntityMapper mapper) {
		return new ClienteRepositoryGateway(clienteRepository, mapper);
	}

	@Bean
	ClienteEntityMapper clienteEntityMapper() {
		return new ClienteEntityMapper();
	}
}
