package br.dominioL.estruturados.excecoes;

public final class ExcecaoDeIteracaoInvalida extends RuntimeException {
	public ExcecaoDeIteracaoInvalida() {
		super("Operação de iteração inválida. Certifique-se que é possível realizar esta operação neste momento da iteração.");
	}
}
