package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.grafo.Aresta;
import br.dominioL.estruturados.grafo.Grafo;
import br.dominioL.estruturados.grafo.Vertice;
import br.dominioL.estruturados.grafo.excecoes.ExcecaoVerticeNaoPertencenteAoGrafo;
import br.dominioL.estruturados.grafo.utilidades.Descritor;

public final class TesteGrafo {
	private Grafo<Descritor, Descritor> grafo;
	private Grafo<Descritor, Descritor> outroGrafo;
	private Descritor descritorDeVertice;
	private Descritor descritorDeAresta;

	@Before
	public void iniciar() {
		grafo = Grafo.criar();
		outroGrafo = Grafo.criar();
		descritorDeVertice = Descritor.deVertice();
		descritorDeAresta = Descritor.deAresta();
	}

	@Test
	public void semVertices() {
		assertThat(grafo.contarVertices(), is(equalTo(0)));
		assertThat(grafo.contarArestas(), is(equalTo(0)));
	}

	@Test
	public void comUmVertice() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.contarVertices(), is(equalTo(1)));
		assertTrue(grafo.contemVertice(vertice));
	}

	@Test
	public void comUmVerticeIgualDeOutroGrafo() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice2 = outroGrafo.criarVertice(descritorDeVertice);
		assertThat(grafo.contarVertices(), is(equalTo(1)));
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertTrue(grafo.contemVertice(vertice1));
		assertFalse(grafo.contemVertice(vertice2));
	}

	@Test
	public void comDoisVerticesComDescritoresIguais() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice2 = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.contarVertices(), is(equalTo(2)));
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertTrue(grafo.contemVertice(vertice1));
		assertTrue(grafo.contemVertice(vertice2));
	}

	@Test
	public void comUmaAresta() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertTrue(grafo.contemAresta(vertice1, vertice2));
		assertTrue(grafo.contemAresta(vertice2, vertice1));
		assertTrue(aresta.igual(grafo.fornecerAresta(vertice1, vertice2)));
		assertTrue(aresta.igual(grafo.fornecerAresta(vertice2, vertice1)));
	}

	@Test
	public void comDuasArestasOpostas() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12 = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		Aresta<Descritor> aresta21 = grafo.criarAresta(vertice2, vertice1, Descritor.deAresta());
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertTrue(grafo.contemAresta(vertice1, vertice2));
		assertTrue(grafo.contemAresta(vertice2, vertice1));
		assertFalse(aresta12.igual(grafo.fornecerAresta(vertice1, vertice2)));
		assertFalse(aresta12.igual(grafo.fornecerAresta(vertice2, vertice1)));
		assertTrue(aresta21.igual(grafo.fornecerAresta(vertice2, vertice1)));
		assertTrue(aresta21.igual(grafo.fornecerAresta(vertice1, vertice2)));
	}

	@Test
	public void comDuasArestasComMesmaDirecaoComDescritoresDiferentes() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12a = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		Aresta<Descritor> aresta12b = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertTrue(grafo.contemAresta(vertice1, vertice2));
		assertTrue(grafo.contemAresta(vertice2, vertice1));
		assertFalse(aresta12a.igual(grafo.fornecerAresta(vertice1, vertice2)));
		assertFalse(aresta12a.igual(grafo.fornecerAresta(vertice2, vertice1)));
		assertTrue(aresta12b.igual(grafo.fornecerAresta(vertice1, vertice2)));
		assertTrue(aresta12b.igual(grafo.fornecerAresta(vertice2, vertice1)));
	}

	@Test
	public void comDuasArestasComMesmaDirecaoComDescritoresIguais() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12Primeira = grafo.criarAresta(vertice1, vertice2, descritorDeAresta);
		Aresta<Descritor> aresta12Segunda = grafo.criarAresta(vertice1, vertice2, descritorDeAresta);
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertTrue(grafo.contemAresta(vertice1, vertice2));
		assertTrue(grafo.contemAresta(vertice2, vertice1));
		assertFalse(aresta12Primeira.igual(grafo.fornecerAresta(vertice1, vertice2)));
		assertFalse(aresta12Primeira.igual(grafo.fornecerAresta(vertice2, vertice1)));
		assertTrue(aresta12Segunda.igual(grafo.fornecerAresta(vertice1, vertice2)));
		assertTrue(aresta12Segunda.igual(grafo.fornecerAresta(vertice2, vertice1)));
	}

	@Test
	public void comArestaReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta = grafo.criarAresta(vertice, vertice, descritorDeAresta);
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertTrue(grafo.contemAresta(vertice, vertice));
		assertTrue(aresta.igual(grafo.fornecerAresta(vertice, vertice)));
	}

	@Test
	public void naoContemVerticeDeOutroGrafo() {
		Vertice<Descritor> vertice = outroGrafo.criarVertice(descritorDeVertice);
		assertFalse(grafo.contemVertice(vertice));
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void comUmaArestaComVerticeDeOrigemDeOutroGrafo() {
		Vertice<Descritor> origem = outroGrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void comUmaArestaComVerticeDeDestinoDeOutroGrafo() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = outroGrafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void comUmaArestaComVerticeDeOrigemRemovido() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(origem);
		grafo.criarAresta(origem, destino, descritorDeAresta);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void comUmaArestaComVerticeDeDestinoRemovido() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(destino);
		grafo.criarAresta(origem, destino, descritorDeAresta);
	}

	@Test
	public void naoContemArestaComVerticeDeOrigemDeOutroGrafo() {
		Vertice<Descritor> origem = outroGrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		assertFalse(grafo.contemAresta(origem, destino));
	}

	@Test
	public void naoContemArestaComVerticeDeDestinoDeOutroGrafo() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = outroGrafo.criarVertice(descritorDeVertice);
		assertFalse(grafo.contemAresta(origem, destino));
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void fornecerArestaComVerticeDeOrigemDeOutroGrafo() {
		Vertice<Descritor> origem = outroGrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.fornecerAresta(origem, destino);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void fornecerArestaComVerticeDeDestinoDeOutroGrafo() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = outroGrafo.criarVertice(descritorDeVertice);
		grafo.fornecerAresta(origem, destino);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void fornecerArestaComVerticeDeOrigemRemovido() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(origem);
		grafo.fornecerAresta(origem, destino);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void fornecerArestaComVerticeDeDestinoRemovido() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(destino);
		grafo.fornecerAresta(origem, destino);
	}

	@Test
	public void adjacentesDoVerticeSemArestas() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.adjacentes(vertice).contarElementos(), is(equalTo(0)));
	}

	@Test
	public void adjacentesDoVerticeComArestaReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeVertice);
		assertThat(grafo.adjacentes(vertice).contarElementos(), is(equalTo(1)));
		assertTrue(vertice.igual(grafo.adjacentes(vertice).fornecerDoInicio()));
	}

	@Test
	public void adjacentesDoVerticeComArestaParaOutroVertice() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeVertice);
		assertThat(grafo.adjacentes(origem).contarElementos(), is(equalTo(1)));
		assertThat(grafo.adjacentes(destino).contarElementos(), is(equalTo(1)));
		assertTrue(origem.igual(grafo.adjacentes(destino).fornecerDoInicio()));
		;
		assertTrue(destino.igual(grafo.adjacentes(origem).fornecerDoInicio()));
	}

	@Test
	public void sucessoresDoVerticeComArestaDeSaidaParaDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeVertice);
		grafo.criarAresta(origem, origem, descritorDeVertice);
		assertThat(grafo.adjacentes(origem).contarElementos(), is(equalTo(2)));
		assertThat(grafo.adjacentes(destino).contarElementos(), is(equalTo(1)));
		assertTrue(grafo.adjacentes(origem).contem(origem));
		assertTrue(grafo.adjacentes(origem).contem(destino));
		assertTrue(grafo.adjacentes(destino).contem(origem));
		assertFalse(grafo.adjacentes(destino).contem(destino));
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void adjacentesDeVerticeDeOutroGrafo() {
		Vertice<Descritor> verticeDeOutroGrafo = outroGrafo.criarVertice(descritorDeVertice);
		grafo.adjacentes(verticeDeOutroGrafo);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void adjacentesDeVerticeRemovido() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		grafo.adjacentes(vertice);
	}

	@Test
	public void removerVertice() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		assertFalse(grafo.contemVertice(vertice));
		assertThat(grafo.contarVertices(), is(equalTo(0)));
	}

	@Test
	public void removerVerticeRelfexivo() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeAresta);
		grafo.removerVertice(vertice);
		assertFalse(grafo.contemVertice(vertice));
		assertThat(grafo.contarVertices(), is(equalTo(0)));
		assertThat(grafo.contarArestas(), is(equalTo(0)));
	}

	@Test
	public void removerVerticeComArestaParaDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(origem, origem, descritorDeAresta);
		grafo.removerVertice(origem);
		assertFalse(grafo.contemVertice(origem));
		assertTrue(grafo.contemVertice(destino));
		assertThat(grafo.contarVertices(), is(equalTo(1)));
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertThat(grafo.adjacentes(destino).contarElementos(), is(equalTo(0)));
		assertFalse(grafo.adjacentes(destino).contem(origem));
		assertFalse(grafo.adjacentes(destino).contem(destino));
	}

	@Test
	public void removerVerticeComArestaParaDoisVerticesMantendoUmaAresta() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(origem, origem, descritorDeAresta);
		grafo.removerVertice(destino);
		assertTrue(grafo.contemVertice(origem));
		assertFalse(grafo.contemVertice(destino));
		assertThat(grafo.contarVertices(), is(equalTo(1)));
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertThat(grafo.adjacentes(origem).contarElementos(), is(equalTo(1)));
		assertTrue(grafo.adjacentes(origem).contem(origem));
		assertFalse(grafo.adjacentes(origem).contem(destino));
	}

	@Test
	public void removerVerticeComArestaDeDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(destino, destino, descritorDeAresta);
		grafo.removerVertice(destino);
		assertFalse(grafo.contemVertice(destino));
		assertTrue(grafo.contemVertice(origem));
		assertThat(grafo.contarVertices(), is(equalTo(1)));
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertThat(grafo.adjacentes(origem).contarElementos(), is(equalTo(0)));
		assertFalse(grafo.adjacentes(origem).contem(origem));
		assertFalse(grafo.adjacentes(origem).contem(destino));
	}

	@Test
	public void removerVerticeComArestaDeDoisVerticesMantendoUmaAresta() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(destino, destino, descritorDeAresta);
		grafo.removerVertice(origem);
		assertTrue(grafo.contemVertice(destino));
		assertFalse(grafo.contemVertice(origem));
		assertThat(grafo.contarVertices(), is(equalTo(1)));
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertThat(grafo.adjacentes(destino).contarElementos(), is(equalTo(1)));
		assertFalse(grafo.adjacentes(destino).contem(origem));
		assertTrue(grafo.adjacentes(destino).contem(destino));
	}

	@Test
	public void removerERecolocarVertice() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		Vertice<Descritor> verticeNovo = grafo.criarVertice(descritorDeVertice);
		assertFalse(vertice.igual(verticeNovo));
		assertFalse(grafo.contemVertice(vertice));
		assertTrue(grafo.contemVertice(verticeNovo));
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void removerVerticeDeOutroGrafo() {
		Vertice<Descritor> verticeDeOutroGrafo = outroGrafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(verticeDeOutroGrafo);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void removerVerticeRemovido() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		grafo.removerVertice(vertice);
	}

	@Test
	public void removerArestaNaoExistente() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerAresta(origem, destino);
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertThat(grafo.adjacentes(origem).contarElementos(), is(equalTo(0)));
		assertThat(grafo.adjacentes(destino).contarElementos(), is(equalTo(0)));
		assertFalse(grafo.contemAresta(origem, destino));
		assertFalse(grafo.contemAresta(destino, origem));
		assertNull(grafo.fornecerAresta(origem, destino));
		assertNull(grafo.fornecerAresta(destino, origem));
	}

	@Test
	public void removerArestaReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeAresta);
		grafo.removerAresta(vertice, vertice);
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertThat(grafo.adjacentes(vertice).contarElementos(), is(equalTo(0)));
		assertFalse(grafo.contemAresta(vertice, vertice));
		assertNull(grafo.fornecerAresta(vertice, vertice));
	}

	@Test
	public void removerArestaEntreDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(destino, origem, descritorDeAresta);
		grafo.removerAresta(destino, origem);
		assertThat(grafo.contarArestas(), is(equalTo(0)));
		assertThat(grafo.adjacentes(origem).contarElementos(), is(equalTo(0)));
		assertThat(grafo.adjacentes(destino).contarElementos(), is(equalTo(0)));
		assertFalse(grafo.contemAresta(origem, destino));
		assertFalse(grafo.contemAresta(destino, origem));
		assertNull(grafo.fornecerAresta(origem, destino));
		assertNull(grafo.fornecerAresta(destino, origem));
	}

	@Test
	public void removerERecolocarAresta() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		Aresta<Descritor> aresta = grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.removerAresta(destino, origem);
		Aresta<Descritor> arestaNova = grafo.criarAresta(origem, destino, descritorDeAresta);
		assertThat(grafo.contarArestas(), is(equalTo(1)));
		assertFalse(aresta.igual(arestaNova));
		assertFalse(grafo.fornecerAresta(origem, destino).igual(aresta));
		assertTrue(grafo.fornecerAresta(origem, destino).igual(arestaNova));
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void removerArestaComVerticeDeOrigemDeOutroGrafo() {
		Vertice<Descritor> origem = outroGrafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerAresta(origem, destino);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void removerArestaComVerticeDeDestinoDeOutroGrafo() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = outroGrafo.criarVertice(descritorDeVertice);
		grafo.removerAresta(origem, destino);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void removerArestaComVerticeDeOrigemRemovido() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(origem);
		grafo.removerAresta(origem, destino);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void removerArestaComVerticeDeDestinoRemovido() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(destino);
		grafo.removerAresta(origem, destino);
	}
}
