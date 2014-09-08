package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Texto;

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
	public Texto comoTextoJson() {
		return valor.colocarAspas();
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof TextoJson) {
			return valor.igual(((TextoJson) outro).valor);
		}
		return false;
	}

}
