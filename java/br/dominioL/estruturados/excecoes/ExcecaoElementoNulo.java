package br.dominioL.estruturados.excecoes;

public class ExcecaoElementoNulo extends RuntimeException {

	private static final long serialVersionUID = -6046183933965310682L;

	public ExcecaoElementoNulo() {
		super("Não é possível inserir elementos nulos");
	}

}
