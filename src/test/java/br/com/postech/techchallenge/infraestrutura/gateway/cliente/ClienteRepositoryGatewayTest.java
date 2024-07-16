package br.com.postech.techchallenge.infraestrutura.gateway.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.postech.techchallenge.application.gateway.ClienteGateway;
import br.com.postech.techchallenge.domain.entity.Cliente;
import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;
import br.com.postech.techchallenge.infraestrutura.helper.HttpHelper;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteEntity;
import br.com.postech.techchallenge.infraestrutura.persistence.cliente.ClienteRepository;

public class ClienteRepositoryGatewayTest {

    private ClienteRepository clienteRepositoryMock;
    private ClienteEntityMapper mapperMock;
    private ClienteGateway clienteGateway;
    private HttpHelper httpHelper;

    @BeforeEach
    void setUp() {
        clienteRepositoryMock = mock(ClienteRepository.class);
        mapperMock = mock(ClienteEntityMapper.class);
        httpHelper = mock(HttpHelper.class);
        clienteGateway = new ClienteRepositoryGateway(clienteRepositoryMock, mapperMock, httpHelper);
    }

    @Test
    void testBuscarPorCpf() throws CpfInvalidoException {
        
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678909");
        ClienteEntity entity = new ClienteEntity();
        when(clienteRepositoryMock.getByCpf(12345678909L)).thenReturn(entity);
        when(mapperMock.toDomainObject(entity)).thenReturn(cliente);

        
        Cliente result = clienteGateway.buscarPorCpf(cliente);

        
        assertThat(result).isNotNull();
        assertThat(result.getCpf()).isEqualTo("12345678909");
        verify(clienteRepositoryMock).getByCpf(12345678909L);
        verify(mapperMock).toDomainObject(entity);
    }

    @Test
    void testRegistrar() throws IOException {
        
        Cliente cliente = new Cliente();
        cliente.setEmail("fulano@test.com");
        ClienteEntity entity = new ClienteEntity();
        when(clienteRepositoryMock.save(any(ClienteEntity.class))).thenReturn(entity);
        when(mapperMock.toEntity(cliente)).thenReturn(new ClienteEntity());
        when(mapperMock.toDomainObject(any(ClienteEntity.class))).thenReturn(cliente);
        
        Cliente result = clienteGateway.registrar(cliente);

        
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("fulano@test.com");
        verify(clienteRepositoryMock).save(any(ClienteEntity.class));
        verify(mapperMock).toEntity(cliente);
    }

    
}
