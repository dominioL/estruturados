package br.dominioL.estruturados.excecoes;

public final class ExcecaoDeEstruturaVazia extends RuntimeException {
	private static final long serialVersionUID = 100271819078666993L;

	public ExcecaoDeEstruturaVazia() {
		super("Não é possível realizar a operação, pois a estrutura está vazia.");
	}
}
