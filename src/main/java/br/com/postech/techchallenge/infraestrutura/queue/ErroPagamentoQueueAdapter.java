package br.com.postech.techchallenge.infraestrutura.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.postech.techchallenge.main.config.RabbitMQConfig;

@Service
public class ErroPagamentoQueueAdapter {

	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void receiveMessage(String message) {
		
	System.out.println("Mensagem recebida: " + message);
	/**
	 * Implementar logica de notificao -> API Whatsapp, email, etc
	 */
	}
}
