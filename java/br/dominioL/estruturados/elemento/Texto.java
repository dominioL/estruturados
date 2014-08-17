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

	public String valor() {
		return valor;
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

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof Texto) {
			return igual((Texto) outro);
		}
		return false;
	}

	@Override
	public String toString() {
		return valor;
	}
}
