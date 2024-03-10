package br.com.postech.techchallenge.infraestrutura.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.techchallenge.application.usecases.FilaPedidoInteractor;
import br.com.postech.techchallenge.domain.entity.FilaPedido;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "4 - API de Fila de Pedidos", description = "API responsável pela exibição do status dos pedidos")
public class FilaPedidoController {

	FilaPedidoInteractor filaPedidoInteractor;

	public FilaPedidoController(FilaPedidoInteractor filaPedidoInteractor) {
		this.filaPedidoInteractor = filaPedidoInteractor;
	}
	
	@GetMapping
	public List<FilaPedido> getFilaPedido() {
		return filaPedidoInteractor.getFilaPedido();
	}
}