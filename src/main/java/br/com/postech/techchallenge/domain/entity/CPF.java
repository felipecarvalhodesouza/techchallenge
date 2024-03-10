package br.com.postech.techchallenge.domain.entity;

import br.com.postech.techchallenge.domain.entity.exception.CpfInvalidoException;

public class CPF {

	private String documento;
	
    public CPF(String documento) throws CpfInvalidoException {
		this.documento = documento;
		if(!isDocumentoValido()) {
			throw new CpfInvalidoException();
		}
	}
    
    public CPF(Long documento) throws CpfInvalidoException {
		this.documento = String.valueOf(documento);
		if(!isDocumentoValido()) {
			throw new CpfInvalidoException();
		}
	}
    
    public String getDocumento() {
    	return documento;
    }

	private boolean isDocumentoValido() {
		documento = documento.replaceAll("[^\\d]", "");

        if (documento.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(documento.charAt(i))) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto >= 2) ? (11 - resto) : 0;

        if (digito1 != Integer.parseInt(String.valueOf(documento.charAt(9)))) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(documento.charAt(i))) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto >= 2) ? (11 - resto) : 0;

        return digito2 == Integer.parseInt(String.valueOf(documento.charAt(10)));
    }
}
