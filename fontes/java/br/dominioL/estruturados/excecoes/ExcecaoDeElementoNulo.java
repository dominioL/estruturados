package br.dominioL.estruturados.excecoes;

public class ExcecaoDeElementoNulo extends RuntimeException {
	public ExcecaoDeElementoNulo() {
		super("Não é possível inserir elementos nulos");
	}
}
