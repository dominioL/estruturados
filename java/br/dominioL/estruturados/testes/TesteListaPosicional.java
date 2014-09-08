package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoIndiceForaDosLimites;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;

public final class TesteListaPosicional {

	private Numero primeiroNumero = Numero.criar(1);
	private Numero segundoNumero = Numero.criar(2);
	private Numero terceiroNumero = Numero.criar(3);
	private Numero quartoNumero = Numero.criar(4);
	private Numero numeroNulo = null;

	@Test
	public void fornecerUmElementoDaPoscaoZero() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertSame(lista.fornecerDaPosicao(Numero.zero()), primeiroNumero);
	}

	@Test
	public void fornecerUmElementoDaPosicaoUm() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		assertSame(lista.fornecerDaPosicao(Numero.um()), segundoNumero);
	}

	@Test(expected = ExcecaoIndiceForaDosLimites.class)
	public void fornecerUmElementoDeUmaPosicaoForaDosLimites() {
		ListaPosicional<Numero> lista = ListaPosicional.criar(Numero.um());
		lista.fornecerDaPosicao(Numero.um());
	}

	@Test
	public void fornecerUmElementoDeUmaPosicaoQueTeveOElementoRemovido() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.removerDaPosicao(Numero.um());
		assertThat(lista.fornecerDaPosicao(Numero.um()), nullValue());
	}

	@Test
	public void inserirUmElementoNaPosicaoZero() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		assertSame(lista.fornecerDaPosicao(Numero.zero()), primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test
	public void inserirUmElementoNaPosicaoUm() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.um(), primeiroNumero);
		assertSame(lista.fornecerDaPosicao(Numero.um()), primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test(expected = ExcecaoIndiceForaDosLimites.class)
	public void inserirUmElementoEmUmaPosicaoForaDosLimites() {
		ListaPosicional<Numero> lista = ListaPosicional.criar(Numero.um());
		lista.inserirNaPosicao(Numero.um(), primeiroNumero);
	}

	@Test
	public void inserirUmElementoNaPosicaoDeUmElementoQueJaExiste() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.zero(), segundoNumero);
		assertSame(lista.fornecerDaPosicao(Numero.zero()), segundoNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void inserirUmElementoNulo() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(numeroNulo);
	}

	@Test
	public void removerUmElementoDaPoscaoZero() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertSame(lista.removerDaPosicao(Numero.zero()), primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(0));
	}

	@Test
	public void removerUmElementoDaPosicaoUm() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		assertSame(lista.removerDaPosicao(Numero.um()), segundoNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test(expected = ExcecaoIndiceForaDosLimites.class)
	public void removerUmElementoDeUmaPosicaoForaDosLimites() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.removerDaPosicao(Numero.criar(-1));
	}

	@Test
	public void removerUmElementoDeUmaPosicaoQueTeveOElementoRemovido() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.removerDaPosicao(Numero.zero());
		assertThat(lista.removerDaPosicao(Numero.zero()), nullValue());
		assertThat(lista.contarElementos().inteiro(), is(0));
	}

	@Test
	public void inserirUmElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test
	public void inserirUmElementoDuplicadoMantemElementosDuplicados() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(primeiroNumero);
		assertThat(lista.contarElementos().inteiro(), is(3));
	}

	@Test
	public void removerUmElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero).avaliar());
		assertThat(lista.contarElementos().inteiro(), is(0));
	}

	@Test
	public void removerUmElementoDuplicadoRemoveApenasOPrimeiro() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero).avaliar());
		assertThat(lista.contarElementos().inteiro(), is(1));
		assertThat(lista.fornecerDaPosicao(Numero.zero()), nullValue());
		assertSame(lista.fornecerDaPosicao(Numero.um()), primeiroNumero);
	}

	@Test
	public void removerUmElementoQueNaoExiste() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.remover(segundoNumero).avaliar());
		assertThat(lista.contarElementos().inteiro(), is(1));
	}

	@Test
	public void contemUmElementoSeElePertence() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertTrue(lista.contem(terceiroNumero).avaliar());
	}

	@Test
	public void naoContemUmElementoSeEleNaoPertence() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertFalse(lista.contem(quartoNumero).avaliar());
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoNaoTemElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		assertThat(lista.contarElementos().inteiro(), is(0));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoTemElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		assertThat(lista.contarElementos().inteiro(), is(2));
	}

	@Test
	public void estaVaziaSeNaoPossuiNenhumElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.remover(primeiroNumero);
		assertTrue(lista.vazio().avaliar());
	}

	@Test
	public void naoEstaVaziaSePossuiElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.vazio().avaliar());
	}

	@Test
	public void iteradorNaoPassaPorElementosNulos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		lista.inserirNaPosicao(Numero.criar(2), terceiroNumero);
		lista.removerDaPosicao(Numero.um());
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertThat(iterador.iterarProximo(), notNullValue());
		assertThat(iterador.iterarProximo(), notNullValue());
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void iteradorPassaPorTodosElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		lista.inserirNaPosicao(Numero.criar(2), terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertSame(iterador.iterarProximo(), primeiroNumero);
		assertSame(iterador.iterarProximo(), segundoNumero);
		assertSame(iterador.iterarProximo(), terceiroNumero);
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		lista.inserirNaPosicao(Numero.criar(2), terceiroNumero);
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
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		lista.inserirNaPosicao(Numero.criar(2), terceiroNumero);
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
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		lista.inserirNaPosicao(Numero.criar(2), terceiroNumero);
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
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		lista.inserirNaPosicao(Numero.criar(2), terceiroNumero);
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
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		lista.fornecerIterador().remover();
		lista.fornecerIterador().remover();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().remover();
	}

	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.substituir(terceiroNumero), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.substituir(quartoNumero), segundoNumero);
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(lista.fornecerDaPosicao(Numero.zero()), terceiroNumero);
		assertSame(lista.fornecerDaPosicao(Numero.um()), quartoNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(primeiroNumero).avaliar());
		assertTrue(iterador.substituir(terceiroNumero).igual(primeiroNumero).avaliar());
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(segundoNumero).avaliar());
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(Numero.zero(), primeiroNumero);
		lista.inserirNaPosicao(Numero.um(), segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(primeiroNumero).avaliar());
		assertTrue(iterador.possuiProximo().avaliar());
		assertTrue(iterador.iterarProximo().igual(segundoNumero).avaliar());
		assertTrue(iterador.substituir(terceiroNumero).igual(segundoNumero).avaliar());
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(lista.fornecerDaPosicao(Numero.um()), terceiroNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(segundoNumero);
		iterador.substituir(segundoNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().substituir(segundoNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.fornecerIterador().iterarProximo();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoMaisDeUmElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
	}

	@Test
	public void iteradorPermiteSubstituirEDepoisRemover() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(terceiroNumero);
		assertSame(iterador.remover(), terceiroNumero);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(terceiroNumero);
	}

	@Test
	public void fixarValorNulo() {
		ListaPosicional<Numero> lista = ListaPosicional.criar(Numero.um());
		assertNull(lista.fornecerDaPosicao(Numero.zero()));
		lista.fixarValorNulo(primeiroNumero);
		assertNotNull(lista.fornecerDaPosicao(Numero.zero()));
		assertTrue(primeiroNumero.igual(lista.fornecerDaPosicao(Numero.zero())).avaliar());
	}

}
