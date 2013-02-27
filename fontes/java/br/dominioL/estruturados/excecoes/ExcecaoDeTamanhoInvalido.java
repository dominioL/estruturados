package br.dominioL.estruturados.excecoes;

public class ExcecaoDeTamanhoInvalido extends RuntimeException {
	private static final long serialVersionUID = -6586434394357821904L;
	
	public ExcecaoDeTamanhoInvalido() {
		super("A estrutura deve possuir tamanho maior que zero.");
	}
}
