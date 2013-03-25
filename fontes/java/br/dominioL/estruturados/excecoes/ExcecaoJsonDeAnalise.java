package br.dominioL.estruturados.excecoes;

public final class ExcecaoJsonDeAnalise extends RuntimeException {
	private static final long serialVersionUID = 2386048683072552488L;
	
	public ExcecaoJsonDeAnalise() {
		super("Erro na análise do objeto JSON.");
	}
}
