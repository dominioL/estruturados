package br.dominioL.estruturados.json;

import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.iteracao.Iterador;

public final class ListaJson extends ValorJson {
	private ListaPosicional<ValorJson> elementos;

	protected ListaJson() {
		elementos = ListaPosicional.criar();
	}
	
	public void inserir(ValorJson valor) {
		elementos.inserir(valor);
	}
	
	@Override
	public ListaJson fornecerComoLista() {
		return this;
	}
	
	@Override
	public String fornecerComoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ABERTURA_DE_LISTA);
		Iterador<ValorJson> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo()) {
			ValorJson elemento = iterador.iterarProximo();
			textoJson.append(elemento.fornecerComoJson());
			if (iterador.possuiProximo()) {
				textoJson.append(SEPARADOR);
			}
		}
		textoJson.append(FECHAMENTO_DE_LISTA);
		return textoJson.toString();
	}
}
