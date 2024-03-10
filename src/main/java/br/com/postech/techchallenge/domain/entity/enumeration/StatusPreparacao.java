package br.com.postech.techchallenge.domain.entity.enumeration;

public enum StatusPreparacao {
	RECEBIDO("Recebido"),
	EM_PREPARACAO("Em Preparação"),
	PRONTO("Pronto"),
	FINALIZADO("Finalizado");
	
	private String descricao;
	
	StatusPreparacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
