package br.dominioL.estruturados.json;

import java.math.BigDecimal;

public final class ConstrutorJsonDeLista extends ConstrutorJson<ListaJson> {
	private ListaJson lista;

	protected ConstrutorJsonDeLista() {
		lista = Json.criarLista();
	}

	public ListaJson construir() {
		return lista;
	}

	public ConstrutorJsonDeLista inserir(ValorJson valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(String valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(BigDecimal valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(Integer valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(Double valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(Boolean valor) {
		lista.inserir(valor);
		return this;
	}
}
