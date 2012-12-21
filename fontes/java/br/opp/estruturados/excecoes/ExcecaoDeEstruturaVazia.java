package br.opp.estruturados.excecoes;

public final class ExcecaoDeEstruturaVazia extends RuntimeException {
	private static final long serialVersionUID = -7313659671771465232L;
	
	public ExcecaoDeEstruturaVazia() {
		super("Não é possível realizar a operação, pois a estrutura está vazia.");
	}
}

