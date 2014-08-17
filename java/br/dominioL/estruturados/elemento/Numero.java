package br.dominioL.estruturados.elemento;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Numero implements Igualavel<Numero>, Comparavel<Numero>, Codificavel {
	private BigDecimal valor;

	private Numero(String valor) {
		this.valor = new BigDecimal(valor);
	}

	private Numero(Integer valor) {
		this.valor = new BigDecimal(valor);
	}

	private Numero(Double valor) {
		this.valor = new BigDecimal(valor);
	}

	public static Numero criar(String valor) {
		return new Numero(valor);
	}

	public static Numero criar(Integer valor) {
		return new Numero(valor);
	}

	public static Numero criar(Double valor) {
		return new Numero(valor);
	}

	public Integer valorInteiro() {
		return valor.intValue();
	}

	public Double valorReal() {
		return valor.doubleValue();
	}

	public String valorMonetario() {
		return new DecimalFormat("0.00").format(valor);
	}

	@Override
	public Integer codificar() {
		return valor.hashCode();
	}

	@Override
	public Boolean maiorQue(Numero outro) {
		return valor.compareTo(outro.valor) > 0;
	}

	@Override
	public Boolean menorQue(Numero outro) {
		return valor.compareTo(outro.valor) < 0;
	}

	@Override
	public Boolean igual(Numero outro) {
		return valor.compareTo(outro.valor) == 0;
	}

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof Numero) {
			return igual((Numero) outro);
		}
		return false;
	}

	@Override
	public String toString() {
		return valor.toString();
	}
}
