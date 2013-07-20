package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Codificavel;

public final class IdentificadorJson implements Codificavel<IdentificadorJson> {
	private String identificador;

	protected IdentificadorJson(String identificador) {
		this.identificador = identificador;
	}

	public String fornecerComoTextoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ValorJson.DELIMITADOR_TEXTUAL);
		textoJson.append(identificador);
		textoJson.append(ValorJson.DELIMITADOR_TEXTUAL);
		textoJson.append(ValorJson.SEPARADOR_DE_IDENTIFICADOR);
		return textoJson.toString();
	}

	@Override
	public Boolean igual(IdentificadorJson outro) {
		return this.identificador.equals(outro.identificador);
	}

	@Override
	public Integer fornecerCodigo() {
		return identificador.hashCode();
	}
}
