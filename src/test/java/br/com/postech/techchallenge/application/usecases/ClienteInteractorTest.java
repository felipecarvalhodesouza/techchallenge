package br.com.postech.techchallenge.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.exception.CpfInvalidoException;

public class ClienteInteractorTest {

	@Mock
	private ClienteGateway clienteGateway;

	@InjectMocks
	private ClienteInteractor clienteInteractor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void buscarPorCpf() throws CpfInvalidoException {

		Cliente cliente = new Cliente();
		cliente.setCpf("12345678909");

		when(clienteGateway.buscarPorCpf(cliente)).thenReturn(cliente);

		Cliente resultado = clienteInteractor.buscar(cliente);

		assertThat(resultado).isEqualTo(cliente);
		verify(clienteGateway).buscarPorCpf(cliente);
	}

	@Test
	void buscarPorId() throws ClienteInexistenteException {

		Cliente cliente = new Cliente();
		cliente.setId(1L);

		when(clienteGateway.buscarPor(1L)).thenReturn(cliente);

		Cliente resultado = clienteInteractor.buscarPor(1L);

		assertThat(resultado).isEqualTo(cliente);
		verify(clienteGateway).buscarPor(1L);
	}

	@Test
	void registrarCliente() throws CpfDuplicadoException, IOException, CpfInvalidoException {

		Cliente cliente = new Cliente();
		cliente.setCpf("12345678909");

		when(clienteGateway.buscarPorCpf(cliente)).thenReturn(null);
		when(clienteGateway.registrar(cliente)).thenReturn(cliente);

		Cliente resultado = clienteInteractor.registrar(cliente);

		assertThat(resultado).isEqualTo(cliente);
		verify(clienteGateway).buscarPorCpf(cliente);
		verify(clienteGateway).registrar(cliente);
	}

	@Test
	void registrarClienteComCpfDuplicado() throws CpfInvalidoException {

		Cliente cliente = new Cliente();
		cliente.setCpf("12345678909");

		when(clienteGateway.buscarPorCpf(cliente)).thenReturn(cliente);

		assertThatThrownBy(() -> clienteInteractor.registrar(cliente)).isInstanceOf(CpfDuplicadoException.class);
	}

	@Test
	void editarCliente() throws ClienteInexistenteException {

		Cliente cliente = new Cliente();
		cliente.setId(1L);

		when(clienteGateway.buscarPor(1L)).thenReturn(cliente);
		when(clienteGateway.editar(cliente)).thenReturn(cliente);

		Cliente resultado = clienteInteractor.editar(cliente);

		assertThat(resultado).isEqualTo(cliente);
		verify(clienteGateway).buscarPor(1L);
		verify(clienteGateway).editar(cliente);
	}

	@Test
	void buscarTodosClientes() {

		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

		when(clienteGateway.buscarTodos()).thenReturn(clientes);

		List<Cliente> resultado = clienteInteractor.buscarTodos();

		assertThat(resultado).containsExactlyInAnyOrder(cliente1, cliente2);
		verify(clienteGateway).buscarTodos();
	}

	@Test
	void removerCliente() {

		Cliente cliente = new Cliente();

		doNothing().when(clienteGateway).remover(cliente);

		clienteInteractor.remover(cliente);

		verify(clienteGateway).remover(cliente);
	}
}
