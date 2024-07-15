package br.com.postech.techchallenge.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.infraestrutura.gateway.pedido.PedidoEntityMapper;

@SpringBootTest
@ActiveProfiles("test")
class PedidoConfigTest {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertThat(context.getBean(PedidoInteractor.class)).isNotNull();
		assertThat(context.getBean(PedidoGateway.class)).isNotNull();
		assertThat(context.getBean(PedidoEntityMapper.class)).isNotNull();
	}

}
