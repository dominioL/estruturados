package br.dominioL.estruturados.json;

public final class TextoJson extends ValorJson {
	String valor;

	protected TextoJson(String valor) {
		this.valor = valor;
	}

	@Override
	public String comoTexto() {
		return valor;
	}

	@Override
	public String comoTextoJson() {
		return String.format("\"%s\"", valor);
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof TextoJson) {
			return valor.equals(((TextoJson) outro).valor);
		}
		return false;
	}
}
