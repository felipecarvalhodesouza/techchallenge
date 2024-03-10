package br.com.postech.techchallenge.infraestrutura.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.domain.entity.Produto;
import br.com.postech.techchallenge.domain.entity.enumeration.TipoProduto;

@SpringBootTest
class ProdutoControllerTest {

	private Produto produto;
	private Long idProdutoIncluido = 19l;

	@Autowired
	private ProdutoController produtoController;

	@BeforeEach
	private void setup() {
		produto = new Produto();
	}

	@Test
	void testRegistrarProduto() {
		produto.setNomeProduto("Coquinha gelada");
		produto.setValor(5);
		produto.setTipoProduto(TipoProduto.BEBIDA);

		produtoController.registrar(produto);

		assertNotNull(idProdutoIncluido);
		assertNotEquals(0, idProdutoIncluido);
	}

	@Test
	void testEditarProduto() {
		produto.setId(idProdutoIncluido);
		produto.setNomeProduto("Pepsi");

		produtoController.editar(produto);

		List<Produto> collect = getProdutoPorId(idProdutoIncluido);

		assertEquals(1, collect.size());
		assertEquals("Pepsi", collect.get(0).getNomeProduto());
	}

	@Test
	void testRemoverProduto() {
		produtoController.removerProduto(idProdutoIncluido - 1);
		
		List<Produto> collect = getProdutoPorId(idProdutoIncluido - 1);
		assertEquals(0, collect.size());
	}

	@Test
	void testGetTodosOsProdutosPor() {
		List<Produto> todosOsProdutosPor = produtoController.getTodosOsProdutosPor("bebida");
		
		assertTrue(!todosOsProdutosPor.isEmpty());
		assertFalse(todosOsProdutosPor.stream()
									  .filter(o -> !o.getTipoProduto().equals(TipoProduto.BEBIDA))
									  .findAny()
									  .isPresent());

		todosOsProdutosPor = produtoController.getTodosOsProdutosPor("categoria inexistente");
		assertTrue(todosOsProdutosPor.isEmpty());
	}

	private List<Produto> getProdutoPorId(Long id) {
		return produtoController.getTodosOsProdutos()
								
								.stream()
								.filter(o -> id.equals(o.getId()))
								.collect(Collectors.toList());
	}
}
