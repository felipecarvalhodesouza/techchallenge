package br.com.postech.techchallenge.infraestrutura.helper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

	public class PasswordGeneratorTest {

	    @Test
	    public void testGeneratePassword() {
	        String password = PasswordGenerator.generatePassword();
	        
	        assertThat(password).hasSize(8);

	        assertThat(password).matches(".*[A-Z].*");

	        assertThat(password).matches(".*[0-9].*");
	        
	        assertThat(password).matches(".*[a-z].*");

	        assertThat(password).matches(".*[!@#$%^&*()\\-_=+<>?].*");
	    }
	}
