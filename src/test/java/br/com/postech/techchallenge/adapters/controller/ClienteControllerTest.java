package br.com.postech.techchallenge.adapters.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;

import br.com.postech.techchallenge.domain.model.Cliente;

@SpringBootTest
class ClienteControllerTest {

	private static final String nome = "Felipe Carvalho de Souza";

    @Autowired
    private ClienteController clienteController;
	
	@Test
	void getClienteTest() {
		HttpEntity<Cliente> cliente = clienteController.getCliente(nome);
		assertNotNull(cliente);
		assertEquals(12345678909l, cliente.getBody().getCpf());
		assertEquals("email@provedor.com.br", cliente.getBody().getEmail());
		assertEquals(nome, cliente.getBody().getNome());
	}
	
	@Test
	void registrarClienteTest() {
		Cliente cliente = new Cliente();
		cliente.setNome("Benedita Rebeca Lavínia Caldeira");
		cliente.setEmail("benedita.rebeca.caldeira@tirantea.com.br");
		
		HttpEntity<Cliente> clienteEntity = clienteController.registrarCliente(cliente);
		
		assertNotNull(clienteEntity.getBody());
		assertEquals(clienteEntity.getBody().getCpf(), 0);

	}
	
	
	

}
