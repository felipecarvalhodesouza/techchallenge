package br.com.postech.techchallenge.ports;

public interface RegistraClienteInputPort {

	void registraCliente(String nome, String email, long cpf);
}
