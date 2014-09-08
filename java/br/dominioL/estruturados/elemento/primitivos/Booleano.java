package br.dominioL.estruturados.elemento.primitivos;

import br.dominioL.estruturados.elemento.Igualavel;

public class Booleano implements Igualavel<Booleano> {

	private static final Booleano VERDADEIRO =  new Booleano(Boolean.TRUE);
	private static final Booleano FALSO = new Booleano(Boolean.FALSE);
	
	private Boolean valor;

	private Booleano(Boolean valor) {
		this.valor = valor;
	}

	public static Booleano criar(Boolean valor) {
		return new Booleano(valor);
	}

	public static Booleano verdadeiro() {
		return VERDADEIRO;
	}

	public static Booleano falso() {
		return FALSO;
	}

	public static Booleano nulo(Object objeto) {
		return new Booleano(objeto == null);
	}

	public static Booleano mesmo(Object primeiro, Object segundo) {
		return new Booleano(primeiro == segundo);
	}

	public static Booleano iguais(Object primeiro, Object segundo) {
		return new Booleano(primeiro.equals(segundo));
	}

	public Booleano e(Booleano outro) {
		Boolean valorDoOutro = outro.valor;
		return criar(valor && valorDoOutro);
	}

	public Booleano ou(Booleano outro) {
		Boolean valorDoOutro = outro.valor;
		return criar(valor || valorDoOutro);
	}

	public Booleano negar() {
		return criar(!valor);
	}

	public Boolean avaliar() {
		return valor;
	}

	public Texto comoTexto() {
		return Texto.criar(valor.toString());
	}

	@Override
	public Booleano igual(Booleano outro) {
		if (outro != null) {
			return Booleano.criar(valor.equals(outro.valor));
		}
		return Booleano.falso();
	}

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof Booleano && outro != null) {
			return valor.equals(((Booleano) outro).valor);
		}
		return false;
	}

	@Override
	public String toString() {
		return valor.toString();
	}

}
