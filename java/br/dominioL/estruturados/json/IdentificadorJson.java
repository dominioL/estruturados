package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class IdentificadorJson implements Igualavel<IdentificadorJson>, Codificavel {

	private Texto identificador;

	protected IdentificadorJson(String identificador) {
		this.identificador = Texto.criar(identificador);
	}

	public Texto comoTexto() {
		return identificador;
	}

	public Texto comoTextoJson() {
		return identificador.colocarDelimitador(ValorJson.DELIMITADOR_TEXTUAL).concatenar(ValorJson.SEPARADOR_DE_IDENTIFICADOR);
	}

	@Override
	public Boolean igual(IdentificadorJson outro) {
		return this.identificador.igual(outro.identificador);
	}

	@Override
	public Integer codificar() {
		return identificador.codificar();
	}

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof IdentificadorJson) {
			return igual((IdentificadorJson) outro);
		}
		return false;
	}

	@Override
	public String toString() {
		return comoTextoJson().valor();
	}

}
