package br.dominioL.estruturados.json;

import br.dominioL.estruturados.colecao.pilha.Pilha;
import br.dominioL.estruturados.colecao.pilha.PilhaLista;
import br.dominioL.estruturados.elemento.extra.ConstrutorDeTexto;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;

public final class Json {

	private final static Texto ABERTURA_DE_OBJETO = Texto.criar("\\{");
	private final static Texto FECHAMENTO_DE_OBJETO = Texto.criar("\\}");
	private final static Texto ABERTURA_DE_LISTA = Texto.criar("\\[");
	private final static Texto FECHAMENTO_DE_LISTA = Texto.criar("\\]");
	private final static Texto SEPARADOR = Texto.criar(",");
	private final static Texto SEPARADOR_DECIMAL = Texto.criar("[.]");
	private final static Texto DELIMITADOR_TEXTUAL = Texto.criar("\"|'");
	private final static Texto ESPACO_EM_BRANCO = Texto.criar("\\s");
	private final static Texto FECHAMENTO_DE_OBJETO_OU_SEPARADOR = Texto.criar("\\}|,");
	private final static Texto FECHAMENTO_DE_LISTA_OU_SEPARADOR = Texto.criar("\\]|,");
	private final static Texto FIM_DE_ENTRADA = Texto.criar("\\$");
	private final static Texto SEPARADOR_DE_IDENTIFICADOR = Texto.criar(":");
	private final static Texto INICIO_DE_BOOLEANO = Texto.criar("t|f");
	private final static Texto INICIO_DE_VERDADEIRO = Texto.criar("t");
	private final static Texto INICIO_DE_FALSO = Texto.criar("f");
	private final static Texto VERDADEIRO = Texto.criar("true");
	private final static Texto FALSO = Texto.criar("false");
	private final static Texto DIGITO = Texto.criar("\\d");

	private Texto entrada;
	private Texto simboloAtual;
	private Numero posicaoDoSimboloAtual;
	private Pilha<ValorJson> valoresAbertos;
	private Pilha<Texto> identificadoresAbertos;

	private Json(Texto entrada) {
		this.entrada = entrada;
		this.entrada = this.entrada.concatenar("$");
		posicaoDoSimboloAtual = Numero.zero();
		valoresAbertos = PilhaLista.criar();
		identificadoresAbertos = PilhaLista.criar();
	}

	private ValorJson analisar() {
		consumirSimbolo();
		json();
		consumirEspacos();
		encontrarSimbolo(FIM_DE_ENTRADA);
		if (valoresAbertos.contarElementos().igual(Numero.um()).negar().avaliar()) {
			throw new ExcecaoJsonDeAnalise();
		}
		return valoresAbertos.desempilhar();
	}

	public static ObjetoJson criarObjeto(Texto entrada) {
		Json json = new Json(entrada);
		return json.analisar().comoObjeto();
	}

	public static ListaJson criarLista(Texto entrada) {
		Json json = new Json(entrada);
		return json.analisar().comoLista();
	}

	static ObjetoJson criarObjeto() {
		return new ObjetoJson();
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

	static IdentificadorJson criarIdentificador(Texto identificador) {
		return new IdentificadorJson(identificador);
	}

	private void json() {
		consumirEspacos();
		if (simboloAtual.combinaCom(ABERTURA_DE_OBJETO).avaliar()) {
			consumirSimbolo();
			valoresAbertos.empilhar(Json.criarObjeto());
			objeto();
		} else if (simboloAtual.combinaCom(ABERTURA_DE_LISTA).avaliar()) {
			consumirSimbolo();
			valoresAbertos.empilhar(Json.criarLista());
			lista();
		} else if (simboloAtual.combinaCom(DELIMITADOR_TEXTUAL).avaliar()) {
			texto();
		} else if (simboloAtual.combinaCom(INICIO_DE_BOOLEANO).avaliar()) {
			booleano();
		} else if (simboloAtual.combinaCom(DIGITO).avaliar()) {
			numero();
		} else if (simboloAtual.combinaCom(FIM_DE_ENTRADA).avaliar()) {
			throw new ExcecaoJsonDeAnalise();
		} else {
			throw new ExcecaoJsonDeAnalise();
		}
	}

	private void objeto() {
		consumirEspacos();
		while (simboloAtual.combinaCom(FECHAMENTO_DE_OBJETO).negar().avaliar()) {
			identificador();
			json();
			Texto identificador = identificadoresAbertos.desempilhar();
			ValorJson valor = valoresAbertos.desempilhar();
			valoresAbertos.fornecerDoTopo().comoObjeto().inserir(identificador, valor);
			consumirEspacos();
			encontrarSimbolo(FECHAMENTO_DE_OBJETO_OU_SEPARADOR);
			consumirSimboloSeFor(SEPARADOR);
		}
		consumirSimbolo();
	}

	private void lista() {
		consumirEspacos();
		while (simboloAtual.combinaCom(FECHAMENTO_DE_LISTA).negar().avaliar()) {
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
		Texto delimitador = simboloAtual;
		ConstrutorDeTexto identificador = new ConstrutorDeTexto();
		consumirSimbolo();
		while (simboloAtual.combinaCom(delimitador).negar().avaliar()) {
			identificador.anexar(simboloAtual);
			consumirSimbolo();
		}
		consumirSimbolo();
		consumirEspacos();
		encontrarSimbolo(SEPARADOR_DE_IDENTIFICADOR);
		consumirSimbolo();
		identificadoresAbertos.empilhar(identificador.construir());
	}

	private void texto() {
		Texto delimitador = simboloAtual;
		ConstrutorDeTexto texto = new ConstrutorDeTexto();
		consumirSimbolo();
		while (simboloAtual.combinaCom(delimitador).negar().avaliar()) {
			texto.anexar(simboloAtual);
			consumirSimbolo();
		}
		consumirSimbolo();
		valoresAbertos.empilhar(Json.criarTexto(texto.construir()));
	}

	private void booleano() {
		if (simboloAtual.combinaCom(INICIO_DE_VERDADEIRO).avaliar()) {
			encontrarSimboloLongo(VERDADEIRO);
			valoresAbertos.empilhar(Json.criarBooleano(Booleano.verdadeiro()));
		} else if (simboloAtual.combinaCom(INICIO_DE_FALSO).avaliar()) {
			encontrarSimboloLongo(FALSO);
			valoresAbertos.empilhar(Json.criarBooleano(Booleano.falso()));
		}
	}

	private void numero() {
		ConstrutorDeTexto numero = new ConstrutorDeTexto();
		numero.anexar(sequenciaNumerica());
		if (simboloAtual.combinaCom(SEPARADOR_DECIMAL).avaliar()) {
			numero.anexar(simboloAtual);
			consumirSimbolo();
			numero.anexar(sequenciaNumerica());
		}
		Numero numeroCriado;
		try {
			numeroCriado = Numero.criar(numero.construir());
		} catch (NumberFormatException excecao) {
			throw new ExcecaoJsonDeAnalise();
		}
		valoresAbertos.empilhar(Json.criarNumero(numeroCriado));
	}

	private Texto sequenciaNumerica() {
		ConstrutorDeTexto sequencia = new ConstrutorDeTexto();
		while (simboloAtual.combinaCom(DIGITO).avaliar()) {
			sequencia.anexar(simboloAtual);
			consumirSimbolo();
		}
		return sequencia.construir();
	}

	private void encontrarSimboloLongo(Texto simbolo) {
		Numero posicao = Numero.zero();
		while (posicao.menorQue(simbolo.tamanho()).avaliar()) {
			Numero novaPosicao = posicao.incrementar();
			encontrarSimbolo(simbolo.particionar(posicao, novaPosicao));
			consumirSimbolo();
			posicao = novaPosicao;
		}
	}

	private void encontrarSimbolo(Texto simbolo) {
		if (simboloAtual.combinaCom(simbolo).negar().avaliar()) {
			throw new ExcecaoJsonDeAnalise();
		}
	}

	private void consumirSimbolo() {
		if (posicaoDoSimboloAtual.maiorOuIgualQue(entrada.tamanho()).avaliar()) {
			throw new ExcecaoJsonDeAnalise();
		}
		Numero novaPosicao = posicaoDoSimboloAtual.incrementar();
		simboloAtual = entrada.particionar(posicaoDoSimboloAtual, novaPosicao);
		posicaoDoSimboloAtual = novaPosicao;
	}

	private void consumirSimboloSeFor(Texto simboloDesejado) {
		if (simboloAtual.combinaCom(simboloDesejado).avaliar()) {
			consumirSimbolo();
		}
	}

	private void consumirEspacos() {
		while (simboloAtual.combinaCom(ESPACO_EM_BRANCO).avaliar()) {
			consumirSimbolo();
		}
	}

}
