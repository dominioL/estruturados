package br.dominioL.estruturados.excecoes;

public class ExcecaoDeElementoNulo extends RuntimeException {
	private static final long serialVersionUID = -6046183933965310682L;

	public ExcecaoDeElementoNulo() {
		super("Não é possível inserir elementos nulos");
	}
}
