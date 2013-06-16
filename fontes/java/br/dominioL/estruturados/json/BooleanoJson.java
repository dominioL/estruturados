package br.dominioL.estruturados.json;

public final class BooleanoJson extends ValorJson {
	Boolean valor;

	protected BooleanoJson(Boolean valor) {
		this.valor = valor;
	}

	@Override
	public Boolean fornecerComoBooleano() {
		return valor;
	}

	@Override
	public String fornecerComoJson() {
		return valor.toString();
	}
}
