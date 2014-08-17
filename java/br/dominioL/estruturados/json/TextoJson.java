package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Texto;

public final class TextoJson extends ValorJson {
	private Texto valor;

	protected TextoJson(Texto valor) {
		this.valor = valor;
	}

	@Override
	public Texto comoTexto() {
		return valor;
	}

	@Override
	public String comoTextoJson() {
		return String.format("\"%s\"", valor);
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof TextoJson) {
			return valor.igual(((TextoJson) outro).valor);
		}
		return false;
	}
}
