package br.com.postech.techchallenge.domain.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;

class CPFTest {

	@Test
	void testCPF() throws CpfInvalidoException {
		
		new CPF("123.456.789-09");
		new CPF("111.222.333-96");
		new CPF("558.739.180-90");
		new CPF("779.732.570-71");
		new CPF("164.193.650-90");
		new CPF("931.599.580-51");
		
		assertThrows(CpfInvalidoException.class, () -> new CPF("123.456.789-00"));
		
		assertThrows(CpfInvalidoException.class, () -> new CPF("123.456.789-00"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("111.222.333-97"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("98765432108"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("123456789"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("123456789101"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("123456789456"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("123.456.789-0X"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("98765432109"));
		assertThrows(CpfInvalidoException.class, () -> new CPF("12345678910"));
	}

}
