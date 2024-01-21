package br.com.postech.techchallenge.adapters.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Autowired
    private ClienteController clienteController;
	
	@Test
	void getClienteTest() {
		HttpEntity<Cliente> cliente = clienteController.getClientePor(String.valueOf(cpf));
		assertNotNull(cliente);
		assertEquals(cpf, cliente.getBody().getCpf());
		assertEquals("email@provedor.com.br", cliente.getBody().getEmail());
		assertEquals("Felipe Carvalho de Souza", cliente.getBody().getNome());
	}
	
	@Test
	void registrarClienteSemCpfTest() throws CpfInvalidoException {
		Cliente cliente = new Cliente();
		cliente.setNome("Benedita Rebeca Lavínia Caldeira");
		cliente.setEmail("benedita.rebeca.caldeira@tirantea.com.br");
		assertThrows(CpfInvalidoException.class, () -> clienteController.registrarCliente(cliente));
	}
	
	@Test
	void registrarClienteDuplicadoTest() throws CpfInvalidoException {
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		assertThrows(CpfDuplicadoException.class, () -> clienteController.registrarCliente(cliente));
	}
	
	@Test
	void registrarClienteTest() throws CpfInvalidoException, CpfDuplicadoException {
		Cliente cliente = new Cliente();
		cliente.setNome("Benedita Rebeca Lavínia Caldeira");
		cliente.setEmail("benedita.rebeca.caldeira@tirantea.com.br");
		cliente.setCpf(93159958051l);
		HttpEntity<Cliente> clienteBanco = clienteController.registrarCliente(cliente);
		assertNotNull(clienteBanco);
	}
	
	@Test
	void editarClienteTest() throws CpfInvalidoException, CpfDuplicadoException, ClienteInexistenteException {
		Cliente cliente = new Cliente();
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
		Cliente cliente = new Cliente();
		cliente.setId(10l);
		assertThrows(ClienteInexistenteException.class, () ->  clienteController.editarCliente(cliente));
	}
}
