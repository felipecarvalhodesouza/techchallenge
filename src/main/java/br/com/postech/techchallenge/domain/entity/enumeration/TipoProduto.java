package br.com.postech.techchallenge.domain.entity.enumeration;

public enum TipoProduto {
	LANCHE("Lanche"), BEBIDA("Bebida"), ACOMPANHAMENTO("Acompanhamento");

	private String descricao;

	TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoProduto obterPorDescricao(String descricao) {
		for (TipoProduto valor : TipoProduto.values()) {
			if (valor.getDescricao().equalsIgnoreCase(descricao)) {
				return valor;
			}
		}
		return null;
	}
}