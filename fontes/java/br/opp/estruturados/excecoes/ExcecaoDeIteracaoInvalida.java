package br.opp.estruturados.excecoes;

public final class ExcecaoDeIteracaoInvalida extends RuntimeException {
	private static final long serialVersionUID = -7739980482130050926L;
	
	public ExcecaoDeIteracaoInvalida() {
		super("Operação de iteração inválida. Certifique-se que é possível realizar esta operação neste momento da iteração.");
	}
}

