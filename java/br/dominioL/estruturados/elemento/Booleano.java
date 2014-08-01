package br.dominioL.estruturados.elemento;

public class Booleano implements Igualavel<Booleano>, Comparavel<Booleano>, Codificavel {
	private Boolean valor;

	private Booleano(Boolean valor) {
		this.valor = valor;
	}

	public static Booleano comValor(Boolean valor) {
		return new Booleano(valor);
	}

	public static Booleano verdadeiro() {
		return new Booleano(Boolean.TRUE);
	}

	public static Booleano falso() {
		return new Booleano(Boolean.FALSE);
	}

	@Override
	public Integer codificar() {
		return valor.hashCode();
	}

	@Override
	public Boolean maiorQue(Booleano outro) {
		return valor.compareTo(outro.valor) > 0;
	}

	@Override
	public Boolean menorQue(Booleano outro) {
		return valor.compareTo(outro.valor) < 0;
	}

	@Override
	public Boolean igual(Booleano outro) {
		return valor.equals(outro.valor);
	}

	public Boolean valor() {
		return valor;
	}
}
