package br.com.postech.techchallenge.adapters.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.service.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.service.exception.CpfDuplicadoException;
import br.com.postech.techchallenge.domain.service.exception.CpfInvalidoException;

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
	void getClienteTest() {
		HttpEntity<Cliente> clienteEntity = clienteController.getClientePor(String.valueOf(cpf));
		assertNotNull(clienteEntity);
		assertEquals(cpf, clienteEntity.getBody().getCpf());
		assertEquals("email@provedor.com.br", clienteEntity.getBody().getEmail());
		assertEquals("Felipe Carvalho de Souza", clienteEntity.getBody().getNome());
	}
	
	@Test
	void registrarClienteSemCpfTest() throws CpfInvalidoException {
		cliente.setNome("Benedita Rebeca Lavínia Caldeira");
		cliente.setEmail("benedita.rebeca.caldeira@tirantea.com.br");
		assertThrows(CpfInvalidoException.class, () -> clienteController.registrarCliente(cliente));
	}
	
	@Test
	void registrarClienteDuplicadoTest() throws CpfInvalidoException {
		cliente.setCpf(cpf);
		assertThrows(CpfDuplicadoException.class, () -> clienteController.registrarCliente(cliente));
	}
	
	@Test
	void registrarClienteTest() throws CpfInvalidoException, CpfDuplicadoException {
		cliente.setNome("Benedita Rebeca Lavínia Caldeira");
		cliente.setEmail("benedita.rebeca.caldeira@tirantea.com.br");
		cliente.setCpf(93159958051l);
		HttpEntity<Cliente> clienteBanco = clienteController.registrarCliente(cliente);
		assertNotNull(clienteBanco);
	}
	
	@Test
	void editarClienteTest() throws CpfInvalidoException, CpfDuplicadoException, ClienteInexistenteException {
		cliente.setNome("João");
		cliente.setCpf(93159958051l);
		cliente.setId(2l);
		HttpEntity<Cliente> editarCliente = clienteController.editarCliente(cliente);
		assertEquals(cliente.getNome(), editarCliente.getBody().getNome());
	}
	
	@Test
	void removerClienteTest() throws CpfInvalidoException, CpfDuplicadoException {
		clienteController.removerCliente(2l);
		HttpEntity<Cliente> clienteBanco = clienteController.getClientePor(String.valueOf(93159958051l));
		assertNull(clienteBanco.getBody());
	}
	
	@Test
	void editarClienteInexistenteTest() {
		cliente.setId(10l);
		assertThrows(ClienteInexistenteException.class, () ->  clienteController.editarCliente(cliente));
	}
	
	@Test
	void getTodosOsClientesTest() {
		List<Cliente> clientes = clienteController.getTodosOsClientes().getBody();
		assertEquals(1, clientes.size());
	}
}
