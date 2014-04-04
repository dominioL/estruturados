package br.dominioL.estruturados.json;

public final class BooleanoJson extends ValorJson {
	Boolean valor;

	protected BooleanoJson(Boolean valor) {
		this.valor = valor;
	}

	@Override
	public Boolean comoBooleano() {
		return valor;
	}

	@Override
	public String comoTextoJson() {
		return valor.toString();
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof BooleanoJson) {
			return valor.equals(((BooleanoJson) outro).valor);
		}
		return false;
	}
}
