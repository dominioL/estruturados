package br.dominioL.estruturados.testes;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.grafo.Aresta;
import br.dominioL.estruturados.grafo.Digrafo;
import br.dominioL.estruturados.grafo.Vertice;
import br.dominioL.estruturados.grafo.utilidades.Descritor;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

public class TesteDigrafo {
	private Digrafo<Descritor, Descritor> digrafo;
	private Digrafo<Descritor, Descritor> outroDigrafo;
	private Descritor descritorDeVertice;
	private Descritor descritorDeAresta;

	@Before
	public void iniciar() {
		digrafo = Digrafo.criar();
		outroDigrafo = Digrafo.criar();
		descritorDeVertice = Descritor.deVertice();
		descritorDeAresta = Descritor.deAresta();
	}

	@Test
	public void semVertices() {
		assertThat(digrafo.contarVertices(), is(equalTo(0)));
		assertThat(digrafo.contarArestas(), is(equalTo(0)));
	}

	@Test
	public void comUmVertice() {
		Vertice<Descritor> vertice = digrafo.criarVertice(descritorDeVertice);
		assertThat(digrafo.contarVertices(), is(equalTo(1)));
		assertTrue(digrafo.contemVertice(vertice));
	}

	@Test
	public void comUmVerticeIgualDeOutroGrafo() {
		Vertice<Descritor> vertice1 = digrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice2 = outroDigrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice3 = Vertice.criar(descritorDeVertice);
		assertThat(digrafo.contarVertices(), is(equalTo(1)));
		assertThat(digrafo.contarArestas(), is(equalTo(0)));
		assertTrue(digrafo.contemVertice(vertice1));
		assertFalse(digrafo.contemVertice(vertice2));
		assertFalse(digrafo.contemVertice(vertice3));
	}

	@Test
	public void comDoisVerticesComDescritoresIguais() {
		Vertice<Descritor> vertice1 = digrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice2 = digrafo.criarVertice(descritorDeVertice);
		assertThat(digrafo.contarVertices(), is(equalTo(2)));
		assertThat(digrafo.contarArestas(), is(equalTo(0)));
		assertTrue(digrafo.contemVertice(vertice1));
		assertTrue(digrafo.contemVertice(vertice2));
	}

	@Test
	public void comUmaAresta() {
		Vertice<Descritor> vertice1 = digrafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = digrafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta = digrafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		assertThat(digrafo.contarArestas(), is(equalTo(1)));
		assertTrue(digrafo.contemAresta(vertice1, vertice2));
		assertTrue(aresta.igual(digrafo.fornecerAresta(vertice1, vertice2)));
	}

	@Test
	public void comDuasArestasOpostas() {
		Vertice<Descritor> vertice1 = digrafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = digrafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12 = digrafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		Aresta<Descritor> aresta21 = digrafo.criarAresta(vertice2, vertice1, Descritor.deAresta());
		assertThat(digrafo.contarArestas(), is(equalTo(2)));
		assertTrue(digrafo.contemAresta(vertice1, vertice2));
		assertTrue(digrafo.contemAresta(vertice2, vertice1));
		assertTrue(aresta12.igual(digrafo.fornecerAresta(vertice1, vertice2)));
		assertTrue(aresta21.igual(digrafo.fornecerAresta(vertice2, vertice1)));
	}

	@Test
	public void comDuasArestasComMesmaDirecaoComDescritoresDiferentes() {
		Vertice<Descritor> vertice1 = digrafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = digrafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12a = digrafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		Aresta<Descritor> aresta12b = digrafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		assertThat(digrafo.contarArestas(), is(equalTo(1)));
		assertTrue(digrafo.contemAresta(vertice1, vertice2));
		assertFalse(aresta12a.igual(digrafo.fornecerAresta(vertice1, vertice2)));
		assertTrue(aresta12b.igual(digrafo.fornecerAresta(vertice1, vertice2)));
	}

	@Test
	public void comDuasArestasComMesmaDirecaoComDescritoresIguais() {
		Vertice<Descritor> vertice1 = digrafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = digrafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12Primeira = digrafo.criarAresta(vertice1, vertice2, descritorDeAresta);
		Aresta<Descritor> aresta12Segunda = digrafo.criarAresta(vertice1, vertice2, descritorDeAresta);
		assertThat(digrafo.contarArestas(), is(equalTo(1)));
		assertTrue(digrafo.contemAresta(vertice1, vertice2));
		assertFalse(aresta12Primeira.igual(digrafo.fornecerAresta(vertice1, vertice2)));
		assertTrue(aresta12Segunda.igual(digrafo.fornecerAresta(vertice1, vertice2)));
	}

	@Test
	public void contemVerticeComVerticeDeOutroGrafo() {
		fail();
	}

	public void comUmaArestaComVerticeDeOrigemDeOutroGrafo() {}

	public void comUmaArestaComVerticeDeDestinoDeOutroGrafo() {}
	
	public void contemArestaComVerticeDeOrigemDeOutroGrafo() {}
	
	public void contemArestaComVerticeDeDestinoDeOutroGrafo() {}

	public void fornecerArestaComVerticeDeOrigemDeOutroGrafo() {}
	
	public void fornecerArestaComVerticeDeDestinoDeOutroGrafo() {}
}
