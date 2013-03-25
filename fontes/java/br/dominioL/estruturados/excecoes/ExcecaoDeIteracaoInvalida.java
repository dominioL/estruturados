package br.dominioL.estruturados.excecoes;

public final class ExcecaoDeIteracaoInvalida extends RuntimeException {
	private static final long serialVersionUID = 444522828921716217L;
	
	public ExcecaoDeIteracaoInvalida() {
		super("Operação de iteração inválida. Certifique-se que é possível realizar esta operação neste momento da iteração.");
	}
}
