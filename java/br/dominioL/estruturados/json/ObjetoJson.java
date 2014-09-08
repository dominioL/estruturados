package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.Iteravel;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;

public final class ObjetoJson extends ValorJson implements Iteravel<Par<IdentificadorJson, ValorJson>> {
	private Mapa<IdentificadorJson, ValorJson> elementos;

	protected ObjetoJson() {
		elementos = MapaLista.criar();
	}

	public void inserir(String identificador, ValorJson valor) {
		elementos.inserir(Json.criarIdentificador(identificador), valor);
	}

	public void inserir(String identificador, Texto valor) {
		elementos.inserir(Json.criarIdentificador(identificador), Json.criarTexto(valor));
	}

	public void inserir(String identificador, Numero valor) {
		elementos.inserir(Json.criarIdentificador(identificador), Json.criarNumero(valor));
	}

	public void inserir(String identificador, Booleano valor) {
		elementos.inserir(Json.criarIdentificador(identificador), Json.criarBooleano(valor));
	}

	public ValorJson fornecer(String identificador) {
		return elementos.fornecer(Json.criarIdentificador(identificador));
	}

	public Boolean contem(String identificador) {
		return elementos.contem(Json.criarIdentificador(identificador));
	}

	public Boolean remover(String identificador) {
		return elementos.remover(Json.criarIdentificador(identificador));
	}

	public void substituir(String identificador, ValorJson valor) {
		elementos.remover(Json.criarIdentificador(identificador));
		elementos.inserir(Json.criarIdentificador(identificador), valor);
	}

	public Integer fornecerQuantidade() {
		return elementos.contarElementos();
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
	public Texto comoTextoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ABERTURA_DE_OBJETO);
		Iterador<Par<IdentificadorJson, ValorJson>> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo()) {
			Par<IdentificadorJson, ValorJson> elemento = iterador.iterarProximo();
			textoJson.append(elemento.fornecerChave().comoTextoJson().valor());
			textoJson.append(elemento.fornecerValor().comoTextoJson().valor());
			if (iterador.possuiProximo()) {
				textoJson.append(SEPARADOR);
			}
		}
		textoJson.append(FECHAMENTO_DE_OBJETO);
		return Texto.criar(textoJson.toString());
	}

	@Override
	public Boolean igual(ValorJson outro) {
		if (outro instanceof ObjetoJson) {
			ObjetoJson outroObjetoJson = (ObjetoJson) outro;
			if (fornecerQuantidade().equals(outroObjetoJson.fornecerQuantidade())) {
				for (Par<IdentificadorJson, ValorJson> par : this) {
					IdentificadorJson identificadorMeu = par.fornecerChave();
					ValorJson valorMeu = par.fornecerValor();
					ValorJson valorDoOutro = outroObjetoJson.fornecer(identificadorMeu.comoTexto().valor());
					if (valorDoOutro == null || !valorMeu.equals(valorDoOutro)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return false;
	}
}
