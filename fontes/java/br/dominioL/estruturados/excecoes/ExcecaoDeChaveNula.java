package br.dominioL.estruturados.excecoes;

public class ExcecaoDeChaveNula extends RuntimeException {
	private static final long serialVersionUID = -8146794445815027556L;
	
	public ExcecaoDeChaveNula() {
		super("Não é possível inserir uma chave nula");
	}
}
