package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.domain.entity.exception.PedidoInexistenteException;
import br.com.postech.techchallenge.infraestrutura.helper.HttpHelper;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoRepository;

public class PedidoRepositoryGatewayTest {

    private PedidoRepository pedidoRepositoryMock;
    private PedidoEntityMapper mapperMock;
    private PedidoRepositoryGateway pedidoGateway;
    private HttpHelper httpHelper;

    @BeforeEach
    void setUp() {
        pedidoRepositoryMock = mock(PedidoRepository.class);
        mapperMock = mock(PedidoEntityMapper.class);
        httpHelper = mock(HttpHelper.class);
        pedidoGateway = new PedidoRepositoryGateway(pedidoRepositoryMock, mapperMock, httpHelper);
    }

    @Test
    void testGetPedidosPor() {
        
        long clienteId = 1L;
        PedidoEntity entity = new PedidoEntity();
        List<PedidoEntity> entities = new ArrayList<>();
        entities.add(entity);
        when(pedidoRepositoryMock.getByClienteId(clienteId)).thenReturn(entities);
        when(mapperMock.toDomainObject(entity)).thenReturn(new Pedido());
        
        List<Pedido> result = pedidoGateway.getPedidosPor(String.valueOf(clienteId));
        
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(pedidoRepositoryMock).getByClienteId(clienteId);
        verify(mapperMock).toDomainObject(entity);
    }

    @Test
    void testGetPedidoPor() throws PedidoInexistenteException {
        
        long pedidoId = 1L;
        PedidoEntity entity = new PedidoEntity();
        when(pedidoRepositoryMock.findById(pedidoId)).thenReturn(Optional.of(entity));
        when(mapperMock.toDomainObject(entity)).thenReturn(new Pedido());
        
        Pedido result = pedidoGateway.getPedidoPor(pedidoId);
        
        assertThat(result).isNotNull();
        verify(pedidoRepositoryMock).findById(pedidoId);
        verify(mapperMock).toDomainObject(entity);
    }

    @Test
    void testExcluir() throws PedidoInexistenteException {
        
        long pedidoId = 1L;
        PedidoEntity entity = new PedidoEntity();
        when(pedidoRepositoryMock.findById(pedidoId)).thenReturn(Optional.of(entity));

        pedidoGateway.excluir(pedidoId);

        verify(pedidoRepositoryMock).delete(entity);
        verify(pedidoRepositoryMock).findById(pedidoId);
    }
}
