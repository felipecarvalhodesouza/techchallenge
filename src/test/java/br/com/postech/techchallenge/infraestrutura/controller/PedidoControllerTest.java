package br.com.postech.techchallenge.infraestrutura.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.postech.techchallenge.application.usecases.PedidoInteractor;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.ClienteInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInvalidoException;
import br.com.postech.techchallenge.main.security.CognitoUserHelper;

class PedidoControllerTest {

    @Mock
    private PedidoInteractor pedidoInteractor;

    @Mock
    private CognitoUserHelper cognitoUserHelper;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        standaloneSetup(pedidoController);
    }

    @Test
    void deveInserirPedido() throws PedidoInvalidoException, ClienteInexistenteException, MalformedURLException, IOException {
        Pedido pedido = new Pedido();
        pedido.setValorTotal(100.0);
        when(pedidoInteractor.inserir(any(Pedido.class))).thenReturn(pedido);

        Pedido result = given()
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(pedido)
                        .when()
                            .post("/pedidos")
                        .then()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(Pedido.class);

        assertThat(result).isNotNull();
        assertThat(result.getValorTotal()).isEqualTo(100.0);
        verify(pedidoInteractor).inserir(any(Pedido.class));
    }

    @Test
    void deveDeletarPedido() throws PedidoInexistenteException {
        String idPedido = "1";

        doNothing().when(pedidoInteractor).delete(idPedido);

        given()
            .pathParam("idPedido", idPedido)
        .when()
            .delete("/pedidos/{idPedido}")
        .then()
            .statusCode(HttpStatus.OK.value());

        verify(pedidoInteractor).delete(idPedido);
    }
}
