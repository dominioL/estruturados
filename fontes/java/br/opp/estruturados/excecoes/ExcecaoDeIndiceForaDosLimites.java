package br.opp.estruturados.excecoes;

public class ExcecaoDeIndiceForaDosLimites extends RuntimeException {
	private static final long serialVersionUID = -3097650112770038373L;
	
	public ExcecaoDeIndiceForaDosLimites() {
		super("Não é possível acessar um indíce fora dos limites");
	}
}

