package br.com.postech.techchallenge.infraestrutura.gateway.cliente;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.exception.CpfInvalidoException;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;

public class ClienteEntityMapperTest {

	private ClienteEntityMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new ClienteEntityMapper();
	}

	@Test
	void testToEntity() throws CpfInvalidoException {

		Cliente clienteDomain = new Cliente();
		clienteDomain.setId(1L);
		clienteDomain.setNome("Fulano");
		clienteDomain.setEmail("fulano@test.com");
		clienteDomain.setCpf("12345678909");

		ClienteEntity entity = mapper.toEntity(clienteDomain);

		assertThat(entity).isNotNull();
		assertThat(entity.getId()).isEqualTo(1L);
		assertThat(entity.getNome()).isEqualTo("Fulano");
		assertThat(entity.getEmail()).isEqualTo("fulano@test.com");
		assertThat(entity.getCpf()).isEqualTo("12345678909");
	}

	@Test
	void testToDomainObject() throws CpfInvalidoException {

		ClienteEntity entity = new ClienteEntity();
		entity.setId(1L);
		entity.setNome("Fulano");
		entity.setEmail("fulano@test.com");
		entity.setCpf("12345678909");

		Cliente clienteDomain = mapper.toDomainObject(entity);

		assertThat(clienteDomain).isNotNull();
		assertThat(clienteDomain.getId()).isEqualTo(1L);
		assertThat(clienteDomain.getNome()).isEqualTo("Fulano");
		assertThat(clienteDomain.getEmail()).isEqualTo("fulano@test.com");
		assertThat(clienteDomain.getCpf()).isEqualTo("12345678909");
	}
}
