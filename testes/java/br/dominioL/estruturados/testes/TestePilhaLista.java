package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.dominioL.estruturados.colecao.pilha.PilhaLista;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoDeEstruturaVazia;
import br.dominioL.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.testes.figuracao.Numero;

public class TestePilhaLista {
	private Numero numeroUm = new Numero(1);
	private Numero numeroDois = new Numero(2);
	private Numero numeroTres = new Numero(3);
	private Numero numeroQuatro = new Numero(4);
	
	@Test
	public void empilharEmPilhaVazia() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		assertSame(pilha.fornecerDoTopo(), numeroUm);
		assertFalse(pilha.vazio());
		assertThat(pilha.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void empilharEmPilhaNaoVazia() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		pilha.empilhar(numeroDois);
		assertSame(pilha.fornecerDoTopo(), numeroDois);
		assertFalse(pilha.vazio());
		assertThat(pilha.fornecerQuantidade(), is(2));
	}
	
	@Test
	public void desempilharUmElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertSame(pilha.desempilhar(), numeroUm);
		assertFalse(pilha.vazio());
		assertThat(pilha.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void desempilharDoisElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertSame(pilha.desempilhar(), numeroUm);
		assertSame(pilha.desempilhar(), numeroDois);
		assertFalse(pilha.vazio());
		assertThat(pilha.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void desempilharTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertSame(pilha.desempilhar(), numeroUm);
		assertSame(pilha.desempilhar(), numeroDois);
		assertSame(pilha.desempilhar(), numeroTres);
		assertTrue(pilha.vazio());
		assertThat(pilha.fornecerQuantidade(), is(0));
	}
	
	@Test(expected = ExcecaoDeElementoNulo.class)
	public void empilharElementoNuloLancaUmaExcecao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(null);
	}
	
	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void desempilharPilhaVaziaLancaUmaExcecao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.desempilhar();
	}
	
	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void fornecerElementoDoTopoDeUmaPilhaVaziaLancaUmaExcecao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.fornecerDoTopo();
	}
	
	@Test
	public void elementoEstaNoTopoSeFoiOUltimoElementoEmpilhado() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertTrue(pilha.estaNoTopo(numeroUm));
	}
	
	@Test
	public void elementoNaoEstaNoTopoSeNaoFoiOUltimoElementoEmpilhado() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertTrue(pilha.estaNoTopo(numeroUm));
	}
	
	@Test
	public void elementoNaoEstaNoTopoSeAPilhaEstaVazia() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		assertFalse(pilha.estaNoTopo(numeroUm));
	}
	
	@Test
	public void inserirUmElementoDuplicadoMantemElementosDuplicados() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroUm);
		assertThat(pilha.fornecerQuantidade(), is(3));
	}
	
	@Test
	public void removerUmElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		assertTrue(pilha.remover(numeroUm));
		assertThat(pilha.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void removerUmElementoDuplicadoRemoveApenasOPrimeiro() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroUm);
		assertTrue(pilha.remover(numeroUm));
		assertThat(pilha.fornecerQuantidade(), is(2));
		assertSame(pilha.fornecerDoTopo(), numeroDois);
	}
	
	@Test
	public void removerUmElementoQueNaoExiste() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		assertFalse(pilha.remover(numeroDois));
		assertThat(pilha.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void contemUmElementoSeElePertence() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroTres);
		assertTrue(pilha.contem(numeroDois));
	}
	
	@Test
	public void naoContemUmElementoSeEleNaoPertence() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroTres);;
		assertFalse(pilha.contem(numeroQuatro));
	}
	
	@Test
	public void fornecerQuantidadeDeElementosQuandoNaoTemElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		assertThat(pilha.fornecerQuantidade(), is(0));
	}
	
	@Test
	public void fornecerQuantidadeDeElementosQuandoTemElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		assertThat(pilha.fornecerQuantidade(), is(2));
	}
	
	@Test
	public void estaVaziaSeNaoPossuiNenhumElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.remover(numeroUm);
		assertTrue(pilha.vazio());
	}
	
	@Test
	public void naoEstaVaziaSePossuiElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		assertFalse(pilha.vazio());
	}
	
	@Test
	public void iteradorPassaPorTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroTres);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		assertSame(iterador.iterarProximo(), numeroTres);
		assertSame(iterador.iterarProximo(), numeroDois);
		assertSame(iterador.iterarProximo(), numeroUm);
		assertFalse(iterador.possuiProximo());
	}
	
	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroUm);
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroDois);
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroTres);
		assertFalse(iterador.possuiProximo());
		assertTrue(pilha.vazio());
	}
	
	@Test
	public void iteradorPermiteRemoverOPrimeiroElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroUm);
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo());
		assertThat(pilha.fornecerQuantidade(), is(2));
	}
	
	@Test
	public void iteradorPermiteRemoverOSegundoElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroDois);
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo());
		assertThat(pilha.fornecerQuantidade(), is(2));
	}
	
	@Test
	public void iteradorPermiteRemoverOUltimoElmento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroTres);
		assertFalse(iterador.possuiProximo());
		assertThat(pilha.fornecerQuantidade(), is(2));
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverDuasVezesNaMesmaIteracao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		pilha.fornecerIterador().remover();
		pilha.fornecerIterador().remover();
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.fornecerIterador().remover();
	}
	
	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.substituir(numeroTres), numeroUm);
		iterador.iterarProximo();
		assertSame(iterador.substituir(numeroQuatro), numeroDois);
		assertFalse(iterador.possuiProximo());
		assertSame(pilha.fornecerDoTopo(), numeroTres);
		pilha.desempilhar();
		assertSame(pilha.fornecerDoTopo(), numeroQuatro);
	}
	
	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo() == numeroUm) {
				iterador.substituir(numeroTres);
			}
		}
		assertSame(pilha.fornecerDoTopo(), numeroTres);
	}
	
	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		pilha.empilhar(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo() == numeroDois) {
				iterador.substituir(numeroTres);
			}
		}
		assertSame(pilha.fornecerDoTopo(), numeroTres);
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroDois);
		iterador.substituir(numeroDois);
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.fornecerIterador().substituir(numeroDois);
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.fornecerIterador().iterarProximo();
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoMaisDeUmElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
	}
	
	@Test
	public void iteradorPermiteSubstituirEDepoisRemover() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroTres);
		assertSame(iterador.remover(), numeroTres);
	}
	
	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(numeroTres);
	}
}
