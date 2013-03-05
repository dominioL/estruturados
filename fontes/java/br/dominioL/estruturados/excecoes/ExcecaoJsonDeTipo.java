package br.dominioL.estruturados.excecoes;

public final class ExcecaoJsonDeTipo extends RuntimeException {
	public ExcecaoJsonDeTipo() {
		super("O tipo JSON está incorreto.");
	}
}
