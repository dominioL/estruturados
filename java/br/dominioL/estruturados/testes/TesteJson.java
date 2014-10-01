package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.ObjetoJson;

public final class TesteJson {

	@Test
	public void objetoVazio() {
		ObjetoJson json = Json.criarObjeto(Texto.criar("{}"));
		assertThat(json.toString(), is("{}"));
	}

	@Test
	public void objetoVazioComEspacos() {
		ObjetoJson json = Json.criarObjeto(Texto.criar("{   }"));
		assertThat(json.toString(), is("{}"));
	}

	@Test
	public void objetoComObjetoDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar("   {   \"outroObjeto\"   : {   }   }   "));
		assertThat(json.toString(), is("{\"outroObjeto\": {}}"));
	}

	@Test
	public void inserirObjetoDentroDeObjeto() {
		ObjetoJson objetoPrimario = Json.criarObjeto(Texto.criar("{}"));
		ObjetoJson objetoSecundario = Json.criarObjeto(Texto.criar("{}"));
		ObjetoJson objetoTerciario = Json.criarObjeto(Texto.criar("{}"));
		objetoSecundario.inserir(Texto.criar("terciario"), objetoTerciario);
		objetoPrimario.inserir(Texto.criar("secundario"), objetoSecundario);
		assertThat(objetoPrimario.toString(), is("{\"secundario\": {\"terciario\": {}}}"));
	}

	@Test
	public void objetoComDuasPropriedades() {
		ObjetoJson json = Json.criarObjeto(Texto.criar("{\"access_token\":\"3a740f86b41be8a2faa0b168bdedd0b5571ba0b1\",\"token_type\":\"bearer\"}"));
		assertThat(json.toString(), is("{\"token_type\": \"bearer\", \"access_token\": \"3a740f86b41be8a2faa0b168bdedd0b5571ba0b1\"}"));
	}

	@Test
	public void objetoComTextoDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar("{\"texto\" : 'valorTextual'}"));
		assertThat(json.toString(), is("{\"texto\": \"valorTextual\"}"));
	}

	@Test
	public void objetoComBooleanoFalsoDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar("   {   \"booleano\" : false   }   "));
		assertThat(json.toString(), is("{\"booleano\": false}"));
	}

	@Test
	public void objetoComBooleanoVerdadeiroDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar(" {\"booleano\" : true} "));
		assertThat(json.toString(), is("{\"booleano\": true}"));
	}

	@Test
	public void objetoComNumeroUmDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar(" {\"numero\" : 1} "));
		assertThat(json.toString(), is("{\"numero\": 1}"));
	}

	@Test
	public void objetoComNumeroUmPontoZeroDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar(" {\"numero\" : 1.0} "));
		assertThat(json.toString(), is("{\"numero\": 1.0}"));
	}

	@Test
	public void objetoComNumeroUmPontoZeroUmZeroUmDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar(" {\"numero\" : 1.0101} "));
		assertThat(json.toString(), is("{\"numero\": 1.0101}"));
	}

	@Test
	public void objetoComNumeroZeroPontoZeroUmZeroUmDentro() {
		ObjetoJson json = Json.criarObjeto(Texto.criar(" {\"numero\" : 0.0101} "));
		assertThat(json.toString(), is("{\"numero\": 0.0101}"));
	}

	@Test
	public void listaComElementosDentro() {
		ListaJson json = Json.criarLista(Texto.criar(" [ 10, 'texto' , true , false , \"outroTexto\" , { \"numero\": 10.1 } , [ 1, 2, 3, { } ] ] "));
		assertThat(json.toString(), is("[10, \"texto\", true, false, \"outroTexto\", {\"numero\": 10.1}, [1, 2, 3, {}]]"));
	}

	@Test
	public void inserirElementosDentroDeLista() {
		ListaJson letras = Json.criarLista(Texto.criar("[]"));
		ListaJson numeros = Json.criarLista(Texto.criar("[]"));
		numeros.inserir(Numero.criar(1));
		numeros.inserir(Numero.criar(3));
		numeros.inserir(Numero.criar(2));
		letras.inserir(Texto.criar("a"));
		letras.inserir(Texto.criar("b"));
		letras.inserir(Texto.criar("c"));
		numeros.inserir(letras);
		assertThat(numeros.toString(), is("[1, 3, 2, [\"a\", \"b\", \"c\"]]"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void textoIncompletoLancaExcecao() {
		Json.criarObjeto(Texto.criar("{\"texto\": \"inicio}"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void booleanoIncompletoLancaExcecao() {
		Json.criarObjeto(Texto.criar("{\"booleano\": fals}"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void numeroComLetrasLancaExcecao() {
		Json.criarObjeto(Texto.criar("{\"numero\": 10A0}"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void vazioLancaExcecao() {
		Json.criarObjeto(Texto.criar(""));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void objetoComVazioLancaExcecao() {
		Json.criarObjeto(Texto.criar("{\"outro\":   }"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void objetoNaoFechadoLancaExcecao() {
		Json.criarObjeto(Texto.criar("{\"outroObjeto\": {}"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void listaNaoFechadaLancaExcecao() {
		Json.criarLista(Texto.criar("[1, 2, 3"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void listaComSeparadorAMaisLancaExcecao() {
		Json.criarLista(Texto.criar("[1, 2, 3, ]"));
	}

	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void objetoSemSeparadorDeIdentificadorLancaExcecao() {
		Json.criarObjeto(Texto.criar("{\"outroObjeto\" {}}"));
	}

}
