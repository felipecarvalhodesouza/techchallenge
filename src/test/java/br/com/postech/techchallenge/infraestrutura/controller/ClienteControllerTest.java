package br.com.postech.techchallenge.infraestrutura.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.postech.techchallenge.application.usecases.ClienteInteractor;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import io.restassured.common.mapper.TypeRef;

class ClienteControllerTest {

	@Mock
	private ClienteInteractor clienteInteractor;

	@InjectMocks
	private ClienteController clienteController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		standaloneSetup(clienteController);
	}

	@Test
	void deveRemoverCliente() throws ClienteInexistenteException {
		Long id = 1L;
		Cliente cliente = new Cliente();
		cliente.setId(id);
		when(clienteInteractor.buscarPor(id)).thenReturn(cliente);

		given().when().delete("/clientes/{id}", String.valueOf(id)).then().statusCode(HttpStatus.NO_CONTENT.value());

		verify(clienteInteractor).remover(any(Cliente.class));
	}

	@Test
	void deveBuscarTodosOsClientes() {
		List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
		when(clienteInteractor.buscarTodos()).thenReturn(clientes);

		List<Cliente> result = given().when().get("/clientes/lista").then().statusCode(HttpStatus.OK.value()).extract()
				.as(new TypeRef<List<Cliente>>() {
				});

		assertThat(result).isNotNull().hasSize(2);
		verify(clienteInteractor).buscarTodos();
	}
}
