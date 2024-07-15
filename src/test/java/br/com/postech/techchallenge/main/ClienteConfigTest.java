package br.com.postech.techchallenge.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.application.usecases.ClienteInteractor;
import br.com.postech.techchallenge.infraestrutura.gateway.cliente.ClienteEntityMapper;

@SpringBootTest
@ActiveProfiles("test")
class ClienteConfigTest {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertThat(context.getBean(ClienteInteractor.class)).isNotNull();
		assertThat(context.getBean(ClienteGateway.class)).isNotNull();
		assertThat(context.getBean(ClienteEntityMapper.class)).isNotNull();
	}

}
