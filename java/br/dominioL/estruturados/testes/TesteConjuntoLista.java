package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.colecao.conjunto.ConjuntoLista;
import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.testes.figuracao.Pessoa;

public class TesteConjuntoLista {
	private ConjuntoLista<Pessoa> conjunto;
	private ListaEncadeada<Pessoa> lista;
	private Pessoa joao;
	private Pessoa jose;

	@Before
	public void criarFigurantes() {
		joao = new Pessoa("João");
		jose = new Pessoa("José");
		lista = ListaEncadeada.criar();
		conjunto = ConjuntoLista.criar();
	}

	@Test
	public void inserirUmElemento() {
		conjunto.inserir(joao);
		assertThat(conjunto.contarElementos(), is(equalTo(new Integer(1))));
		assertTrue(conjunto.contem(joao));
	}

	@Test
	public void inserirUmElementoDuasVezes() {
		conjunto.inserir(joao);
		conjunto.inserir(joao);
		assertThat(conjunto.contarElementos(), is(equalTo(new Integer(1))));
		assertTrue(conjunto.contem(joao));
	}

	@Test
	public void inserirColecaoComElementosRepetidos() {
		lista.inserir(joao);
		lista.inserir(jose);
		lista.inserir(joao);
		conjunto.inserir(lista);
		assertThat(conjunto.contarElementos(), is(equalTo(new Integer(2))));
		assertTrue(conjunto.contem(joao));
		assertTrue(conjunto.contem(jose));
	}

	@Test
	public void removerUmElementoExistente() {
		conjunto.inserir(joao);
		assertTrue(conjunto.remover(joao));
		assertThat(conjunto.contarElementos(), is(equalTo(new Integer(0))));
		assertFalse(conjunto.contem(joao));
	}

	@Test
	public void removerUmElementoInexistente() {
		conjunto.inserir(joao);
		assertFalse(conjunto.remover(jose));
		assertThat(conjunto.contarElementos(), is(equalTo(new Integer(1))));
		assertFalse(conjunto.contem(jose));
		assertTrue(conjunto.contem(joao));
	}
}
