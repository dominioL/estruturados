package br.dominioL.estruturados.excecoes;

public class ExcecaoDeChaveNula extends RuntimeException {
	public ExcecaoDeChaveNula() {
		super("Não é possível inserir uma chave nula");
	}
}
