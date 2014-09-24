package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

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

	public ConstrutorJsonDeLista inserir(Texto valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(Numero valor) {
		lista.inserir(valor);
		return this;
	}

	public ConstrutorJsonDeLista inserir(Booleano valor) {
		lista.inserir(valor);
		return this;
	}

}
