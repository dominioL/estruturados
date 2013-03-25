package br.dominioL.estruturados.excecoes;

public class ExcecaoDeIndiceForaDosLimites extends RuntimeException {
	private static final long serialVersionUID = -5812047383801074386L;
	
	public ExcecaoDeIndiceForaDosLimites() {
		super("Não é possível acessar um indíce fora dos limites");
	}
}
