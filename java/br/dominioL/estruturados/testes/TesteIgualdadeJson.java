package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ValorJson;

public class TesteIgualdadeJson {
	private ValorJson objetoVazio;
	private ValorJson outroObjetoVazio;
	private ValorJson objetoComTextoLucasBooleanoFalsoNumeroZero;
	private ValorJson objetoComTextinhoLucasBooleanoFalsoNumeroZero;
	private ValorJson objetoComTextoLucasBooleanoFalsoNumeroUm;
	private ValorJson listaVazia;
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
		textoVazio = Json.criarTexto("");
		outroTextoVazio = Json.criarTexto("");
		textoLucas = Json.criarTexto("Lucas");
		numeroZero = Json.criarNumero(0);
		numeroUm = Json.criarNumero(1);
		booleanoFalso = Json.criarBooleano(false);
		booleanoVerdadeiro = Json.criarBooleano(true);
		outroBooleanoVerdadeiro = Json.criarBooleano(true);
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
}
