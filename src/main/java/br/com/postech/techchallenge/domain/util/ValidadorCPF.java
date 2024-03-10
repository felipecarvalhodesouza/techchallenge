package br.com.postech.techchallenge.domain.util;

public class ValidadorCPF {
	
	private ValidadorCPF() {
		throw new IllegalStateException("Classe de utilitários");
	}

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto >= 2) ? (11 - resto) : 0;

        if (digito1 != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto >= 2) ? (11 - resto) : 0;

        return digito2 == Integer.parseInt(String.valueOf(cpf.charAt(10)));
    }
}

