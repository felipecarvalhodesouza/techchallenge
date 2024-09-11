package br.com.postech.techchallenge.domain.repository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IPagamentoQueueAdapter {
	
    void publicarPedidoRealizado(String pedidoJson) throws IOException, TimeoutException;
}
