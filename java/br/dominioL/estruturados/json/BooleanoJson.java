package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Booleano;

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
	public String comoTextoJson() {
		return valor.toString();
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof BooleanoJson) {
			return valor.igual(((BooleanoJson) outro).valor);
		}
		return false;
	}
}
