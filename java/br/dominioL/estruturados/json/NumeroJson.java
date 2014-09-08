package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class NumeroJson extends ValorJson {

	private Numero valor;

	protected NumeroJson(Numero valor) {
		this.valor = valor;
	}

	@Override
	public Numero comoNumero() {
		return valor;
	}

	@Override
	public Texto comoTextoJson() {
		return valor.comoTexto();
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof NumeroJson) {
			return valor.igual(((NumeroJson) outro).valor);
		}
		return false;
	}

}
