package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;

final class TextoJson extends ValorJson {

	private Texto valor;

	TextoJson(Texto valor) {
		this.valor = valor;
	}

	@Override
	public Texto comoTexto() {
		return valor;
	}

	@Override
	public Texto comoTextoEmFormatoJson() {
		return valor.colocarAspas();
	}

	@Override
	public Booleano igual(ValorJson outro) {
		if (outro instanceof TextoJson) {
			return valor.igual(((TextoJson) outro).valor);
		}
		return Booleano.falso();
	}

}
