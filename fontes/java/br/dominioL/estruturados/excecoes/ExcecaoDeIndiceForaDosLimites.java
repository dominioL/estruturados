package br.dominioL.estruturados.excecoes;

public class ExcecaoDeIndiceForaDosLimites extends RuntimeException {
	public ExcecaoDeIndiceForaDosLimites() {
		super("Não é possível acessar um indíce fora dos limites");
	}
}
