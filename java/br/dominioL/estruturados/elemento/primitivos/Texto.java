package br.dominioL.estruturados.elemento.primitivos;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Comparavel;
import br.dominioL.estruturados.elemento.Igualavel;

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

	public Texto colocarAspas() {
		return colocarDelimitador("\"");
	}

	public Texto colocarDelimitador(String delimitador) {
		return Texto.criar(delimitador + valor + delimitador);
	}

	public Texto colocarDelimitador(Texto delimitador) {
		return colocarDelimitador(delimitador.valor);
	}

	public Texto concatenar(String outro) {
		return Texto.criar(valor + outro);
	}

	public Texto concatenar(Texto outro) {
		return Texto.criar(valor + outro.valor);
	}

	public Texto concatenar(Numero outro) {
		return Texto.criar(valor + outro.comoTexto());
	}

	public Texto formatar(Object... objetos) {
		return Texto.criar(String.format(valor, objetos));
	}

	public Texto substituirPrimeiro(Texto expressaoRegular, Texto substituto) {
		return Texto.criar(valor.replaceFirst(expressaoRegular.valor, substituto.valor));
	}

	public Texto substituirTodos(Texto expressaoRegular, Texto substituto) {
		return Texto.criar(valor.replaceAll(expressaoRegular.valor, substituto.valor));
	}

	public Booleano combinaCom(Texto expressaoRegular) {
		return Booleano.criar(valor.matches(expressaoRegular.valor));
	}

	public Numero tamanho() {
		return Numero.criar(valor.length());
	}

	public Texto particionar(Numero inicio, Numero fim) {
		return Texto.criar(valor.substring(inicio.inteiro(), fim.inteiro()));
	}

	public String valor() {
		return valor;
	}

	@Override
	public Numero codificar() {
		return Numero.criar(valor.hashCode());
	}

	@Override
	public Booleano maiorQue(Texto outro) {
		return Booleano.criar(valor.compareTo(outro.valor) > 0);
	}

	@Override
	public Booleano menorQue(Texto outro) {
		return Booleano.criar(valor.compareTo(outro.valor) < 0);
	}

	@Override
	public Booleano igual(Texto outro) {
		return Booleano.criar(valor.equals(outro.valor));
	}

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof Texto) {
			return igual((Texto) outro).avaliar();
		}
		return false;
	}

	@Override
	public String toString() {
		return valor;
	}

}
