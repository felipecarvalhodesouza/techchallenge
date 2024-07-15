package br.com.postech.techchallenge.infraestrutura.gateway.pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.Pedido;
import br.com.postech.techchallenge.infraestrutura.gateway.cliente.ClienteEntityMapper;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.pedido.PedidoEntity;

public class PedidoEntityMapperTest {

    private PedidoEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PedidoEntityMapper();
    }

    @Test
    void testToEntity() {
        
        Pedido pedidoDomain = new Pedido();
        pedidoDomain.setId(1L);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Fulano");
        pedidoDomain.setCliente(cliente);
        
        ClienteEntityMapper clienteEntityMapperMock = mock(ClienteEntityMapper.class);
        ClienteEntity clienteEntity = new ClienteEntity();
        when(clienteEntityMapperMock.toEntity(cliente)).thenReturn(clienteEntity);
        
        PedidoEntity entity = mapper.toEntity(pedidoDomain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
    }

    @Test
    void testToDomainObject() {
        
        PedidoEntity entity = new PedidoEntity();
        entity.setId(1L);

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setNome("Fulano");
        entity.setCliente(clienteEntity);

        
        ClienteEntityMapper clienteEntityMapperMock = mock(ClienteEntityMapper.class);
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Fulano");
        when(clienteEntityMapperMock.toDomainObject(clienteEntity)).thenReturn(cliente);
        
        Pedido pedidoDomain = mapper.toDomainObject(entity);

        assertThat(pedidoDomain).isNotNull();
        assertThat(pedidoDomain.getId()).isEqualTo(1L);
    }
}
