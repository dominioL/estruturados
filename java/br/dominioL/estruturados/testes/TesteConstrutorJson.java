package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.ObjetoJson;

public final class TesteConstrutorJson {

	private ObjetoJson objetoComUmLucasFalso;
	private ObjetoJson objetoVazio;
	private ObjetoJson objetoComObjetoVazio;
	private ListaJson listaVazia;
	private ListaJson listaComUmLucasFalso;
	private ListaJson listaComElementosSimplesEObjetoComElementosSimples;

	@Before
	public void criarFigurantes() {
		objetoComUmLucasFalso = Json.criarObjeto(Texto.criar("{ \"numero\": 1, \"texto\": \"Lucas\", \"booleano\": false }"));
		objetoVazio = Json.criarObjeto(Texto.criar("{}"));
		objetoComObjetoVazio = Json.criarObjeto(Texto.criar("{ \"identificador\": {}}"));
		listaVazia = Json.criarLista(Texto.criar("[]"));
		listaComUmLucasFalso = Json.criarLista(Texto.criar("[ 1, \"Lucas\", false ]"));
		listaComElementosSimplesEObjetoComElementosSimples = Json.criarLista(Texto.criar("[ 1, \"Lucas\", false, { \"numero\": 1, \"texto\": \"Lucas\", \"booleano\": false } ]"));
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
	public void construirObjetoComElementosSimplesUtilizandoIdentificadoresTexto() {
		ObjetoJson objetoConstruido = ConstrutorJson.deObjeto()
				.inserir(Texto.criar("numero"), Numero.um())
				.inserir(Texto.criar("texto"), Texto.criar("Lucas"))
				.inserir(Texto.criar("booleano"), Booleano.falso())
				.construir();
		assertThat(objetoConstruido, is(equalTo(objetoComUmLucasFalso)));
	}

	@Test
	public void construirObjetoComElementosSimplesUtilizandoIdentificadoresString() {
		ObjetoJson objetoConstruido = ConstrutorJson.deObjeto()
				.inserir("numero", Numero.um())
				.inserir("texto", Texto.criar("Lucas"))
				.inserir("booleano", Booleano.falso())
				.construir();
		assertThat(objetoConstruido, is(equalTo(objetoComUmLucasFalso)));
	}

	@Test
	public void construirObjetoComOjetoVazioDentro() {
		ObjetoJson objetoConstruido = ConstrutorJson.deObjeto()
				.inserir("identificador", ConstrutorJson.deObjeto()
						.construir())
				.construir();
		assertThat(objetoConstruido, is(equalTo(objetoComObjetoVazio)));
	}

	@Test
	public void construirListaComElementosSimples() {
		ListaJson construido = ConstrutorJson.deLista()
				.inserir(Numero.um())
				.inserir(Texto.criar("Lucas"))
				.inserir(Booleano.falso())
				.construir();
		assertThat(construido, is(equalTo(listaComUmLucasFalso)));
	}

	@Test
	public void construirListaComElementosSimplesEObjetoComElementosSimples() {
		ListaJson listaConstruida = ConstrutorJson.deLista()
				.inserir(Numero.um())
				.inserir(Texto.criar("Lucas"))
				.inserir(Booleano.falso())
				.inserir(ConstrutorJson.deObjeto()
						.inserir(Texto.criar("numero"), Numero.um())
						.inserir(Texto.criar("texto"), Texto.criar("Lucas"))
						.inserir(Texto.criar("booleano"), Booleano.falso())
						.construir())
				.construir();
		assertThat(listaConstruida, is(equalTo(listaComElementosSimplesEObjetoComElementosSimples)));
	}

}
