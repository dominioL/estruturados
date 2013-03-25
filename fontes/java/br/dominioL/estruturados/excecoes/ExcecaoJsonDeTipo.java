package br.dominioL.estruturados.excecoes;

public final class ExcecaoJsonDeTipo extends RuntimeException {
	private static final long serialVersionUID = 7370454274353708604L;
	
	public ExcecaoJsonDeTipo() {
		super("O tipo JSON está incorreto.");
	}
}
