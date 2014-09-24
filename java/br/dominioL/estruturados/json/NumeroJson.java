package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

final class NumeroJson extends ValorJson {

	private Numero valor;

	NumeroJson(Numero valor) {
		this.valor = valor;
	}

	@Override
	public Numero comoNumero() {
		return valor;
	}

	@Override
	public Texto comoTextoEmFormatoJson() {
		return valor.comoTexto();
	}

	@Override
	public Booleano igual(ValorJson outro) {
		if (outro instanceof NumeroJson) {
			return valor.igual(((NumeroJson) outro).valor);
		}
		return Booleano.falso();
	}

}
