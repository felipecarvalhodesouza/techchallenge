package br.com.postech.techchallenge.infraestrutura.queue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.com.postech.techchallenge.domain.repository.IPagamentoQueueAdapter;

public class PagamentoQueueAdapter implements IPagamentoQueueAdapter {

	private final static String QUEUE_NAME = "mensageria_pedidos";
	
    @Value("${spring.rabbitmq.host}")
    private String rabbitmqhost;
    
    @Value("${spring.rabbitmq.port}")
    private String rabbitmqport;
	
	@Override
	public void publicarPedidoRealizado(String pedidoJson) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitmqhost);
        factory.setPort(Integer.valueOf(rabbitmqport));

        try (Connection connection = factory.newConnection(); 
             Channel channel = connection.createChannel()) {
             
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            channel.basicPublish("", QUEUE_NAME, 
                                 MessageProperties.PERSISTENT_TEXT_PLAIN, 
                                 pedidoJson.getBytes());
        }
    }

}
