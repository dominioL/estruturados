package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Comparavel;

public final class Peso implements Comparavel<Peso>, Codificavel {
	private Integer valor;

	private Peso(Integer valor) {
		this.valor = valor;
	}

	@Override
	public Boolean igual(Peso outro) {
		return valor.equals(outro.valor);
	}

	@Override
	public Boolean maiorQue(Peso outro) {
		return (valor.compareTo(outro.valor) > 0);
	}

	@Override
	public Boolean menorQue(Peso outro) {
		return (valor.compareTo(outro.valor) < 0);
	}

	@Override
	public Integer codificar() {
		Integer primo = 31;
		Integer codigo = 1;
		codigo = primo * codigo + ((valor == null) ? 0 : valor.hashCode());
		return codigo;
	}

	public static Peso zero() {
		return new Peso(0);
	}

	public static Peso um() {
		return new Peso(1);
	}

	public static Peso maisInfinito() {
		return new Peso(Integer.MAX_VALUE);
	}

	public static Peso menosInfinito() {
		return new Peso(Integer.MIN_VALUE);
	}

	public static Peso comValor(Integer valor) {
		if (valor.equals(Integer.MAX_VALUE) || valor.equals(Integer.MIN_VALUE)) {
			throw new IllegalArgumentException();
		}
		return new Peso(valor);
	}
}
