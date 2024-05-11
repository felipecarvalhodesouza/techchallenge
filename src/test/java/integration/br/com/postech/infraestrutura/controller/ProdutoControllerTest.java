package integration.br.com.postech.infraestrutura.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.postech.techchallenge.TechchallengeApplication;
import br.com.postech.techchallenge.domain.entity.Produto;
import br.com.postech.techchallenge.domain.entity.enumeration.TipoProduto;
import br.com.postech.techchallenge.infraestrutura.controller.ProdutoController;
import br.com.postech.techchallenge.main.ProdutoConfig;

@SpringBootTest(classes = {TechchallengeApplication.class, ProdutoConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdutoControllerTest {

	private Produto produto;

	@Autowired
	private ProdutoController produtoController;

	@BeforeEach
	private void setup() {
		produto = new Produto();
	}

	@Test @Order(1)
	void testRegistrarProduto() {
		produto.setNomeProduto("Coquinha gelada");
		produto.setValor(5);
		produto.setTipoProduto(TipoProduto.BEBIDA);

		Produto produtoInserido = produtoController.registrar(produto);

		assertNotNull(produtoInserido.getId());
		assertNotEquals(0, produtoInserido.getId());
	}

	@Test @Order(2)
	void testEditarProduto() {
		Produto produtoInserido = produtoController.getProdutoPorNome("Coquinha gelada");
		
		produto.setId(produtoInserido.getId());
		produto.setNomeProduto("Pepsi");

		produtoController.editar(produto);

		List<Produto> collect = getProdutoPorId(produtoInserido.getId());

		assertEquals(1, collect.size());
		assertEquals("Pepsi", collect.get(0).getNomeProduto());
	}

	@Test @Order(3)
	void testRemoverProduto() {
		Produto produtoInserido = produtoController.getProdutoPorNome("Pepsi");
		
		produtoController.removerProduto(produtoInserido.getId());
		
		List<Produto> collect = getProdutoPorId(produtoInserido.getId());
		assertEquals(0, collect.size());
	}

	@Test @Order(4)
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
