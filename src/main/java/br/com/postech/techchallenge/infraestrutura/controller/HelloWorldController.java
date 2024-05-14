package br.com.postech.techchallenge.infraestrutura.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/")
public class HelloWorldController {

	@ApiResponse(responseCode = "200")
	@GetMapping
	public String helloWorld(String cpf) throws CpfInvalidoException {
		return "Hello World! Tech Challenge 3";
	}
}