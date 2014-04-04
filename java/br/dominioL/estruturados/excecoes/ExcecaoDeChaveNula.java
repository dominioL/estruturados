package br.dominioL.estruturados.excecoes;

public class ExcecaoDeChaveNula extends RuntimeException {
	private static final long serialVersionUID = 3552963201109880308L;

	public ExcecaoDeChaveNula() {
		super("Não é possível inserir uma chave nula");
	}
}
