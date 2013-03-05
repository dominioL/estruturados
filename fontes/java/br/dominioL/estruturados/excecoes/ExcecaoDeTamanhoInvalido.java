package br.dominioL.estruturados.excecoes;

public class ExcecaoDeTamanhoInvalido extends RuntimeException {
	public ExcecaoDeTamanhoInvalido() {
		super("A estrutura deve possuir tamanho maior que zero.");
	}
}
