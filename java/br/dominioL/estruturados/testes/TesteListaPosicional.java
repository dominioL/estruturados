package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoDeIndiceForaDosLimites;
import br.dominioL.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.testes.figuracao.Numero;

public final class TesteListaPosicional {
	private Numero primeiroNumero = new Numero(1);
	private Numero segundoNumero = new Numero(2);
	private Numero terceiroNumero = new Numero(3);
	private Numero quartoNumero = new Numero(4);
	private Numero numeroNulo = null;

	@Test
	public void fornecerUmElementoDaPoscaoZero() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertSame(lista.fornecerDaPosicao(0), primeiroNumero);
	}

	@Test
	public void fornecerUmElementoDaPosicaoUm() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		assertSame(lista.fornecerDaPosicao(1), segundoNumero);
	}

	@Test(expected = ExcecaoDeIndiceForaDosLimites.class)
	public void fornecerUmElementoDeUmaPosicaoForaDosLimites() {
		ListaPosicional<Numero> lista = ListaPosicional.criar(1);
		lista.fornecerDaPosicao(1);
	}

	@Test
	public void fornecerUmElementoDeUmaPosicaoQueTeveOElementoRemovido() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.removerDaPosicao(1);
		assertThat(lista.fornecerDaPosicao(1), nullValue());
	}

	@Test
	public void inserirUmElementoNaPosicaoZero() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		assertSame(lista.fornecerDaPosicao(0), primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test
	public void inserirUmElementoNaPosicaoUm() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(1, primeiroNumero);
		assertSame(lista.fornecerDaPosicao(1), primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test(expected = ExcecaoDeIndiceForaDosLimites.class)
	public void inserirUmElementoEmUmaPosicaoForaDosLimites() {
		ListaPosicional<Numero> lista = ListaPosicional.criar(1);
		lista.inserirNaPosicao(1, primeiroNumero);
	}

	@Test
	public void inserirUmElementoNaPosicaoDeUmElementoQueJaExiste() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(0, segundoNumero);
		assertSame(lista.fornecerDaPosicao(0), segundoNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void inserirUmElementoNulo() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(numeroNulo);
	}

	@Test
	public void removerUmElementoDaPoscaoZero() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertSame(lista.removerDaPosicao(0), primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(0));
	}

	@Test
	public void removerUmElementoDaPosicaoUm() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		assertSame(lista.removerDaPosicao(1), segundoNumero);
		assertThat(lista.fornecerQuantidade(), is(1));	
	}

	@Test(expected = ExcecaoDeIndiceForaDosLimites.class)
	public void removerUmElementoDeUmaPosicaoForaDosLimites() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.removerDaPosicao(-1);
	}

	@Test
	public void removerUmElementoDeUmaPosicaoQueTeveOElementoRemovido() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.removerDaPosicao(0);
		assertThat(lista.removerDaPosicao(0), nullValue());
		assertThat(lista.fornecerQuantidade(), is(0));
	}

	@Test
	public void inserirUmElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test
	public void inserirUmElementoDuplicadoMantemElementosDuplicados() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(3));
	}

	@Test
	public void removerUmElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero));
		assertThat(lista.fornecerQuantidade(), is(0));
	}

	@Test
	public void removerUmElementoDuplicadoRemoveApenasOPrimeiro() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero));
		assertThat(lista.fornecerQuantidade(), is(1));
		assertThat(lista.fornecerDaPosicao(0), nullValue());
		assertSame(lista.fornecerDaPosicao(1), primeiroNumero);
	}

	@Test
	public void removerUmElementoQueNaoExiste() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.remover(segundoNumero));
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test
	public void contemUmElementoSeElePertence() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertTrue(lista.contem(terceiroNumero));
	}

	@Test
	public void naoContemUmElementoSeEleNaoPertence() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertFalse(lista.contem(quartoNumero));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoNaoTemElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		assertThat(lista.fornecerQuantidade(), is(0));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoTemElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void estaVaziaSeNaoPossuiNenhumElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.remover(primeiroNumero);
		assertTrue(lista.vazio());
	}

	@Test
	public void naoEstaVaziaSePossuiElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.vazio());
	}

	@Test
	public void iteradorNaoPassaPorElementosNulos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		lista.inserirNaPosicao(2, terceiroNumero);
		lista.removerDaPosicao(1);
		Iterador<Numero> iterador = lista.fornecerIterador();
		while (iterador.possuiProximo()) {
			assertThat(iterador.iterarProximo(), notNullValue());
		}
	}

	@Test
	public void iteradorPassaPorTodosElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		lista.inserirNaPosicao(2, terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertSame(iterador.iterarProximo(), primeiroNumero);
		assertSame(iterador.iterarProximo(), segundoNumero);
		assertSame(iterador.iterarProximo(), terceiroNumero);
		assertFalse(iterador.possuiProximo());
	}

	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		lista.inserirNaPosicao(2, terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.remover(), segundoNumero);
		iterador.iterarProximo();
		assertSame(iterador.remover(), terceiroNumero);
		assertFalse(iterador.possuiProximo());
		assertTrue(lista.vazio());
	}

	@Test
	public void iteradorPermiteRemoverOPrimeiroElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		lista.inserirNaPosicao(2, terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), primeiroNumero);
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo());
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOSegundoElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		lista.inserirNaPosicao(2, terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), segundoNumero);
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo());
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOUltimoElmento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		lista.inserirNaPosicao(2, terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), terceiroNumero);
		assertFalse(iterador.possuiProximo());
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverDuasVezesNaMesmaIteracao() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		lista.fornecerIterador().remover();
		lista.fornecerIterador().remover();
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().remover();
	}

	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.substituir(terceiroNumero), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.substituir(quartoNumero), segundoNumero);
		assertFalse(iterador.possuiProximo());
		assertSame(lista.fornecerDaPosicao(0), terceiroNumero);
		assertSame(lista.fornecerDaPosicao(1), quartoNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo() == primeiroNumero) {
				iterador.substituir(terceiroNumero);
			}
		}
		assertSame(lista.fornecerDaPosicao(0), terceiroNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserirNaPosicao(0, primeiroNumero);
		lista.inserirNaPosicao(1, segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo() == segundoNumero) {
				iterador.substituir(terceiroNumero);
			}
		}
		assertSame(lista.fornecerDaPosicao(1), terceiroNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(segundoNumero);
		iterador.substituir(segundoNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().substituir(segundoNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.fornecerIterador().iterarProximo();
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
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

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		ListaPosicional<Numero> lista = ListaPosicional.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(terceiroNumero);
	}
}
