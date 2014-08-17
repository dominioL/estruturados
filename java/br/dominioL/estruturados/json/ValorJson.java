package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.Numero;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeTipo;

public abstract class ValorJson implements Igualavel<ValorJson> {
	protected static final String ABERTURA_DE_OBJETO = "{";
	protected static final String FECHAMENTO_DE_OBJETO = "}";
	protected static final String ABERTURA_DE_LISTA = "[";
	protected static final String FECHAMENTO_DE_LISTA = "]";
	protected final static String SEPARADOR = ", ";
	protected final static String DELIMITADOR_TEXTUAL = "\"";
	protected final static String SEPARADOR_DE_IDENTIFICADOR = ": ";

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

	public abstract String comoTextoJson();

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof ValorJson) {
			return igual((ValorJson) outro);
		}
		return false;
	}

	@Override
	public String toString() {
		return comoTextoJson();
	}
}
