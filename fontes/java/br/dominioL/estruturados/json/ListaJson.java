package br.dominioL.estruturados.json;

import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.iteracao.Iteravel;
import br.dominioL.estruturados.iteracao.Iterador;

import java.util.Iterator;

public final class ListaJson extends ValorJson implements Iteravel<ValorJson> {
	private ListaPosicional<ValorJson> elementos;

	protected ListaJson() {
		elementos = ListaPosicional.criar();
	}

	public void inserir(ValorJson valor) {
		elementos.inserir(valor);
	}

	public ValorJson fornecer(Integer posicao) {
		return elementos.fornecerDaPosicao(posicao);
	}

	@Override
	public Iterador<ValorJson> fornecerIterador() {
		return elementos.fornecerIterador();
	}

	@Override
	public Iterator<ValorJson> iterator() {
		return fornecerIterador();
	}

	@Override
	public ListaJson comoLista() {
		return this;
	}

	@Override
	public String comoTextoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ABERTURA_DE_LISTA);
		Iterador<ValorJson> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo()) {
			ValorJson elemento = iterador.iterarProximo();
			textoJson.append(elemento.comoTextoJson());
			if (iterador.possuiProximo()) {
				textoJson.append(SEPARADOR);
			}
		}
		textoJson.append(FECHAMENTO_DE_LISTA);
		return textoJson.toString();
	}
}
