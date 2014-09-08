package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoEstruturaVazia;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;

public final class TesteListaEncadeada {

	private Numero primeiroNumero = Numero.criar(1);
	private Numero segundoNumero = Numero.criar(2);
	private Numero terceiroNumero = Numero.criar(3);
	private Numero quartoNumero = Numero.criar(4);

	@Test
	public void fornecerUmElementoDoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		assertSame(primeiroNumero, lista.fornecerDoInicio());
		lista.inserirNoInicio(segundoNumero);
		assertSame(segundoNumero, lista.fornecerDoInicio());
	}

	@Test
	public void fornecerUmElementoDoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		assertSame(primeiroNumero, lista.fornecerDoFim());
		lista.inserirNoFim(segundoNumero);
		assertSame(segundoNumero, lista.fornecerDoFim());
	}

	@Test
	public void fornecerUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		assertSame(primeiroNumero, lista.fornecerDoInicio());
		assertSame(primeiroNumero, lista.fornecerDoFim());
		lista.inserirNoInicio(segundoNumero);
		assertSame(segundoNumero, lista.fornecerDoInicio());
		assertSame(primeiroNumero, lista.fornecerDoFim());
		lista.inserirNoFim(terceiroNumero);
		assertSame(segundoNumero, lista.fornecerDoInicio());
		assertSame(terceiroNumero, lista.fornecerDoFim());
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void fornecerUmElmentoDoInicioDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.fornecerDoInicio();
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void fornecerUmElmentoDoFimDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.fornecerDoFim();
	}

	@Test
	public void inserirUmElementoNoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		assertThat(lista.contarElementos().inteiro(), is(0));
		lista.inserirNoInicio(primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
		lista.inserirNoInicio(segundoNumero);
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test
	public void inserirUmElementoNoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		assertThat(lista.contarElementos().inteiro(), is(0));
		lista.inserirNoFim(primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
		lista.inserirNoFim(segundoNumero);
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void inserirUmElementoNuloNoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(null);
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void inserirUmElementoNuloNoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(null);
	}

	@Test
	public void removerElementoDoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		Numero primeiroNumeroRemovido = lista.removerDoInicio();
		assertThat(lista.contarElementos().inteiro(), is(0));
		lista.inserirNoInicio(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Numero segundoNumeroRemovido = lista.removerDoInicio();
		assertThat(lista.contarElementos().inteiro(), is(1));
		Numero terceiroNumeroRemovido = lista.removerDoInicio();
		assertThat(lista.contarElementos().inteiro(), is(0));
		assertSame(primeiroNumeroRemovido, primeiroNumero);
		assertSame(segundoNumeroRemovido, segundoNumero);
		assertSame(terceiroNumeroRemovido, terceiroNumero);
	}

	@Test
	public void removerElementoDoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		Numero primeiroNumeroRemovido = lista.removerDoFim();
		assertThat(lista.contarElementos().inteiro(), is(0));
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoInicio(terceiroNumero);
		Numero segundoNumeroRemovido = lista.removerDoFim();
		assertThat(lista.contarElementos().inteiro(), is(1));
		Numero terceiroNumeroRemovido = lista.removerDoFim();
		assertThat(lista.contarElementos().inteiro(), is(0));
		assertSame(primeiroNumeroRemovido, primeiroNumero);
		assertSame(segundoNumeroRemovido, segundoNumero);
		assertSame(terceiroNumeroRemovido, terceiroNumero);
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void removerUmElmentoDoInicioDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.removerDoInicio();
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void removerUmElmentoDoFimDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.removerDoFim();
	}

	@Test
	public void inserirUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test
	public void inserirUmElementoDuplicadoMantemElementosDuplicados() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(3));
	}

	@Test
	public void removerUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero).avaliar());
		assertThat(lista.contarElementos().inteiro(), is(0));
	}

	@Test
	public void removerUmElementoDuplicadoRemoveApenasOPrimeiro() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero).avaliar());
		assertThat(lista.contarElementos().inteiro(), is(1));
		assertSame(lista.fornecerDoInicio(), primeiroNumero);
	}

	@Test
	public void removerUmElementoQueNaoExiste() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.remover(segundoNumero).avaliar());
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test
	public void contemUmElementoSeElePertence() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertTrue(lista.contem(terceiroNumero).avaliar());
	}

	@Test
	public void naoContemUmElementoSeEleNaoPertence() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertFalse(lista.contem(quartoNumero).avaliar());
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoNaoTemElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		assertThat(lista.contarElementos().inteiro(), is(0));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoTemElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test
	public void estaVaziaSeNaoPossuiNenhumElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.remover(primeiroNumero);
		assertTrue(lista.vazio().avaliar());
	}

	@Test
	public void naoEstaVaziaSePossuiElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.vazio().avaliar());
	}

	@Test
	public void iteradorPassaPorTodosElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertSame(iterador.iterarProximo(), primeiroNumero);
		assertSame(iterador.iterarProximo(), segundoNumero);
		assertSame(iterador.iterarProximo(), terceiroNumero);
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.remover(), segundoNumero);
		iterador.iterarProximo();
		assertSame(iterador.remover(), terceiroNumero);
		assertFalse(iterador.possuiProximo().avaliar());
		assertTrue(lista.vazio().avaliar());
	}

	@Test
	public void iteradorPermiteRemoverOPrimeiroElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), primeiroNumero);
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOSegundoElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), segundoNumero);
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOUltimoElmento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), terceiroNumero);
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverDuasVezesNaMesmaIteracao() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		lista.fornecerIterador().remover();
		lista.fornecerIterador().remover();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().remover();
	}

	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.substituir(terceiroNumero), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.substituir(quartoNumero), segundoNumero);
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(lista.fornecerDoInicio(), terceiroNumero);
		assertSame(lista.fornecerDoFim(), quartoNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(primeiroNumero).avaliar());
		assertTrue(iterador.substituir(terceiroNumero).igual(primeiroNumero).avaliar());
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(segundoNumero).avaliar());
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(lista.fornecerDoInicio(), terceiroNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(primeiroNumero).avaliar());
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(segundoNumero).avaliar());
		assertTrue(iterador.substituir(terceiroNumero).igual(segundoNumero).avaliar());
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(lista.fornecerDoFim(), terceiroNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(segundoNumero);
		iterador.substituir(segundoNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().substituir(segundoNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.fornecerIterador().iterarProximo();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoMaisDeUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
	}

	@Test
	public void iteradorPermiteSubstituirEDepoisRemover() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(terceiroNumero);
		assertSame(iterador.remover(), terceiroNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(terceiroNumero);
	}

}
