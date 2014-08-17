package br.dominioL.estruturados.elemento;

public class Texto implements Igualavel<Texto>, Comparavel<Texto>, Codificavel {
	private String valor;

	private Texto(String valor) {
		this.valor = valor;
	}

	public static Texto criar(String valor) {
		return new Texto(valor);
	}

	public static Texto criar() {
		return new Texto("");
	}

	@Override
	public Integer codificar() {
		return valor.hashCode();
	}

	@Override
	public Boolean maiorQue(Texto outro) {
		return valor.compareTo(outro.valor) > 0;
	}

	@Override
	public Boolean menorQue(Texto outro) {
		return valor.compareTo(outro.valor) < 0;
	}

	@Override
	public Boolean igual(Texto outro) {
		return valor.equals(outro.valor);
	}

	public String valor() {
		return valor;
	}
}
