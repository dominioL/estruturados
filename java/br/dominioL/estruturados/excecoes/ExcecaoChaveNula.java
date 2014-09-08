package br.dominioL.estruturados.excecoes;

public class ExcecaoChaveNula extends RuntimeException {

	private static final long serialVersionUID = 3552963201109880308L;

	public ExcecaoChaveNula() {
		super("Não é possível inserir uma chave nula");
	}

}
