package br.dominioL.estruturados.json;

import br.dominioL.estruturados.colecao.pilha.Pilha;
import br.dominioL.estruturados.colecao.pilha.PilhaLista;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;

public final class Json {
	private final static String ABERTURA_DE_OBJETO = "\\{";
	private final static String FECHAMENTO_DE_OBJETO = "\\}";
	private final static String ABERTURA_DE_LISTA = "\\[";
	private final static String FECHAMENTO_DE_LISTA = "\\]";
	private final static String SEPARADOR = ",";
	private final static String SEPARADOR_DECIMAL = "[.]";
	private final static String DELIMITADOR_TEXTUAL = "\"|'";
	private final static String ESPACO_EM_BRANCO = "\\s";
	private final static String FECHAMENTO_DE_OBJETO_OU_SEPARADOR = "\\}|,";
	private final static String FECHAMENTO_DE_LISTA_OU_SEPARADOR = "\\]|,";
	private final static String FIM_DE_ENTRADA = "\\$";
	private final static String SEPARADOR_DE_IDENTIFICADOR = ":";
	private final static String INICIO_DE_BOOLEANO = "t|f";
	private final static String INICIO_DE_VERDADEIRO = "t";
	private final static String INICIO_DE_FALSO = "f";
	private final static String VERDADEIRO = "true";
	private final static String FALSO = "false";
	private final static String DIGITO = "\\d";
	private StringBuilder entrada;
	private String simboloAtual;
	private Integer posicaoDoSimboloAtual;
	private Pilha<ValorJson> valoresAbertos;
	private Pilha<IdentificadorJson> identificadoresAbertos;

	public Json(String entrada) {
		this.entrada = new StringBuilder(entrada);
		this.entrada.append("$");
		posicaoDoSimboloAtual = 0;
		valoresAbertos = PilhaLista.criar();
		identificadoresAbertos = PilhaLista.criar();
	}

	private ValorJson analisar() {
		consumirSimbolo();
		json();
		consumirEspacos();
		encontrarSimbolo(FIM_DE_ENTRADA);
		if (valoresAbertos.contarElementos() != 1) {
			throw new ExcecaoJsonDeAnalise();
		}
		return valoresAbertos.desempilhar();
	}

	public static ObjetoJson criarObjeto(String entrada) {
		Json json = new Json(entrada);
		return json.analisar().comoObjeto();
	}

	static ObjetoJson criarObjeto() {
		return new ObjetoJson();
	}

	public static ListaJson criarLista(String entrada) {
		Json json = new Json(entrada);
		return json.analisar().comoLista();
	}

	static ListaJson criarLista() {
		return new ListaJson();
	}

	static TextoJson criarTexto(Texto texto) {
		return new TextoJson(texto);
	}

	static BooleanoJson criarBooleano(Booleano booleano) {
		return new BooleanoJson(booleano);
	}

	static NumeroJson criarNumero(Numero numero) {
		return new NumeroJson(numero);
	}

	static IdentificadorJson criarIdentificador(String identificador) {
		return new IdentificadorJson(identificador);
	}

	private void json() {
		consumirEspacos();
		if (simboloAtual.matches(ABERTURA_DE_OBJETO)) {
			consumirSimbolo();
			valoresAbertos.empilhar(Json.criarObjeto());
			objeto();
		} else if (simboloAtual.matches(ABERTURA_DE_LISTA)) {
			consumirSimbolo();
			valoresAbertos.empilhar(Json.criarLista());
			lista();
		} else if (simboloAtual.matches(DELIMITADOR_TEXTUAL)) {
			texto();
		} else if (simboloAtual.matches(INICIO_DE_BOOLEANO)) {
			booleano();
		} else if (simboloAtual.matches(DIGITO)) {
			numero();
		} else if (simboloAtual.matches(FIM_DE_ENTRADA)) {
			throw new ExcecaoJsonDeAnalise();
		} else {
			throw new ExcecaoJsonDeAnalise();
		}
	}

	private void objeto() {
		consumirEspacos();
		while (!simboloAtual.matches(FECHAMENTO_DE_OBJETO)) {
			identificador();
			json();
			IdentificadorJson identificador = identificadoresAbertos.desempilhar();
			ValorJson valor = valoresAbertos.desempilhar();
			valoresAbertos.fornecerDoTopo().comoObjeto().inserir(identificador.comoTexto().valor(), valor);
			consumirEspacos();
			encontrarSimbolo(FECHAMENTO_DE_OBJETO_OU_SEPARADOR);
			consumirSimboloSeFor(SEPARADOR);
		}
		consumirSimbolo();
	}

	private void lista() {
		consumirEspacos();
		while (!simboloAtual.matches(FECHAMENTO_DE_LISTA)) {
			json();
			ValorJson valor = valoresAbertos.desempilhar();
			valoresAbertos.fornecerDoTopo().comoLista().inserir(valor);
			consumirEspacos();
			encontrarSimbolo(FECHAMENTO_DE_LISTA_OU_SEPARADOR);
			consumirSimboloSeFor(SEPARADOR);
		}
		consumirSimbolo();
	}

	private void identificador() {
		consumirEspacos();
		encontrarSimbolo(DELIMITADOR_TEXTUAL);
		String delimitador = simboloAtual;
		StringBuilder identificador = new StringBuilder();
		consumirSimbolo();
		while (!simboloAtual.matches(delimitador)) {
			identificador.append(simboloAtual);
			consumirSimbolo();
		}
		consumirSimbolo();
		consumirEspacos();
		encontrarSimbolo(SEPARADOR_DE_IDENTIFICADOR);
		consumirSimbolo();
		identificadoresAbertos.empilhar(Json.criarIdentificador(identificador.toString()));
	}

	private void texto() {
		String delimitador = simboloAtual;
		StringBuilder texto = new StringBuilder();
		consumirSimbolo();
		while (!simboloAtual.matches(delimitador)) {
			texto.append(simboloAtual);
			consumirSimbolo();
		}
		consumirSimbolo();
		valoresAbertos.empilhar(Json.criarTexto(Texto.criar(texto.toString())));
	}

	private void booleano() {
		if (simboloAtual.matches(INICIO_DE_VERDADEIRO)) {
			encontrarSimboloLongo(VERDADEIRO);
			valoresAbertos.empilhar(Json.criarBooleano(Booleano.verdadeiro()));
		} else if (simboloAtual.matches(INICIO_DE_FALSO)) {
			encontrarSimboloLongo(FALSO);
			valoresAbertos.empilhar(Json.criarBooleano(Booleano.falso()));
		}
	}

	private void numero() {
		StringBuilder numero = new StringBuilder();
		numero.append(sequenciaNumerica());
		if (simboloAtual.matches(SEPARADOR_DECIMAL)) {
			numero.append(simboloAtual);
			consumirSimbolo();
			numero.append(sequenciaNumerica());
		}
		Numero numeroCriado;
		try {
			numeroCriado = Numero.criar(numero.toString());
		} catch (NumberFormatException excecao) {
			throw new ExcecaoJsonDeAnalise();
		}
		valoresAbertos.empilhar(Json.criarNumero(numeroCriado));
	}

	private String sequenciaNumerica() {
		StringBuilder sequencia = new StringBuilder();
		while (simboloAtual.matches(DIGITO)) {
			sequencia.append(simboloAtual);
			consumirSimbolo();
		}
		return sequencia.toString();
	}

	private void encontrarSimboloLongo(String simbolo) {
		Integer posicao = 0;
		while (posicao < simbolo.length()) {
			encontrarSimbolo(simbolo.substring(posicao, posicao + 1));
			consumirSimbolo();
			posicao++;
		}
	}

	private void encontrarSimbolo(String simbolo) {
		if (!simboloAtual.matches(simbolo)) {
			throw new ExcecaoJsonDeAnalise();
		}
	}

	private void consumirSimbolo() {
		if (posicaoDoSimboloAtual >= entrada.length()) {
			throw new ExcecaoJsonDeAnalise();
		}
		simboloAtual = entrada.substring(posicaoDoSimboloAtual, posicaoDoSimboloAtual + 1);
		posicaoDoSimboloAtual++;
	}

	private void consumirSimboloSeFor(String simboloDesejado) {
		if (simboloAtual.matches(simboloDesejado)) {
			consumirSimbolo();
		}
	}

	private void consumirEspacos() {
		while (simboloAtual.matches(ESPACO_EM_BRANCO)) {
			consumirSimbolo();
		}
	}
}
