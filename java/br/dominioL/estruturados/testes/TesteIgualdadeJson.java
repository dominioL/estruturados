package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.BooleanoJson;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.NumeroJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;
import br.dominioL.estruturados.json.ValorJson;

public final class TesteIgualdadeJson {
	private ValorJson objetoVazio;
	private ValorJson outroObjetoVazio;
	private ValorJson objetoComTextoLucasBooleanoFalsoNumeroZero;
	private ValorJson objetoComTextinhoLucasBooleanoFalsoNumeroZero;
	private ValorJson objetoComTextoLucasBooleanoFalsoNumeroUm;
	private ValorJson listaVazia;
	private ValorJson outraListaVazia;
	private ValorJson listaComUmElemento;
	private ValorJson listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro;
	private ValorJson listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso;
	private ValorJson listaComTextoLucasENumeroUm;
	private ValorJson listaComNumeroUmETextoLucas;
	private ValorJson textoVazio;
	private ValorJson outroTextoVazio;
	private ValorJson textoLucas;
	private ValorJson numeroZero;
	private ValorJson numeroUm;
	private ValorJson booleanoFalso;
	private ValorJson booleanoVerdadeiro;
	private ValorJson outroBooleanoVerdadeiro;

	@Before
	public void criarFiguracao() {
		objetoVazio = Json.criarObjeto();
		outroObjetoVazio = Json.criarObjeto();
		listaVazia = Json.criarLista();
		outraListaVazia = Json.criarLista();
		listaComUmElemento = Json.criarLista();
		listaComUmElemento.comoLista().igual(Json.criarNumero(Numero.criar(10)));
		textoVazio = Json.criarTexto(Texto.criar());
		outroTextoVazio = Json.criarTexto(Texto.criar());
		textoLucas = Json.criarTexto(Texto.criar("Lucas"));
		numeroZero = Json.criarNumero(Numero.criar(0));
		numeroUm = Json.criarNumero(Numero.criar(1));
		booleanoFalso = Json.criarBooleano(Booleano.falso());
		booleanoVerdadeiro = Json.criarBooleano(Booleano.verdadeiro());
		outroBooleanoVerdadeiro = Json.criarBooleano(Booleano.verdadeiro());

		objetoComTextoLucasBooleanoFalsoNumeroZero = Json.criarObjeto();
		objetoComTextoLucasBooleanoFalsoNumeroZero.comoObjeto().inserir("texto", textoLucas);
		objetoComTextoLucasBooleanoFalsoNumeroZero.comoObjeto().inserir("booleano", booleanoFalso);
		objetoComTextoLucasBooleanoFalsoNumeroZero.comoObjeto().inserir("numero", numeroZero);
		objetoComTextinhoLucasBooleanoFalsoNumeroZero = Json.criarObjeto();
		objetoComTextinhoLucasBooleanoFalsoNumeroZero.comoObjeto().inserir("textinho", textoLucas);
		objetoComTextinhoLucasBooleanoFalsoNumeroZero.comoObjeto().inserir("booleano", booleanoFalso);
		objetoComTextinhoLucasBooleanoFalsoNumeroZero.comoObjeto().inserir("numero", numeroZero);
		objetoComTextoLucasBooleanoFalsoNumeroUm = Json.criarObjeto();
		objetoComTextoLucasBooleanoFalsoNumeroUm.comoObjeto().inserir("texto", textoLucas);
		objetoComTextoLucasBooleanoFalsoNumeroUm.comoObjeto().inserir("booleano", booleanoFalso);
		objetoComTextoLucasBooleanoFalsoNumeroUm.comoObjeto().inserir("numero", numeroUm);

		TextoJson textoLucas = Json.criarTexto(Texto.criar("Lucas"));
		NumeroJson numeroUm = Json.criarNumero(Numero.criar(1));
		BooleanoJson booleanoFalso = Json.criarBooleano(Booleano.verdadeiro());
		BooleanoJson booleanoVerdadeiro = Json.criarBooleano(Booleano.falso());
		ObjetoJson objetoComNumeroUmEListaComBooleanoVerdadeiro = Json.criarObjeto();
		ObjetoJson objetoComNumeroUmEListaComBooleanoFalso = Json.criarObjeto();
		ListaJson listaComBooleanoVerdadeiro = Json.criarLista();
		ListaJson listaComBooleanoFalso = Json.criarLista();
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro = Json.criarLista();
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso = Json.criarLista();
		listaComBooleanoVerdadeiro.inserir(booleanoVerdadeiro);
		listaComBooleanoFalso.inserir(booleanoFalso);
		objetoComNumeroUmEListaComBooleanoVerdadeiro.inserir("numero", numeroUm);
		objetoComNumeroUmEListaComBooleanoVerdadeiro.inserir("lista", listaComBooleanoVerdadeiro);
		objetoComNumeroUmEListaComBooleanoFalso.inserir("numero", numeroUm);
		objetoComNumeroUmEListaComBooleanoFalso.inserir("lista", listaComBooleanoFalso);
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro.comoLista().inserir(textoLucas);
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro.comoLista().inserir(objetoComNumeroUmEListaComBooleanoVerdadeiro);
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso.comoLista().inserir(textoLucas);
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso.comoLista().inserir(objetoComNumeroUmEListaComBooleanoFalso);
		
		listaComTextoLucasENumeroUm = Json.criarLista();
		listaComTextoLucasENumeroUm.comoLista().inserir(textoLucas);
		listaComTextoLucasENumeroUm.comoLista().inserir(numeroUm);
		listaComNumeroUmETextoLucas = Json.criarLista();
		listaComNumeroUmETextoLucas.comoLista().inserir(numeroUm);
		listaComNumeroUmETextoLucas.comoLista().inserir(textoLucas);
	}

	@Test
	public void compararTextoVazioComNulo() {
		assertThat(textoVazio, is(not(equalTo(null))));
	}

	@Test
	public void comprarTextoVazioComObjetoVazio() {
		assertThat(textoVazio, is(not(equalTo(objetoVazio))));
	}

	@Test
	public void comprarTextoVazioComTextoVazio() {
		assertThat(textoVazio, is(sameInstance(textoVazio)));
		assertThat(textoVazio, is(equalTo(textoVazio)));
		assertThat(textoVazio, is(not(sameInstance(outroTextoVazio))));
		assertThat(textoVazio, is(equalTo(outroTextoVazio)));
	}

	@Test
	public void comprarTextoVazioComTextoLucas() {
		assertThat(textoVazio, is(not(equalTo(textoLucas))));
	}

	@Test
	public void comprarNumeroUmComNumeroUm() {
		assertThat(numeroUm, is(sameInstance(numeroUm)));
		assertThat(numeroUm, is(equalTo(numeroUm)));
	}

	@Test
	public void comprarNumeroUmComObjetoVazio() {
		assertThat(numeroUm, is(not(equalTo(objetoVazio))));
	}

	@Test
	public void comprarNumeroZeroComNumeroUm() {
		assertThat(numeroZero, is(not(sameInstance(numeroUm))));
		assertThat(numeroZero, is(not(equalTo(numeroUm))));
	}

	@Test
	public void comprarBooleanoVerdadeiroComBooleanoVerdadeiro() {
		assertThat(booleanoVerdadeiro, is(sameInstance(booleanoVerdadeiro)));
		assertThat(booleanoVerdadeiro, is(equalTo(booleanoVerdadeiro)));
		assertThat(booleanoVerdadeiro, is(not(sameInstance(outroBooleanoVerdadeiro))));
		assertThat(booleanoVerdadeiro, is(equalTo(outroBooleanoVerdadeiro)));
	}

	@Test
	public void compararBooleanoVerdadeiroComBooleanoFalso() {
		assertThat(booleanoVerdadeiro, is(not(sameInstance(booleanoFalso))));
		assertThat(booleanoVerdadeiro, is(not(equalTo(booleanoFalso))));
	}

	@Test
	public void compararBooleanoVerdadeiroComObjetoVazio() {
		assertThat(booleanoVerdadeiro, is(not(equalTo(objetoVazio))));
	}

	@Test
	public void compararObjetoVazioComNulo() {
		assertThat(objetoVazio, is(not(equalTo(null))));
	}

	@Test
	public void compararObjetoVazioComObjetoVazio() {
		assertThat(objetoVazio, is(sameInstance(objetoVazio)));
		assertThat(objetoVazio, is(equalTo(objetoVazio)));
		assertThat(objetoVazio, is(not(sameInstance(outroObjetoVazio))));
		assertThat(objetoVazio, is(equalTo(outroObjetoVazio)));
	}

	@Test
	public void compararObjetoVazioComListaVazia() {
		assertThat(objetoVazio, is(not(equalTo(listaVazia))));
	}

	@Test
	public void comprarObjetoVazioComObjetoComTextoLucasBooleanoFalsoNumeroZero() {
		assertThat(objetoVazio, is(not(equalTo(objetoComTextoLucasBooleanoFalsoNumeroZero))));
	}

	@Test
	public void comprarObjetosComIdentificadoresDiferentesEValoresIguais() {
		assertThat(objetoComTextoLucasBooleanoFalsoNumeroUm, is(not(sameInstance(objetoComTextinhoLucasBooleanoFalsoNumeroZero))));
		assertThat(objetoComTextoLucasBooleanoFalsoNumeroUm, is(not(equalTo(objetoComTextinhoLucasBooleanoFalsoNumeroZero))));
	}

	@Test
	public void comprarObjetosComIdentificadoresIguaisEValoresDiferentes() {
		assertThat(objetoComTextoLucasBooleanoFalsoNumeroUm, is(not(sameInstance(objetoComTextoLucasBooleanoFalsoNumeroZero))));
		assertThat(objetoComTextoLucasBooleanoFalsoNumeroUm, is(not(equalTo(objetoComTextoLucasBooleanoFalsoNumeroZero))));
	}

	@Test
	public void compararListaVaziaComNulo() {
		assertThat(listaVazia, is(not(equalTo(null))));
	}

	@Test
	public void compararListaVaziaComListaVazia() {
		assertThat(listaVazia, is(sameInstance(listaVazia)));
		assertThat(listaVazia, is(equalTo(listaVazia)));
		assertThat(listaVazia, is(not(sameInstance(outraListaVazia))));
		assertThat(listaVazia, is(equalTo(outraListaVazia)));
	}

	@Test
	public void compararListaVaziaComObjetoVazio() {
		assertThat(listaVazia, is(not(equalTo(objetoVazio))));
	}

	@Test
	public void comprarListaVaziaComListaComUmElemento() {
		assertThat(listaVazia, is(equalTo(listaComUmElemento)));
	}

	@Test
	public void compararListaComElementosDiferentes() {
		assertThat(listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro, is(not(equalTo(listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso))));
	}

	@Test
	public void compararListaComElementosInvertidos() {
		assertThat(listaComTextoLucasENumeroUm, is(not(equalTo(listaComNumeroUmETextoLucas))));
	}
}
