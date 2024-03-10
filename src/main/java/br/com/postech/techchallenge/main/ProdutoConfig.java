package br.com.postech.techchallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.postech.techchallenge.application.gateway.ProdutoGateway;
import br.com.postech.techchallenge.application.usecases.ProdutoInteractor;
import br.com.postech.techchallenge.infraestrutura.gateway.produto.ProdutoEntityMapper;
import br.com.postech.techchallenge.infraestrutura.gateway.produto.ProdutoRepositoryGateway;
import br.com.postech.techchallenge.infraestrutura.persistence.produto.ProdutoRepository;

@Configuration
public class ProdutoConfig {

	@Bean
	ProdutoInteractor createProdutoUseCase(ProdutoGateway ProdutoGateway) {
		return new ProdutoInteractor(ProdutoGateway);
	}

	@Bean
	ProdutoGateway ProdutoGateway(ProdutoRepository ProdutoRepository, ProdutoEntityMapper mapper) {
		return new ProdutoRepositoryGateway(ProdutoRepository, mapper);
	}

	@Bean
	ProdutoEntityMapper ProdutoEntityMapper() {
		return new ProdutoEntityMapper();
	}
}
