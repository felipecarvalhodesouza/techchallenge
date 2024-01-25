package br.com.postech.techchallenge.domain.model;

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
	
	public static StatusPreparacao obterPorDescricao(String descricao) {
		for (StatusPreparacao valor : StatusPreparacao.values()) {
			if (valor.getDescricao().equalsIgnoreCase(descricao)) {
				return valor;
			}
		}
		return null;
	}
}
