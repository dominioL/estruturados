package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeTipo;

public abstract class ValorJson implements Igualavel<ValorJson> {
	protected static final Texto ABERTURA_DE_OBJETO = Texto.criar("{");
	protected static final Texto FECHAMENTO_DE_OBJETO = Texto.criar("}");
	protected static final Texto ABERTURA_DE_LISTA = Texto.criar("[");
	protected static final Texto FECHAMENTO_DE_LISTA = Texto.criar("]");
	protected final static Texto SEPARADOR = Texto.criar(", ");
	protected final static Texto DELIMITADOR_TEXTUAL = Texto.criar("\"");
	protected final static Texto SEPARADOR_DE_IDENTIFICADOR = Texto.criar(": ");

	public final ValorJson comoJson() {
		return this;
	}

	public Texto comoTexto() {
		throw new ExcecaoJsonDeTipo();
	}

	public Numero comoNumero() {
		throw new ExcecaoJsonDeTipo();
	}

	public Booleano comoBooleano() {
		throw new ExcecaoJsonDeTipo();
	}

	public ObjetoJson comoObjeto() {
		throw new ExcecaoJsonDeTipo();
	}

	public ListaJson comoLista() {
		throw new ExcecaoJsonDeTipo();
	}

	public abstract Texto comoTextoJson();

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof ValorJson) {
			return igual((ValorJson) outro);
		}
		return false;
	}

	@Override
	public final String toString() {
		return comoTextoJson().valor();
	}
}
