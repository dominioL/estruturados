package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.grafo.Aresta;
import br.dominioL.estruturados.grafo.Digrafo;
import br.dominioL.estruturados.grafo.Vertice;
import br.dominioL.estruturados.grafo.excecoes.ExcecaoVerticeNaoPertencenteAoGrafo;
import br.dominioL.estruturados.grafo.utilidades.Descritor;

public final class TesteDigrafo {

	private Digrafo<Descritor, Descritor> grafo;
	private Digrafo<Descritor, Descritor> outroGrafo;
	private Descritor descritorDeVertice;
	private Descritor descritorDeAresta;

	@Before
	public void iniciar() {
		grafo = Digrafo.criar();
		outroGrafo = Digrafo.criar();
		descritorDeVertice = Descritor.deVertice();
		descritorDeAresta = Descritor.deAresta();
	}

	@Test
	public void semVertices() {
		assertThat(grafo.contarVertices(), is(equalTo(Numero.zero())));
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
	}

	@Test
	public void comUmVertice() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.contarVertices(), is(equalTo(Numero.um())));
		assertTrue(grafo.contemVertice(vertice).avaliar());
	}

	@Test
	public void comUmVerticeIgualDeOutroGrafo() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice2 = outroGrafo.criarVertice(descritorDeVertice);
		assertThat(grafo.contarVertices(), is(equalTo(Numero.um())));
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
		assertTrue(grafo.contemVertice(vertice1).avaliar());
		assertFalse(grafo.contemVertice(vertice2).avaliar());
	}

	@Test
	public void comDoisVerticesComDescritoresIguais() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> vertice2 = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.contarVertices(), is(equalTo(Numero.criar(2))));
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
		assertTrue(grafo.contemVertice(vertice1).avaliar());
		assertTrue(grafo.contemVertice(vertice2).avaliar());
	}

	@Test
	public void comUmaAresta() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		assertThat(grafo.contarArestas(), is(equalTo(Numero.um())));
		assertFalse(grafo.contemAresta(vertice2, vertice1).avaliar());
		assertTrue(grafo.contemAresta(vertice1, vertice2).avaliar());
		assertTrue(aresta.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
	}

	@Test
	public void comDuasArestasOpostas() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12 = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		Aresta<Descritor> aresta21 = grafo.criarAresta(vertice2, vertice1, Descritor.deAresta());
		assertThat(grafo.contarArestas(), is(equalTo(Numero.criar(2))));
		assertTrue(grafo.contemAresta(vertice1, vertice2).avaliar());
		assertTrue(grafo.contemAresta(vertice2, vertice1).avaliar());
		assertTrue(aresta12.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
		assertFalse(aresta12.igual(grafo.fornecerAresta(vertice2, vertice1)).avaliar());
		assertTrue(aresta21.igual(grafo.fornecerAresta(vertice2, vertice1)).avaliar());
		assertFalse(aresta21.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
	}

	@Test
	public void comDuasArestasComMesmaDirecaoComDescritoresDiferentes() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12a = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		Aresta<Descritor> aresta12b = grafo.criarAresta(vertice1, vertice2, Descritor.deAresta());
		assertThat(grafo.contarArestas(), is(equalTo(Numero.um())));
		assertTrue(grafo.contemAresta(vertice1, vertice2).avaliar());
		assertFalse(grafo.contemAresta(vertice2, vertice1).avaliar());
		assertFalse(aresta12a.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
		assertTrue(aresta12b.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
	}

	@Test
	public void comDuasArestasComMesmaDirecaoComDescritoresIguais() {
		Vertice<Descritor> vertice1 = grafo.criarVertice(Descritor.deVertice());
		Vertice<Descritor> vertice2 = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta12Primeira = grafo.criarAresta(vertice1, vertice2, descritorDeAresta);
		Aresta<Descritor> aresta12Segunda = grafo.criarAresta(vertice1, vertice2, descritorDeAresta);
		assertThat(grafo.contarArestas(), is(equalTo(Numero.um())));
		assertTrue(grafo.contemAresta(vertice1, vertice2).avaliar());
		assertFalse(grafo.contemAresta(vertice2, vertice1).avaliar());
		assertFalse(aresta12Primeira.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
		assertTrue(aresta12Segunda.igual(grafo.fornecerAresta(vertice1, vertice2)).avaliar());
	}

	@Test
	public void comArestasReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(Descritor.deVertice());
		Aresta<Descritor> aresta = grafo.criarAresta(vertice, vertice, descritorDeAresta);
		assertThat(grafo.contarArestas(), is(equalTo(Numero.um())));
		assertTrue(grafo.contemAresta(vertice, vertice).avaliar());
		assertTrue(aresta.igual(grafo.fornecerAresta(vertice, vertice)).avaliar());
	}

	@Test
	public void naoContemVerticeDeOutroGrafo() {
		Vertice<Descritor> vertice = outroGrafo.criarVertice(descritorDeVertice);
		assertFalse(grafo.contemVertice(vertice).avaliar());
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
		assertFalse(grafo.contemAresta(origem, destino).avaliar());
	}

	@Test
	public void naoContemArestaComVerticeDeDestinoDeOutroGrafo() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = outroGrafo.criarVertice(descritorDeVertice);
		assertFalse(grafo.contemAresta(origem, destino).avaliar());
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
	public void sucessoresDoVerticeSemArestasDeSaida() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.sucessores(vertice).contarElementos(), is(equalTo(Numero.zero())));
	}

	@Test
	public void sucessoresDoVerticeComArestaDeSaidaReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeVertice);
		assertThat(grafo.sucessores(vertice).contarElementos(), is(equalTo(Numero.um())));
		assertTrue(vertice.igual(grafo.sucessores(vertice).fornecerDoInicio()).avaliar());
	}

	@Test
	public void sucessoresDoVerticeComArestaDeSaida() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeVertice);
		assertThat(grafo.sucessores(origem).contarElementos(), is(equalTo(Numero.um())));
		assertThat(grafo.sucessores(destino).contarElementos(), is(equalTo(Numero.zero())));
		assertTrue(destino.igual(grafo.sucessores(origem).fornecerDoInicio()).avaliar());
	}

	@Test
	public void sucessoresDoVerticeComArestaDeSaidaParaDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeVertice);
		grafo.criarAresta(origem, origem, descritorDeVertice);
		assertThat(grafo.sucessores(origem).contarElementos(), is(equalTo(Numero.criar(2))));
		assertThat(grafo.sucessores(destino).contarElementos(), is(equalTo(Numero.zero())));
		assertTrue(grafo.sucessores(origem).contem(origem).avaliar());
		assertTrue(grafo.sucessores(origem).contem(destino).avaliar());
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void sucessoresDeVerticeDeOutroGrafo() {
		Vertice<Descritor> verticeDeOutroGrafo = outroGrafo.criarVertice(descritorDeVertice);
		grafo.sucessores(verticeDeOutroGrafo);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void sucessoresDeVerticeRemovido() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		grafo.sucessores(vertice);
	}

	@Test
	public void antecessoresDeVerticeSemArestasDeEntrada() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		assertThat(grafo.antecessores(vertice).contarElementos(), is(equalTo(Numero.zero())));
	}

	@Test
	public void antecessoresComArestaDeEntradaReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeAresta);
		assertThat(grafo.antecessores(vertice).contarElementos(), is(equalTo(Numero.um())));
		assertTrue(vertice.igual(grafo.antecessores(vertice).fornecerDoInicio()).avaliar());
	}

	@Test
	public void antecessoresComArestaDeEntrada() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		assertThat(grafo.antecessores(destino).contarElementos(), is(equalTo(Numero.um())));
		assertTrue(origem.igual(grafo.antecessores(destino).fornecerDoInicio()).avaliar());
	}

	@Test
	public void antecessoresComArestasDeEntradaDeDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(destino, destino, descritorDeAresta);
		assertThat(grafo.antecessores(destino).contarElementos(), is(equalTo(Numero.criar(2))));
		assertTrue(grafo.antecessores(destino).contem(origem).avaliar());
		assertTrue(grafo.antecessores(destino).contem(destino).avaliar());
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void antecessoresDeVerticeDeOutroGrafo() {
		Vertice<Descritor> verticeDeOutroGrafo = outroGrafo.criarVertice(descritorDeVertice);
		grafo.antecessores(verticeDeOutroGrafo);
	}

	@Test(expected = ExcecaoVerticeNaoPertencenteAoGrafo.class)
	public void antecessoresDeVerticeRemovido() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		grafo.antecessores(vertice);
	}

	@Test
	public void removerVertice() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		assertFalse(grafo.contemVertice(vertice).avaliar());
		assertThat(grafo.contarVertices(), is(equalTo(Numero.zero())));
	}

	@Test
	public void removerVerticeRelfexivo() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeAresta);
		grafo.removerVertice(vertice);
		assertFalse(grafo.contemVertice(vertice).avaliar());
		assertThat(grafo.contarVertices(), is(equalTo(Numero.zero())));
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
	}

	@Test
	public void removerVerticeComArestaDeSaidaParaDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(origem, origem, descritorDeAresta);
		grafo.removerVertice(origem);
		assertFalse(grafo.contemVertice(origem).avaliar());
		assertThat(grafo.contarVertices(), is(equalTo(Numero.um())));
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
		assertThat(grafo.sucessores(destino).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.antecessores(destino).contarElementos(), is(equalTo(Numero.zero())));
	}

	@Test
	public void removerVerticeComArestaDeEntradaDeDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(destino, destino, descritorDeAresta);
		grafo.removerVertice(destino);
		assertFalse(grafo.contemVertice(destino).avaliar());
		assertThat(grafo.contarVertices(), is(equalTo(Numero.um())));
		assertThat(grafo.sucessores(origem).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.antecessores(origem).contarElementos(), is(equalTo(Numero.zero())));
	}

	@Test
	public void removerERecolocarVertice() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.removerVertice(vertice);
		Vertice<Descritor> verticeNovo = grafo.criarVertice(descritorDeVertice);
		assertFalse(vertice.igual(verticeNovo).avaliar());
		assertFalse(grafo.contemVertice(vertice).avaliar());
		assertTrue(grafo.contemVertice(verticeNovo).avaliar());
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
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
		assertThat(grafo.sucessores(origem).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.antecessores(origem).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.sucessores(destino).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.antecessores(destino).contarElementos(), is(equalTo(Numero.zero())));
		assertFalse(grafo.contemAresta(origem, destino).avaliar());
		assertFalse(grafo.contemAresta(destino, origem).avaliar());
		assertNull(grafo.fornecerAresta(origem, destino));
		assertNull(grafo.fornecerAresta(destino, origem));
	}

	@Test
	public void removerArestaReflexiva() {
		Vertice<Descritor> vertice = grafo.criarVertice(descritorDeVertice);
		grafo.criarAresta(vertice, vertice, descritorDeAresta);
		grafo.removerAresta(vertice, vertice);
		assertThat(grafo.contarArestas(), is(equalTo(Numero.zero())));
		assertThat(grafo.sucessores(vertice).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.antecessores(vertice).contarElementos(), is(equalTo(Numero.zero())));
		assertFalse(grafo.contemAresta(vertice, vertice).avaliar());
		assertNull(grafo.fornecerAresta(vertice, vertice));
	}

	@Test
	public void removerArestaEntreDoisVertices() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		Aresta<Descritor> aresta = grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.criarAresta(destino, origem, descritorDeAresta);
		grafo.removerAresta(destino, origem);
		assertThat(grafo.contarArestas(), is(equalTo(Numero.um())));
		assertThat(grafo.sucessores(origem).contarElementos(), is(equalTo(Numero.um())));
		assertThat(grafo.antecessores(origem).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.sucessores(destino).contarElementos(), is(equalTo(Numero.zero())));
		assertThat(grafo.antecessores(destino).contarElementos(), is(equalTo(Numero.um())));
		assertTrue(grafo.contemAresta(origem, destino).avaliar());
		assertFalse(grafo.contemAresta(destino, origem).avaliar());
		assertTrue(aresta.igual(grafo.fornecerAresta(origem, destino)).avaliar());
		assertNull(grafo.fornecerAresta(destino, origem));
	}

	@Test
	public void removerERecolocarAresta() {
		Vertice<Descritor> origem = grafo.criarVertice(descritorDeVertice);
		Vertice<Descritor> destino = grafo.criarVertice(descritorDeVertice);
		Aresta<Descritor> aresta = grafo.criarAresta(origem, destino, descritorDeAresta);
		grafo.removerAresta(destino, origem);
		Aresta<Descritor> arestaNova = grafo.criarAresta(origem, destino, descritorDeAresta);
		assertThat(grafo.contarArestas(), is(equalTo(Numero.um())));
		assertFalse(aresta.igual(arestaNova).avaliar());
		assertFalse(grafo.fornecerAresta(origem, destino).igual(aresta).avaliar());
		assertTrue(grafo.fornecerAresta(origem, destino).igual(arestaNova).avaliar());
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
