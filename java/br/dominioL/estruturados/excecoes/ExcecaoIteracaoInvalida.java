package br.dominioL.estruturados.excecoes;

public final class ExcecaoIteracaoInvalida extends RuntimeException {
	private static final long serialVersionUID = 444522828921716217L;

	public ExcecaoIteracaoInvalida() {
		super("Operação de iteração inválida. Certifique-se que é possível realizar esta operação neste momento da iteração.");
	}
}
