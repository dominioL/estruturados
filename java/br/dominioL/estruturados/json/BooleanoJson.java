package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;

final class BooleanoJson extends ValorJson {

	private Booleano valor;

	BooleanoJson(Booleano valor) {
		this.valor = valor;
	}

	@Override
	public Booleano comoBooleano() {
		return valor;
	}

	@Override
	public Texto comoTextoEmFormatoJson() {
		return valor.comoTexto();
	}

	@Override
	public Booleano igual(ValorJson outro) {
		if (outro instanceof BooleanoJson) {
			return valor.igual(((BooleanoJson) outro).valor);
		}
		return Booleano.falso();
	}

}
