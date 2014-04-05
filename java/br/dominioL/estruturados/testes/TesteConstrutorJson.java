package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.json.BooleanoJson;
import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.NumeroJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;

public final class TesteConstrutorJson {
	private String identificador;
	private String identificadorTexto;
	private String identificadorNumero;
	private String identificadorBooleano;
	private String textoLucas;
	private BigDecimal decimalUm;
	private Integer inteiroUm;
	private Double flutuanteUm;
	private Boolean booleanoFalso;
	private IdentificadorJson identificadorJson;
	private IdentificadorJson identificadorJsonTexto;
	private IdentificadorJson identificadorJsonNumero;
	private IdentificadorJson identificadorJsonBooleano;
	private TextoJson textoJsonLucas;
	private NumeroJson numeroJsonUm;
	private BooleanoJson booleanoJsonFalso;
	private ObjetoJson objetoComUmLucasFalso;
	private ObjetoJson objetoComTextoLucas;
	private ObjetoJson objetoComNumeroUm;
	private ObjetoJson objetoComNumeroUmFlutuante;
	private ObjetoJson objetoComBooleanoFalso;
	private ObjetoJson objetoVazio;
	private ObjetoJson objetoComObjetoVazio;
	private ListaJson listaVazia;
	private ListaJson listaComTextoLucas;
	private ListaJson listaComNumeroUm;
	private ListaJson listaComNumeroUmFlutuante;
	private ListaJson listaComBooleanoFalso;
	private ListaJson listaComElementosSimplesEObjetoComElementosSimples;

	@Before
	public void criarFigurantes() {
		identificador = "identificador";
		identificadorTexto = "texto";
		identificadorNumero = "numero";
		identificadorBooleano = "booleano";
		textoLucas = "Lucas";
		decimalUm = BigDecimal.ONE;
		inteiroUm = 1;
		flutuanteUm = 1.0;
		booleanoFalso = false;
		identificadorJson = Json.criarIdentificador(identificador);
		identificadorJsonTexto = Json.criarIdentificador(identificadorTexto);
		identificadorJsonNumero = Json.criarIdentificador(identificadorNumero);
		identificadorJsonBooleano = Json.criarIdentificador(identificadorBooleano);
		textoJsonLucas = Json.criarTexto(textoLucas);
		numeroJsonUm = Json.criarNumero(inteiroUm);
		booleanoJsonFalso = Json.criarBooleano(booleanoFalso);
		objetoComUmLucasFalso = Json.criarObjeto();
		objetoComUmLucasFalso.inserir(identificadorJsonNumero, numeroJsonUm);
		objetoComUmLucasFalso.inserir(identificadorJsonTexto, textoJsonLucas);
		objetoComUmLucasFalso.inserir(identificadorJsonBooleano, booleanoJsonFalso);
		objetoComTextoLucas = Json.criarObjeto();
		objetoComTextoLucas.inserir(identificadorJson, textoJsonLucas);
		objetoComNumeroUm = Json.criarObjeto();
		objetoComNumeroUm.inserir(identificadorJson, numeroJsonUm);
		objetoComNumeroUmFlutuante = Json.criarObjeto();
		objetoComNumeroUmFlutuante.inserir(identificadorJson, flutuanteUm);
		objetoComBooleanoFalso = Json.criarObjeto();
		objetoComBooleanoFalso.inserir(identificadorJson, booleanoJsonFalso);
		objetoVazio = Json.criarObjeto();
		objetoComObjetoVazio = Json.criarObjeto();
		objetoComObjetoVazio.inserir(identificadorJson, objetoVazio);
		listaVazia = Json.criarLista();
		listaComTextoLucas = Json.criarLista();
		listaComTextoLucas.inserir(textoLucas);
		listaComNumeroUm = Json.criarLista();
		listaComNumeroUm.inserir(decimalUm);
		listaComNumeroUmFlutuante = Json.criarLista();
		listaComNumeroUmFlutuante.inserir(flutuanteUm);
		listaComBooleanoFalso = Json.criarLista();
		listaComBooleanoFalso.inserir(booleanoFalso);
		listaComElementosSimplesEObjetoComElementosSimples = Json.criarLista();
		listaComElementosSimplesEObjetoComElementosSimples.inserir(textoJsonLucas);
		listaComElementosSimplesEObjetoComElementosSimples.inserir(numeroJsonUm);
		listaComElementosSimplesEObjetoComElementosSimples.inserir(booleanoJsonFalso);
		listaComElementosSimplesEObjetoComElementosSimples.inserir(objetoComUmLucasFalso);
	}

	@Test
	public void construirObjetoVazio() {
		ObjetoJson objetoConstruido = ConstrutorJson.deObjeto().construir();
		assertThat(objetoConstruido, is(equalTo(objetoVazio)));
	}

	@Test
	public void construirListaVazia() {
		ListaJson listaConstruida = ConstrutorJson.deLista().construir();
		assertThat(listaConstruida, is(equalTo(listaVazia)));
	}

	@Test
	public void construirObjetoComElementosSimplesUtilizandoValoresJson() {
		ObjetoJson objetoConstruido;
		objetoConstruido = ConstrutorJson.deObjeto()
				.inserir(identificadorJsonNumero, numeroJsonUm)
				.inserir(identificadorJsonTexto, textoJsonLucas)
				.inserir(identificadorJsonBooleano, booleanoJsonFalso)
				.construir();
		assertThat(objetoConstruido, is(equalTo(objetoComUmLucasFalso)));
		objetoConstruido = ConstrutorJson.deObjeto()
				.inserir(identificadorNumero, numeroJsonUm)
				.inserir(identificadorTexto, textoJsonLucas)
				.inserir(identificadorBooleano, booleanoJsonFalso)
				.construir();
		assertThat(objetoConstruido, is(equalTo(objetoComUmLucasFalso)));
	}

	@Test
	public void construirObjetoComElementosSimplesUtilizandoValoresJava() {
		ObjetoJson construido;
		construido = ConstrutorJson.deObjeto().inserir(identificador, textoLucas).construir();
		assertThat(construido, is(equalTo(objetoComTextoLucas)));
		construido = ConstrutorJson.deObjeto().inserir(identificadorJson, textoLucas).construir();
		assertThat(construido, is(equalTo(objetoComTextoLucas)));
		construido = ConstrutorJson.deObjeto().inserir(identificador, decimalUm).construir();
		assertThat(construido, is(equalTo(objetoComNumeroUm)));
		construido = ConstrutorJson.deObjeto().inserir(identificadorJson, decimalUm).construir();
		assertThat(construido, is(equalTo(objetoComNumeroUm)));
		construido = ConstrutorJson.deObjeto().inserir(identificador, inteiroUm).construir();
		assertThat(construido, is(equalTo(objetoComNumeroUm)));
		construido = ConstrutorJson.deObjeto().inserir(identificadorJson, inteiroUm).construir();
		assertThat(construido, is(equalTo(objetoComNumeroUm)));
		construido = ConstrutorJson.deObjeto().inserir(identificador, flutuanteUm).construir();
		assertThat(construido, is(equalTo(objetoComNumeroUmFlutuante)));
		construido = ConstrutorJson.deObjeto().inserir(identificadorJson, flutuanteUm).construir();
		assertThat(construido, is(equalTo(objetoComNumeroUmFlutuante)));
		construido = ConstrutorJson.deObjeto().inserir(identificador, booleanoFalso).construir();
		assertThat(construido, is(equalTo(objetoComBooleanoFalso)));
		construido = ConstrutorJson.deObjeto().inserir(identificadorJson, booleanoFalso).construir();
		assertThat(construido, is(equalTo(objetoComBooleanoFalso)));
	}

	@Test
	public void construirObjetoComOjetoVazioDentro() {
		ObjetoJson objetoConstruido  = ConstrutorJson.deObjeto()
				.inserir(identificadorJson, ConstrutorJson.deObjeto()
						.construir())
				.construir();
		assertThat(objetoConstruido, is(equalTo(objetoComObjetoVazio)));
	}

	@Test
	public void construirListaComElementosSimplesUtilizandoValoresJava() {
		ListaJson construido;
		construido = ConstrutorJson.deLista().inserir(textoLucas).construir();
		assertThat(construido, is(equalTo(listaComTextoLucas)));
		construido = ConstrutorJson.deLista().inserir(decimalUm).construir();
		assertThat(construido, is(equalTo(listaComNumeroUm)));
		construido = ConstrutorJson.deLista().inserir(inteiroUm).construir();
		assertThat(construido, is(equalTo(listaComNumeroUm)));
		construido = ConstrutorJson.deLista().inserir(flutuanteUm).construir();
		assertThat(construido, is(equalTo(listaComNumeroUmFlutuante)));
		construido = ConstrutorJson.deLista().inserir(booleanoFalso).construir();
		assertThat(construido, is(equalTo(listaComBooleanoFalso)));
	}

	@Test
	public void construirListaComElementosSimplesEObjetoComElementosSimples() {
		ListaJson listaConstruida = ConstrutorJson.deLista()
				.inserir(textoJsonLucas)
				.inserir(numeroJsonUm)
				.inserir(booleanoJsonFalso)
				.inserir(ConstrutorJson.deObjeto()
						.inserir(identificadorJsonNumero, numeroJsonUm)
						.inserir(identificadorJsonTexto, textoJsonLucas)
						.inserir(identificadorJsonBooleano, booleanoJsonFalso)
						.construir())
				.construir();
		assertThat(listaConstruida, is(equalTo(listaComElementosSimplesEObjetoComElementosSimples)));
	}
}
