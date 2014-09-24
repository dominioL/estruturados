package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class IdentificadorJson implements Igualavel<IdentificadorJson>, Codificavel {

	private Texto identificador;

	protected IdentificadorJson(Texto identificador) {
		this.identificador = identificador;
	}

	public Texto comoTexto() {
		return identificador;
	}

	public Texto comoTextoEmFormatoJson() {
		return identificador.colocarDelimitador(ValorJson.DELIMITADOR_TEXTUAL).concatenar(ValorJson.SEPARADOR_DE_IDENTIFICADOR);
	}

	@Override
	public Booleano igual(IdentificadorJson outro) {
		return this.identificador.igual(outro.identificador);
	}

	@Override
	public Numero codificar() {
		return identificador.codificar();
	}

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof IdentificadorJson) {
			return igual((IdentificadorJson) outro).avaliar();
		}
		return false;
	}

	@Override
	public String toString() {
		return comoTextoEmFormatoJson().valor();
	}

}
