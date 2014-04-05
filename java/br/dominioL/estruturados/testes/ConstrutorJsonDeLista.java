package br.dominioL.estruturados.testes;

import java.math.BigDecimal;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.ValorJson;

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
