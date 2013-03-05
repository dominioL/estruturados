package br.dominioL.estruturados.excecoes;

public final class ExcecaoDeEstruturaVazia extends RuntimeException {
	public ExcecaoDeEstruturaVazia() {
		super("Não é possível realizar a operação, pois a estrutura está vazia.");
	}
}
