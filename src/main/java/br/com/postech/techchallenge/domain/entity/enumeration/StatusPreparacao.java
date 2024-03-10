package br.com.postech.techchallenge.domain.entity.enumeration;

public enum StatusPreparacao {
	FINALIZADO("Finalizado", null),
	PRONTO("Pronto", FINALIZADO),
	EM_PREPARACAO("Em Preparação", PRONTO),
	RECEBIDO("Recebido", EM_PREPARACAO);
	
	private String descricao;
	private StatusPreparacao proximoStatus;
	
	StatusPreparacao(String descricao, StatusPreparacao proximoStatus) {
		this.descricao = descricao;
		this.proximoStatus = proximoStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public StatusPreparacao getProximoStatus() {
		return proximoStatus;
	}
}
