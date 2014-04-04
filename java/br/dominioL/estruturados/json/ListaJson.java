package br.dominioL.estruturados.json;

import java.math.BigDecimal;
import java.util.Iterator;

import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.Iteravel;

public final class ListaJson extends ValorJson implements Iteravel<ValorJson> {
	private ListaPosicional<ValorJson> elementos;

	protected ListaJson() {
		elementos = ListaPosicional.criar();
	}

	public void inserir(ValorJson valor) {
		elementos.inserir(valor);
	}

	public void inserir(String valor) {
		elementos.inserir(Json.criarTexto(valor));
	}

	public void inserir(BigDecimal valor) {
		elementos.inserir(Json.criarNumero(valor));
	}

	public void inserir(Integer valor) {
		elementos.inserir(Json.criarNumero(valor));
	}

	public void inserir(Double valor) {
		elementos.inserir(Json.criarNumero(valor));
	}

	public void inserir(Boolean valor) {
		elementos.inserir(Json.criarBooleano(valor));
	}

	public ValorJson fornecer(Integer posicao) {
		return elementos.fornecerDaPosicao(posicao);
	}

	public Integer fornecerQuantidade() {
		return elementos.fornecerQuantidade();
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

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof ListaJson) {
			Integer quatidade = fornecerQuantidade();
			ListaJson outroListaJson = (ListaJson) outro;
			if (quatidade.equals(outroListaJson.fornecerQuantidade())) {
				Integer contador = 0;
				while (contador < quatidade) {
					ValorJson meuElemento = fornecer(contador);
					ValorJson outroElemento = outroListaJson.fornecer(contador);
					if (!meuElemento.equals(outroElemento)) {
						return false;
					}
					contador++;
				}
				return true;
			}
			return false;
		}
		return false;
	}
}
