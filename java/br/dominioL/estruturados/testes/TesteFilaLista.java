package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.colecao.fila.FilaLista;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoEstruturaVazia;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;

public final class TesteFilaLista {

	private Numero numeroUm = Numero.criar(1);
	private Numero numeroDois = Numero.criar(2);
	private Numero numeroTres = Numero.criar(3);
	private Numero numeroQuatro = Numero.criar(4);
	private Numero numeroNulo = null;

	@Test
	public void enfileirarAdicionaUmElementoNoFim() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertSame(fila.fornecerDoFim(), numeroDois);
		assertThat(fila.contarElementos().inteiro(), is(2));
	}

	@Test
	public void enfileirarAdicionaUmElementoNoFimMesmoQueElePertenca() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		fila.enfileirar(numeroUm);
		assertSame(fila.fornecerDoFim(), numeroUm);
		assertThat(fila.contarElementos().inteiro(), is(3));
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void enfileirarLancaUmaExcecaoSeOElementoForNulo() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroNulo);
	}

	@Test
	public void desenfileirarRemoveUmElementoDoInicio() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertSame(fila.desenfileirar(), numeroUm);
		assertThat(fila.contarElementos().inteiro(), is(1));
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void desenfileirarLancaUmExcecaoSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.desenfileirar();
	}

	@Test
	public void fornecerDoInicioForneceOPrimeiroElementoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertSame(fila.fornecerDoInicio(), numeroUm);
		assertThat(fila.contarElementos().inteiro(), is(2));
	}

	@Test
	public void fornecerDoFimForneceOUltimoElementoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertSame(fila.fornecerDoFim(), numeroDois);
		assertThat(fila.contarElementos().inteiro(), is(2));
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void fornecerDoInicioLancaUmaExcecaoSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.fornecerDoInicio();
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void fornecerDoFimLancaUmaExcecaoSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.fornecerDoFim();
	}

	@Test
	public void estaNoInicioSeOElementoFoiOPrimeiroEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertTrue(fila.estaNoInicio(numeroUm).avaliar());
	}

	@Test
	public void estaNoInicioSeForOUnicoElemento() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		assertTrue(fila.estaNoInicio(numeroUm).avaliar());
	}

	@Test
	public void naoEstaNoInicioSeOElementoNaoFoiOPrimeiroEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertFalse(fila.estaNoInicio(numeroDois).avaliar());
	}

	@Test
	public void naoEstaNoInicioSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		assertFalse(fila.estaNoInicio(numeroUm).avaliar());
	}

	@Test
	public void naoEstaNoInicioSeOElementoForNulo() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		assertFalse(fila.estaNoInicio(numeroNulo).avaliar());
	}

	@Test
	public void estaNoFimSeOElementoFoiOUltimoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertTrue(fila.estaNoFim(numeroDois).avaliar());
	}

	@Test
	public void estaNoFimSeForOUnicoElemento() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		assertTrue(fila.estaNoFim(numeroUm).avaliar());
	}

	@Test
	public void naoEstaNoFimSeOElementoNaoFoiOUltimoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		fila.enfileirar(numeroDois);
		assertFalse(fila.estaNoFim(numeroUm).avaliar());
	}

	@Test
	public void naoEstaNoFimSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		assertFalse(fila.estaNoFim(numeroUm).avaliar());
	}

	@Test
	public void naoEstaNoFimSeOElementoForNulo() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfileirar(numeroUm);
		assertFalse(fila.estaNoFim(numeroNulo).avaliar());
	}

	@Test
	public void inserirAdicionaUmElemento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		assertTrue(colecao.contem(numeroUm).avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(1));
	}

	@Test
	public void inserirAdicionaUmElementoMesmoQueElePertenca() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroUm);
		assertTrue(colecao.contem(numeroUm).avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(3));
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void inserirLancaUmaExecacaoSeOElementoForNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroNulo);
	}

	@Test
	public void removerRemoveOElementoSeElePertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		assertTrue(colecao.remover(numeroUm).avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(1));
	}

	@Test
	public void removerRemoveApenasUmElementoSeExistiremDuplicatas() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroUm);
		assertTrue(colecao.remover(numeroUm).avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(2));
	}

	@Test
	public void removerNaoRemoveOElementoSeEleNaoPertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		assertFalse(colecao.remover(numeroTres).avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(2));
	}

	@Test
	public void removerNaoRemoveSeOElementoForNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		assertFalse(colecao.remover(numeroNulo).avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(2));
	}

	@Test
	public void contemSeOElementoPertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		assertTrue(colecao.contem(numeroDois).avaliar());
	}

	@Test
	public void naoContemSeOElementoNaoPertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		assertFalse(colecao.contem(numeroQuatro).avaliar());
	}

	@Test
	public void naoContemSeNaoExistiremElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		assertFalse(colecao.contem(numeroDois).avaliar());
	}

	@Test
	public void naoContemOElementoNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		assertFalse(colecao.contem(numeroNulo).avaliar());
	}

	@Test
	public void estaVazioSeNaoPossuiElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		assertTrue(colecao.vazio().avaliar());
	}

	@Test
	public void naoEstaVazioSePossuiElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		assertFalse(colecao.vazio().avaliar());
	}

	@Test
	public void iteradorPassaPorTodosElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		colecao.inserir(numeroQuatro);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		assertSame(numeroUm, iterador.iterarProximo());
		assertSame(numeroDois, iterador.iterarProximo());
		assertSame(numeroTres, iterador.iterarProximo());
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(4));
	}

	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		colecao.inserir(numeroQuatro);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		assertSame(numeroUm, iterador.remover());
		iterador.iterarProximo();
		assertSame(numeroDois, iterador.remover());
		iterador.iterarProximo();
		assertSame(numeroTres, iterador.remover());
		iterador.iterarProximo();
		assertSame(numeroQuatro, iterador.remover());
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(colecao.contarElementos().inteiro(), is(0));
	}

	@Test
	public void iteradorPermiteRemoverOPrimeiroElemento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		colecao.inserir(numeroQuatro);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		assertSame(numeroUm, iterador.remover());
		assertThat(colecao.contarElementos().inteiro(), is(3));
		assertSame(numeroDois, colecao.fornecerDoInicio());
	}

	@Test
	public void iteradorPermiteRemoverOSegundoElemento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		colecao.inserir(numeroQuatro);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(numeroDois, iterador.remover());
		assertThat(colecao.contarElementos().inteiro(), is(3));
		assertSame(numeroUm, colecao.fornecerDoInicio());
		assertSame(numeroQuatro, colecao.fornecerDoFim());
	}

	@Test
	public void iteradorPermiteRemoverOUltimoElmento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		colecao.inserir(numeroQuatro);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(numeroQuatro, iterador.remover());
		assertThat(colecao.contarElementos().inteiro(), is(3));
		assertSame(numeroTres, colecao.fornecerDoFim());
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverDuasVezesNaMesmaIteracao() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.remove();
		iterador.remove();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.remove();
	}

	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		assertSame(numeroUm, iterador.substituir(numeroTres));
		iterador.iterarProximo();
		assertSame(numeroDois, iterador.substituir(numeroQuatro));
		assertFalse(iterador.possuiProximo().avaliar());
		iterador = colecao.fornecerIterador();
		assertSame(numeroTres, iterador.iterarProximo());
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertThat(colecao.contarElementos().inteiro(), is(2));
	}

	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		assertSame(numeroUm, iterador.substituir(numeroQuatro));
		assertSame(numeroDois, iterador.iterarProximo());
		assertSame(numeroTres, iterador.iterarProximo());
		assertFalse(iterador.possuiProximo().avaliar());
		iterador = colecao.fornecerIterador();
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertSame(numeroDois, iterador.iterarProximo());
		assertSame(numeroTres, iterador.iterarProximo());
		assertThat(colecao.contarElementos().inteiro(), is(3));
	}

	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		assertSame(numeroUm, iterador.iterarProximo());
		assertSame(numeroDois, iterador.iterarProximo());
		iterador.iterarProximo();
		assertSame(numeroTres, iterador.substituir(numeroQuatro));
		assertFalse(iterador.possuiProximo().avaliar());
		iterador = colecao.fornecerIterador();
		assertSame(numeroUm, iterador.iterarProximo());
		assertSame(numeroDois, iterador.iterarProximo());
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertThat(colecao.contarElementos().inteiro(), is(3));
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroQuatro);
		iterador.substituir(numeroQuatro);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.substituir(numeroQuatro);
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void iteradorNaoPermiteSubstituirPorUmelementoNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroNulo);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoMaisDeUmElemento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
	}

	@Test
	public void iteradorPermiteSubstituirEDepoisRemover() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		assertSame(numeroUm, iterador.substituir(numeroQuatro));
		assertSame(numeroQuatro, iterador.remover());
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(numeroQuatro);
	}

}
