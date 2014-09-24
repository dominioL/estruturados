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

	public void inserir(Texto identificador, ValorJson valor) {
		elementos.inserir(Json.criarIdentificador(identificador), valor);
	}

	public void inserir(Texto identificador, Texto valor) {
		elementos.inserir(Json.criarIdentificador(identificador), Json.criarTexto(valor));
	}

	public void inserir(Texto identificador, Numero valor) {
		elementos.inserir(Json.criarIdentificador(identificador), Json.criarNumero(valor));
	}

	public void inserir(Texto identificador, Booleano valor) {
		elementos.inserir(Json.criarIdentificador(identificador), Json.criarBooleano(valor));
	}

	public void inserir(String identificador, ValorJson valor) {
		elementos.inserir(Json.criarIdentificador(Texto.criar(identificador)), valor);
	}

	public void inserir(String identificador, Texto valor) {
		elementos.inserir(Json.criarIdentificador(Texto.criar(identificador)), Json.criarTexto(valor));
	}

	public void inserir(String identificador, Numero valor) {
		elementos.inserir(Json.criarIdentificador(Texto.criar(identificador)), Json.criarNumero(valor));
	}

	public void inserir(String identificador, Booleano valor) {
		elementos.inserir(Json.criarIdentificador(Texto.criar(identificador)), Json.criarBooleano(valor));
	}

	public ValorJson fornecer(Texto identificador) {
		return elementos.fornecer(Json.criarIdentificador(identificador));
	}

	public ValorJson fornecer(String identificador) {
		return elementos.fornecer(Json.criarIdentificador(Texto.criar(identificador)));
	}

	public Booleano contem(Texto identificador) {
		return elementos.contem(Json.criarIdentificador(identificador));
	}

	public Booleano contem(String identificador) {
		return elementos.contem(Json.criarIdentificador(Texto.criar(identificador)));
	}

	public Booleano remover(Texto identificador) {
		return elementos.remover(Json.criarIdentificador(identificador));
	}

	public Booleano remover(String identificador) {
		return elementos.remover(Json.criarIdentificador(Texto.criar(identificador)));
	}

	public void substituir(Texto identificador, ValorJson valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(identificador);
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, valor);
	}

	public void substituir(Texto identificador, Texto valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(identificador);
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, Json.criarTexto(valor));
	}

	public void substituir(Texto identificador, Numero valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(identificador);
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, Json.criarNumero(valor));
	}

	public void substituir(Texto identificador, Booleano valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(identificador);
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, Json.criarBooleano(valor));
	}

	public void substituir(String identificador, ValorJson valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(Texto.criar(identificador));
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, valor);
	}

	public void substituir(String identificador, Texto valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(Texto.criar(identificador));
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, Json.criarTexto(valor));
	}

	public void substituir(String identificador, Numero valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(Texto.criar(identificador));
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, Json.criarNumero(valor));
	}

	public void substituir(String identificador, Booleano valor) {
		IdentificadorJson identificadorJson = Json.criarIdentificador(Texto.criar(identificador));
		elementos.remover(identificadorJson);
		elementos.inserir(identificadorJson, Json.criarBooleano(valor));
	}

	public Numero fornecerQuantidade() {
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
	public Texto comoTextoEmFormatoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ABERTURA_DE_OBJETO);
		Iterador<Par<IdentificadorJson, ValorJson>> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo().avaliar()) {
			Par<IdentificadorJson, ValorJson> elemento = iterador.iterarProximo();
			textoJson.append(elemento.fornecerChave().comoTextoEmFormatoJson().valor());
			textoJson.append(elemento.fornecerValor().comoTextoEmFormatoJson().valor());
			if (iterador.possuiProximo().avaliar()) {
				textoJson.append(SEPARADOR);
			}
		}
		textoJson.append(FECHAMENTO_DE_OBJETO);
		return Texto.criar(textoJson.toString());
	}

	@Override
	public Booleano igual(ValorJson outro) {
		if (outro instanceof ObjetoJson) {
			ObjetoJson outroObjetoJson = (ObjetoJson) outro;
			if (fornecerQuantidade().equals(outroObjetoJson.fornecerQuantidade())) {
				for (Par<IdentificadorJson, ValorJson> par : this) {
					IdentificadorJson identificadorMeu = par.fornecerChave();
					ValorJson valorMeu = par.fornecerValor();
					ValorJson valorDoOutro = outroObjetoJson.fornecer(identificadorMeu.comoTexto().valor());
					if (valorDoOutro == null || !valorMeu.equals(valorDoOutro)) {
						return Booleano.falso();
					}
				}
				return Booleano.verdadeiro();
			}
			return Booleano.falso();
		}
		return Booleano.falso();
	}

}
