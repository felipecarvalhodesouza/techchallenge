package br.com.postech.techchallenge.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.domain.entity.CPF;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import br.com.postech.techchallenge.infraestrutura.controller.ClienteController;

@SpringBootTest
class ClienteControllerTest {

	private static final Long cpf = 12345678909l;
	Cliente cliente;
	
	@BeforeEach
	void setup() {
		cliente = new Cliente();
	}

    @Autowired
    private ClienteController clienteController;
	
	@Test
	void getClienteTest() throws CpfInvalidoException {
		Cliente cliente = clienteController.getClientePor(String.valueOf(cpf));
		assertNotNull(cliente);
		assertEquals(cpf, Long.valueOf(cliente.getCpf().getDocumento()));
		assertEquals("email@provedor.com.br", cliente.getEmail());
		assertEquals("Felipe Carvalho de Souza", cliente.getNome());
	}
	
	@Test
	void registrarClienteDuplicadoTest() throws CpfInvalidoException {
		cliente.setCpf(new CPF(cpf));
		assertThrows(CpfDuplicadoException.class, () -> clienteController.registrar(cliente));
	}
	
	@Test
	void registrarClienteTest() throws CpfInvalidoException, CpfDuplicadoException {
		cliente.setNome("Benedita Rebeca Lavínia Caldeira");
		cliente.setEmail("benedita.rebeca.caldeira@tirantea.com.br");
		cliente.setCpf(new CPF(93159958051l));
		Cliente clienteBanco = clienteController.registrar(cliente);
		assertNotNull(clienteBanco);
	}
	
	@Test
	void editarClienteTest() throws CpfInvalidoException, CpfDuplicadoException, ClienteInexistenteException {
		cliente.setNome("João");
		cliente.setCpf(new CPF(93159958051l));
		cliente.setId(2l);
		Cliente editarCliente = clienteController.editar(cliente);
		assertEquals(cliente.getNome(), editarCliente.getNome());
	}
	
	@Test
	void removerClienteTest() throws CpfInvalidoException, CpfDuplicadoException, ClienteInexistenteException {
		clienteController.removerCliente(2l);
		Cliente clienteBanco = clienteController.getClientePor(String.valueOf(93159958051l));
		assertNull(clienteBanco);
	}
	
	@Test
	void editarClienteInexistenteTest() {
		cliente.setId(10l);
		assertThrows(ClienteInexistenteException.class, () ->  clienteController.editar(cliente));
	}
	
	@Test
	void getTodosOsClientesTest() {
		List<Cliente> clientes = clienteController.getTodosOsClientes();
		assertEquals(1, clientes.size());
	}
}
