package br.com.postech.techchallenge.domain.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidadorCPFTest {

	@Test
	void testValidarCPFValido() {
		assertTrue(ValidadorCPF.validarCPF("123.456.789-09"));
		assertTrue(ValidadorCPF.validarCPF("111.222.333-96"));
		assertTrue(ValidadorCPF.validarCPF("558.739.180-90"));
		assertTrue(ValidadorCPF.validarCPF("779.732.570-71"));
		assertTrue(ValidadorCPF.validarCPF("164.193.650-90"));
		assertTrue(ValidadorCPF.validarCPF("931.599.580-51"));
	}

	@Test
	void testValidarCPFInvalido() {
		assertFalse(ValidadorCPF.validarCPF("123.456.789-00"));
		assertFalse(ValidadorCPF.validarCPF("111.222.333-97"));
		assertFalse(ValidadorCPF.validarCPF("98765432108"));
		assertFalse(ValidadorCPF.validarCPF("123456789"));
		assertFalse(ValidadorCPF.validarCPF("123456789101"));
		assertFalse(ValidadorCPF.validarCPF("123456789456"));
		assertFalse(ValidadorCPF.validarCPF("123.456.789-0X"));
		assertFalse(ValidadorCPF.validarCPF(""));
		assertFalse(ValidadorCPF.validarCPF("98765432109"));
		assertFalse(ValidadorCPF.validarCPF("12345678910"));
	}

}
