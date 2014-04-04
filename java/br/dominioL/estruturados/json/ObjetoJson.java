package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.Iteravel;

public final class ObjetoJson extends ValorJson implements Iteravel<Par<IdentificadorJson, ValorJson>> {
	private Mapa<IdentificadorJson, ValorJson> elementos;

	protected ObjetoJson() {
		elementos = MapaLista.criar();
	}

	public void inserir(String identificador, ValorJson valor) {
		elementos.inserir(Json.criarIdentificador(identificador), valor);
	}

	public void inserir(IdentificadorJson identificador, ValorJson valor) {
		elementos.inserir(identificador, valor);
	}

	public ValorJson fornecer(String identificador) {
		return elementos.fornecer(Json.criarIdentificador(identificador));
	}

	public ValorJson fornecer(IdentificadorJson identificador) {
		return elementos.fornecer(identificador);
	}

	public Boolean remover(String identificador) {
		return elementos.remover(Json.criarIdentificador(identificador));
	}

	public Boolean remover(IdentificadorJson identificador) {
		return elementos.remover(identificador);
	}

	public void substituir(String identificador, ValorJson valor) {
		elementos.remover(Json.criarIdentificador(identificador));
		elementos.inserir(Json.criarIdentificador(identificador), valor);
	}

	public void substituir(IdentificadorJson identificador, ValorJson valor) {
		elementos.remover(identificador);
		elementos.inserir(identificador, valor);
	}

	@Override
	public Iterador<Par<IdentificadorJson, ValorJson>> fornecerIterador() {
		return elementos.fornecerIterador();
	}

	@Override
	public Iterador<Par<IdentificadorJson, ValorJson>> iterator() {
		return fornecerIterador();
	}

	@Override
	public ObjetoJson comoObjeto() {
		return this;
	}

	@Override
	public String comoTextoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ABERTURA_DE_OBJETO);
		Iterador<Par<IdentificadorJson, ValorJson>> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo()) {
			Par<IdentificadorJson, ValorJson> elemento = iterador.iterarProximo();
			textoJson.append(elemento.fornecerChave().comoTextoJson());
			textoJson.append(elemento.fornecerValor().comoTextoJson());
			if (iterador.possuiProximo()) {
				textoJson.append(SEPARADOR);
			}
		}
		textoJson.append(FECHAMENTO_DE_OBJETO);
		return textoJson.toString();
	}
}