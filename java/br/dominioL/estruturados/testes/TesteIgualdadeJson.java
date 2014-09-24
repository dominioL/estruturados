package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.ValorJson;

public final class TesteIgualdadeJson {

	private ValorJson objetoVazio;
	private ValorJson outroObjetoVazio;
	private ValorJson objetoComTextoLucasBooleanoFalsoNumeroZero;
	private ValorJson objetoComTextinhoLucasBooleanoFalsoNumeroZero;
	private ValorJson objetoComTextoLucasBooleanoFalsoNumeroUm;
	private ValorJson listaVazia;
	private ValorJson outraListaVazia;
	private ValorJson listaComZero;
	private ValorJson listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro;
	private ValorJson listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso;
	private ValorJson listaComTextoLucasENumeroUm;
	private ValorJson listaComNumeroUmETextoLucas;

	@Before
	public void criarFiguracaoVazia() {
		objetoVazio = ConstrutorJson.deObjeto().construir();
		outroObjetoVazio = ConstrutorJson.deObjeto().construir();
		listaVazia = ConstrutorJson.deLista().construir();
		outraListaVazia = ConstrutorJson.deLista().construir();
		listaComZero = ConstrutorJson.deLista().inserir(Numero.zero()).construir();
	}

	@Before
	public void criarObjetoComTextoLucasBooleanoFalsoNumeroZero() {
		objetoComTextoLucasBooleanoFalsoNumeroZero = ConstrutorJson.deObjeto()
				.inserir("texto", Texto.criar("Lucas"))
				.inserir("booleano", Booleano.falso())
				.inserir("numero", Numero.zero())
				.construir();
	}

	@Before
	public void criarObjetoComTextinhoLucasBooleanoFalsoNumeroZero() {
		objetoComTextinhoLucasBooleanoFalsoNumeroZero = ConstrutorJson.deObjeto()
				.inserir("textinho", Texto.criar("Lucas"))
				.inserir("booleano", Booleano.falso())
				.inserir("numero", Numero.zero())
				.construir();
	}

	@Before
	public void criarObjetoComTextoLucasBooleanoFalsoNumeroUm() {
		objetoComTextoLucasBooleanoFalsoNumeroUm = ConstrutorJson.deObjeto()
				.inserir("texto", Texto.criar("Lucas"))
				.inserir("booleano", Booleano.falso())
				.inserir("numero", Numero.um())
				.construir();
	}

	@Before
	public void criarListaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso() {
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoFalso = ConstrutorJson.deLista()
				.inserir(Texto.criar("Lucas"))
				.inserir(ConstrutorJson.deObjeto()
						.inserir("numero", Numero.um())
						.inserir("lista", ConstrutorJson.deLista()
								.inserir(Booleano.falso())
								.construir())
						.construir())
				.construir();
	}

	@Before
	public void criarListaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro() {
		listaComTextoLucasEObjetoComNumeroUmEListaComBooleanoVerdadeiro = ConstrutorJson.deLista()
				.inserir(Texto.criar("Lucas"))
				.inserir(ConstrutorJson.deObjeto()
						.inserir("numero", Numero.um())
						.inserir("lista", ConstrutorJson.deLista()
								.inserir(Booleano.verdadeiro())
								.construir())
						.construir())
				.construir();
	}

	@Before
	public void criarListaComTextoLucasENumeroUm() {
		listaComTextoLucasENumeroUm = ConstrutorJson.deLista()
				.inserir(Texto.criar("Lucas"))
				.inserir(Numero.um())
				.construir();
	}

	@Before
	public void criarListaComNumeroUmETextoLucas() {
		listaComNumeroUmETextoLucas = ConstrutorJson.deLista()
				.inserir(Numero.um())
				.inserir(Texto.criar("Lucas"))
				.construir();
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
		assertThat(objetoComTextoLucasBooleanoFalsoNumeroZero, is(not(sameInstance(objetoComTextinhoLucasBooleanoFalsoNumeroZero))));
		assertThat(objetoComTextoLucasBooleanoFalsoNumeroZero, is(not(equalTo(objetoComTextinhoLucasBooleanoFalsoNumeroZero))));
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
		assertThat(listaVazia, not(equalTo(listaComZero)));
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
