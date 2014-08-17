package br.dominioL.estruturados.json;

public abstract class ConstrutorJson<T extends ValorJson> {
	public final static ConstrutorJsonDeObjeto deObjeto() {
		return new ConstrutorJsonDeObjeto();
	}

	public final static ConstrutorJsonDeLista deLista() {
		return new ConstrutorJsonDeLista();
	}

	public abstract T construir();
}
