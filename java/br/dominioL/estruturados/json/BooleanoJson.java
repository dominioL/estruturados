package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class BooleanoJson extends ValorJson {

	private Booleano valor;

	protected BooleanoJson(Booleano valor) {
		this.valor = valor;
	}

	@Override
	public Booleano comoBooleano() {
		return valor;
	}

	@Override
	public Texto comoTextoJson() {
		return valor.comoTexto();
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof BooleanoJson) {
			return valor.igual(((BooleanoJson) outro).valor);
		}
		return false;
	}

}
