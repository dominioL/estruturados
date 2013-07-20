package br.dominioL.estruturados.json;

public final class TextoJson extends ValorJson {
	String valor;

	protected TextoJson(String valor) {
		this.valor = valor;
	}

	@Override
	public String fornecerComoTexto() {
		return valor;
	}

	@Override
	public String fornecerComoTextoJson() {
		return "\"" + valor + "\"";
	}
}
