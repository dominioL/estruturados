package br.dominioL.estruturados.excecoes;

public final class ExcecaoJsonDeAnalise extends RuntimeException {
	public ExcecaoJsonDeAnalise() {
		super("Erro na análise do objeto JSON.");
	}
}
