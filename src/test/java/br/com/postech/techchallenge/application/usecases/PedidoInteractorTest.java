package br.com.postech.techchallenge.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.application.gateway.PedidoGateway;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.exception.PedidoInvalidoException;

public class PedidoInteractorTest {

	@Mock
	private PedidoGateway pedidoGateway;

	@Mock
	private ClienteGateway clienteGateway;

	@InjectMocks
	private PedidoInteractor pedidoInteractor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void inserirPedidoValido() throws PedidoInvalidoException, ClienteInexistenteException, MalformedURLException, IOException {

		Cliente cliente = new Cliente();
		cliente.setId(1L);

		Pedido pedido = new Pedido();
		pedido.setValorTotal(100.0d);
		pedido.setCliente(cliente);

		when(clienteGateway.buscarPor(1L)).thenReturn(cliente);
		when(pedidoGateway.inserir(pedido)).thenReturn(pedido);

		Pedido resultado = pedidoInteractor.inserir(pedido);

		assertThat(resultado).isEqualTo(pedido);
		verify(clienteGateway).buscarPor(1L);
		verify(pedidoGateway).inserir(pedido);
	}

	@Test
	void inserirPedidoInvalido() {

		Pedido pedido = new Pedido();
		pedido.setValorTotal(0d);

		assertThatThrownBy(() -> pedidoInteractor.inserir(pedido)).isInstanceOf(PedidoInvalidoException.class);
	}

	@Test
	void getPedidosPorEmail() throws ClienteInexistenteException {

		Cliente cliente = new Cliente();
		cliente.setId(1L);
		String email = "cliente@exemplo.com";

		Pedido pedido1 = new Pedido();
		pedido1.setCliente(cliente);
		Pedido pedido2 = new Pedido();
		pedido2.setCliente(cliente);
		List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

		when(clienteGateway.buscarPor(email)).thenReturn(cliente);
		when(pedidoGateway.getPedidosPor("1")).thenReturn(pedidos);

		List<Pedido> resultado = pedidoInteractor.getPedidosPor(email);

		assertThat(resultado).containsExactlyInAnyOrder(pedido1, pedido2);
		verify(clienteGateway).buscarPor(email);
		verify(pedidoGateway).getPedidosPor("1");
	}

	@Test
	void deletePedido() throws PedidoInexistenteException {

		String pedidoId = "1";

		doNothing().when(pedidoGateway).excluir(1L);

		pedidoInteractor.delete(pedidoId);

		verify(pedidoGateway).excluir(1L);
	}
}
