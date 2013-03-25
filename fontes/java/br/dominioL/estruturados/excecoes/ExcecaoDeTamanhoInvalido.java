package br.dominioL.estruturados.excecoes;

public class ExcecaoDeTamanhoInvalido extends RuntimeException {
	private static final long serialVersionUID = -4737416108632592184L;
	
	public ExcecaoDeTamanhoInvalido() {
		super("A estrutura deve possuir tamanho maior que zero.");
	}
}
