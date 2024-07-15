package br.com.postech.techchallenge.infraestrutura.helper;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import br.com.postech.techchallenge.domain.entity.Pedido;

public class SQSHelper {

	public void enviarMensagem(Pedido pedido) {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withEndpointConfiguration(new EndpointConfiguration("https://sqs.us-east-1.amazonaws.com", "us-east-1"))
                .build();

        String queueUrl = "https://sqs.us-east-1.amazonaws.com/615687076434/techchallenge-pagamento-sqs";

		String json = String.format("{\"id\": \"%s\", \"valorTotal\": \"%d\"}", String.valueOf(pedido.getId()), pedido.getValorTotal());

        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(json);

        sqs.sendMessage(sendMsgRequest);
	}
}
