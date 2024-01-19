package br.com.postech.techchallenge.adapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.model.Cliente;
import br.com.postech.techchallenge.domain.service.ClienteService;
import br.com.postech.techchallenge.ports.RegistraClienteInputPort;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements RegistraClienteInputPort {
	
	@Autowired
	ClienteService service;

	@Override
	@PostMapping
	public void registraCliente(String nome, String email, long cpf) {
		// TODO Auto-generated method stub
	}
	
	@GetMapping
	public ResponseEntity<Cliente> getCliente() {
		return new ResponseEntity<Cliente>(service.getCliente("John Doe"), HttpStatusCode.valueOf(200));
	}

}
