package br.dominioL.estruturados.excecoes;

public class ExcecaoDeElementoNulo extends RuntimeException {
	private static final long serialVersionUID = -4791450955768582634L;
	
	public ExcecaoDeElementoNulo() {
		super("Não é possível inserir elementos nulos");
	}
}
