package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.dominioL.estruturados.colecao.fila.FilaLista;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoDeEstruturaVazia;
import br.dominioL.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.testes.figuracao.Numero;

public class TesteFilaLista {
	private Numero numeroUm = new Numero(1);
	private Numero numeroDois = new Numero(2);
	private Numero numeroTres = new Numero(3);
	private Numero numeroQuatro = new Numero(4);
	private Numero numeroNulo = null;

	@Test
	public void enfileirarAdicionaUmElementoNoFim() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertSame(fila.fornecerDoFim(), numeroDois);
		assertThat(fila.fornecerQuantidade(), is(2));
	}

	@Test
	public void enfileirarAdicionaUmElementoNoFimMesmoQueElePertenca() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		fila.enfilerar(numeroUm);
		assertSame(fila.fornecerDoFim(), numeroUm);
		assertThat(fila.fornecerQuantidade(), is(3));
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void enfileirarLancaUmaExcecaoSeOElementoForNulo() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroNulo);
	}

	@Test
	public void desenfileirarRemoveUmElementoDoInicio() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertSame(fila.desenfilerar(), numeroUm);
		assertThat(fila.fornecerQuantidade(), is(1));
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void desenfileirarLancaUmExcecaoSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.desenfilerar();
	}

	@Test
	public void fornecerDoInicioForneceOPrimeiroElementoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertSame(fila.fornecerDoInicio(), numeroUm);
		assertThat(fila.fornecerQuantidade(), is(2));
	}

	@Test
	public void fornecerDoFimForneceOUltimoElementoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertSame(fila.fornecerDoFim(), numeroDois);
		assertThat(fila.fornecerQuantidade(), is(2));
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void fornecerDoInicioLancaUmaExcecaoSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.fornecerDoInicio();
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void fornecerDoFimLancaUmaExcecaoSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.fornecerDoFim();
	}

	@Test
	public void estaNoInicioSeOElementoFoiOPrimeiroEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertTrue(fila.estaNoInicio(numeroUm));
	}

	@Test
	public void estaNoInicioSeForOUnicoElemento() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		assertTrue(fila.estaNoInicio(numeroUm));
	}

	@Test
	public void naoEstaNoInicioSeOElementoNaoFoiOPrimeiroEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertFalse(fila.estaNoInicio(numeroDois));
	}

	@Test
	public void naoEstaNoInicioSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		assertFalse(fila.estaNoInicio(numeroUm));
	}

	@Test
	public void naoEstaNoInicioSeOElementoForNulo() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		assertFalse(fila.estaNoInicio(numeroNulo));
	}

	@Test
	public void estaNoFimSeOElementoFoiOUltimoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertTrue(fila.estaNoFim(numeroDois));
	}

	@Test
	public void estaNoFimSeForOUnicoElemento() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		assertTrue(fila.estaNoFim(numeroUm));
	}

	@Test
	public void naoEstaNoFimSeOElementoNaoFoiOUltimoEnfileirado() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		fila.enfilerar(numeroDois);
		assertFalse(fila.estaNoFim(numeroUm));
	}

	@Test
	public void naoEstaNoFimSeNaoExistiremElementos() {
		FilaLista<Numero> fila = FilaLista.criar();
		assertFalse(fila.estaNoFim(numeroUm));
	}

	@Test
	public void naoEstaNoFimSeOElementoForNulo() {
		FilaLista<Numero> fila = FilaLista.criar();
		fila.enfilerar(numeroUm);
		assertFalse(fila.estaNoFim(numeroNulo));
	}

	@Test
	public void inserirAdicionaUmElemento() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		assertTrue(colecao.contem(numeroUm));
		assertThat(colecao.fornecerQuantidade(), is(1));
	}

	@Test
	public void inserirAdicionaUmElementoMesmoQueElePertenca() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroUm);
		assertTrue(colecao.contem(numeroUm));
		assertThat(colecao.fornecerQuantidade(), is(3));
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void inserirLancaUmaExecacaoSeOElementoForNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroNulo);
	}

	@Test
	public void removerRemoveOElementoSeElePertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		assertTrue(colecao.remover(numeroUm));
		assertThat(colecao.fornecerQuantidade(), is(1));
	}

	@Test
	public void removerRemoveApenasUmElementoSeExistiremDuplicatas() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroUm);
		assertTrue(colecao.remover(numeroUm));
		assertThat(colecao.fornecerQuantidade(), is(2));
	}

	@Test
	public void removerNaoRemoveOElementoSeEleNaoPertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		assertFalse(colecao.remover(numeroTres));
		assertThat(colecao.fornecerQuantidade(), is(2));
	}

	@Test
	public void removerNaoRemoveSeOElementoForNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		assertFalse(colecao.remover(numeroNulo));
		assertThat(colecao.fornecerQuantidade(), is(2));
	}

	@Test
	public void contemSeOElementoPertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		assertTrue(colecao.contem(numeroDois));
	}

	@Test
	public void naoContemSeOElementoNaoPertence() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		assertFalse(colecao.contem(numeroQuatro));
	}

	@Test
	public void naoContemSeNaoExistiremElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		assertFalse(colecao.contem(numeroDois));
	}

	@Test
	public void naoContemOElementoNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		colecao.inserir(numeroTres);
		assertFalse(colecao.contem(numeroNulo));
	}

	@Test
	public void estaVazioSeNaoPossuiElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		assertTrue(colecao.vazio());
	}

	@Test
	public void naoEstaVazioSePossuiElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		assertFalse(colecao.vazio());
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
		assertFalse(iterador.possuiProximo());
		assertThat(colecao.fornecerQuantidade(), is(4));
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
		assertFalse(iterador.possuiProximo());
		assertThat(colecao.fornecerQuantidade(), is(0));
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
		assertThat(colecao.fornecerQuantidade(), is(3));
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
		assertThat(colecao.fornecerQuantidade(), is(3));
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
		assertThat(colecao.fornecerQuantidade(), is(3));
		assertSame(numeroTres, colecao.fornecerDoFim());
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
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

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
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
		assertFalse(iterador.possuiProximo());
		iterador = colecao.fornecerIterador();
		assertSame(numeroTres, iterador.iterarProximo());
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertThat(colecao.fornecerQuantidade(), is(2));
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
		assertFalse(iterador.possuiProximo());
		iterador = colecao.fornecerIterador();
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertSame(numeroDois, iterador.iterarProximo());
		assertSame(numeroTres, iterador.iterarProximo());
		assertThat(colecao.fornecerQuantidade(), is(3));
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
		assertFalse(iterador.possuiProximo());
		iterador = colecao.fornecerIterador();
		assertSame(numeroUm, iterador.iterarProximo());
		assertSame(numeroDois, iterador.iterarProximo());
		assertSame(numeroQuatro, iterador.iterarProximo());
		assertThat(colecao.fornecerQuantidade(), is(3));
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroQuatro);
		iterador.substituir(numeroQuatro);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.substituir(numeroQuatro);
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void iteradorNaoPermiteSubstituirPorUmelementoNulo() {
		FilaLista<Numero> colecao = FilaLista.criar();
		colecao.inserir(numeroUm);
		colecao.inserir(numeroDois);
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroNulo);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		FilaLista<Numero> colecao = FilaLista.criar();
		Iterador<Numero> iterador = colecao.fornecerIterador();
		iterador.iterarProximo();
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
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

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
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
