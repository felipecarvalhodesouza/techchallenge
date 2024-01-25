package br.com.postech.techchallenge.adapters.controller;

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

import br.com.postech.techchallenge.domain.model.Produto;
import br.com.postech.techchallenge.domain.model.TipoProduto;

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

		produtoController.registrarProduto(produto);

		assertNotNull(idProdutoIncluido);
		assertNotEquals(0, idProdutoIncluido);
	}

	@Test
	void testEditarProduto() {
		produto.setId(idProdutoIncluido);
		produto.setNomeProduto("Pepsi");

		produtoController.editarProduto(produto);

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
		List<Produto> todosOsProdutosPor = produtoController.getTodosOsProdutosPor("bebida").getBody();
		
		assertTrue(!todosOsProdutosPor.isEmpty());
		assertFalse(todosOsProdutosPor.stream()
									  .filter(o -> !o.getTipoProduto().equals(TipoProduto.BEBIDA))
									  .findAny()
									  .isPresent());

		todosOsProdutosPor = produtoController.getTodosOsProdutosPor("categoria inexistente").getBody();
		assertTrue(todosOsProdutosPor.isEmpty());
	}

	private List<Produto> getProdutoPorId(Long id) {
		return produtoController.getTodosOsProdutos()
								.getBody()
								.stream()
								.filter(o -> id.equals(o.getId()))
								.collect(Collectors.toList());
	}
}
