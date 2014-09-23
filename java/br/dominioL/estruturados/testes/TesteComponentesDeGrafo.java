package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.grafo.Aresta;
import br.dominioL.estruturados.grafo.Cor;
import br.dominioL.estruturados.grafo.Digrafo;
import br.dominioL.estruturados.grafo.Vertice;
import br.dominioL.estruturados.grafo.utilidades.Descritor;

public final class TesteComponentesDeGrafo {

	private Descritor a;
	private Descritor a1;
	private Descritor a2;
	private Descritor b;
	private Digrafo<Descritor, Descritor> grafo;

	@Before
	public void iniciar() {
		grafo = Digrafo.criar();
		a = new Descritor(Texto.criar("a"));
		a1 = new Descritor(Texto.criar("a"));
		a2 = new Descritor(Texto.criar("a"));
		b = new Descritor(Texto.criar("b"));
		Descritor.reiniciarContagem();
	}

	@Test
	public void descritorIgualAEleMesmo() {
		assertTrue(a.igual(a).avaliar());
	}

	@Test
	public void descritoresDiferentes() {
		assertFalse(a.igual(b).avaliar());
		assertFalse(b.igual(a).avaliar());
	}

	@Test
	public void descritoresguais() {
		assertTrue(a1.igual(a2).avaliar());
		assertTrue(a2.igual(a1).avaliar());
	}

	@Test
	public void verticeIGualAEleMesmo() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		assertTrue(vertice.igual(vertice).avaliar());
	}

	@Test
	public void verticesDiferentesComDescritoresDiferentes() {
		Vertice<Descritor> verticeA = grafo.criarVertice(a);
		Vertice<Descritor> verticeB = grafo.criarVertice(a);
		assertFalse(verticeA.igual(verticeB).avaliar());
		assertFalse(verticeB.igual(verticeA).avaliar());
	}

	@Test
	public void verticesDiferentesComDescritoresIguais() {
		Vertice<Descritor> verticeA = grafo.criarVertice(a);
		Vertice<Descritor> verticeB = grafo.criarVertice(a);
		assertFalse(verticeA.igual(verticeB).avaliar());
		assertFalse(verticeB.igual(verticeA).avaliar());
	}

	@Test
	public void arestaIgualAElaMesma() {
		Aresta<Descritor> aresta = grafo.criarAresta(grafo.criarVertice(a), grafo.criarVertice(b), a1);
		assertTrue(aresta.igual(aresta).avaliar());
	}

	@Test
	public void arestasDiferentesComDescritoresDiferentes() {
		Aresta<Descritor> arestaA = grafo.criarAresta(grafo.criarVertice(a), grafo.criarVertice(b), a1);
		Aresta<Descritor> arestaB = grafo.criarAresta(grafo.criarVertice(a), grafo.criarVertice(b), a2);
		assertFalse(arestaA.igual(arestaB).avaliar());
		assertFalse(arestaB.igual(arestaA).avaliar());
	}

	@Test
	public void arestasDiferentesComDescritoresIguais() {
		Aresta<Descritor> arestaA = grafo.criarAresta(grafo.criarVertice(a), grafo.criarVertice(b), a1);
		Aresta<Descritor> arestaB = grafo.criarAresta(grafo.criarVertice(a), grafo.criarVertice(b), a1);
		assertFalse(arestaA.igual(arestaB).avaliar());
		assertFalse(arestaB.igual(arestaA).avaliar());
	}

	@Test
	public void criarDescitorDeVertice() {
		Descritor descritor1 = Descritor.deVertice();
		Descritor descritor2 = Descritor.deVertice();
		assertThat(descritor1.fornecerTexto(), is(equalTo(Texto.criar("v1"))));
		assertThat(descritor2.fornecerTexto(), is(equalTo(Texto.criar("v2"))));
	}

	@Test
	public void criarDescitorDeAresta() {
		Descritor descritor1 = Descritor.deAresta();
		Descritor descritor2 = Descritor.deAresta();
		assertThat(descritor1.fornecerTexto(), is(equalTo(Texto.criar("a1"))));
		assertThat(descritor2.fornecerTexto(), is(equalTo(Texto.criar("a2"))));
	}

	@Test
	public void componenteComCorPadrao() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		assertTrue(vertice.coloridoCom(Cor.TRANSPARENTE).avaliar());
	}

	@Test
	public void colorirComponente() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		vertice.colorir(Cor.BRANCO);
		assertTrue(vertice.coloridoCom(Cor.BRANCO).avaliar());
		vertice.colorir(Cor.PRETO);
		assertTrue(vertice.coloridoCom(Cor.PRETO).avaliar());
	}

	@Test
	public void componenteNaoVisitado() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		assertFalse(vertice.marcado().avaliar());
	}

	@Test
	public void visitarComponente() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		vertice.marcar();
		assertTrue(vertice.marcado().avaliar());
	}

	@Test
	public void visitarComponenteEDesmarcar() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		vertice.marcar();
		assertTrue(vertice.marcado().avaliar());
		vertice.desmarcar();
		assertFalse(vertice.marcado().avaliar());
	}

	@Test
	public void componenteComPesoPadrao() {
		Vertice<Descritor> verticeA = grafo.criarVertice(a);
		Vertice<Descritor> verticeB = grafo.criarVertice(b);
		verticeB.fixarPeso(Numero.zero());
		assertTrue(verticeA.mesmoPesoQue(verticeB).avaliar());
		assertTrue(verticeB.mesmoPesoQue(verticeA).avaliar());
		assertFalse(verticeA.maisPesadoQue(verticeB).avaliar());
		assertFalse(verticeB.maisPesadoQue(verticeA).avaliar());
		assertFalse(verticeA.menosPesadoQue(verticeB).avaliar());
		assertFalse(verticeB.menosPesadoQue(verticeA).avaliar());
	}

	@Test
	public void componenteComPesoDiferenteDoPadrao() {
		Vertice<Descritor> verticeA = grafo.criarVertice(a);
		Vertice<Descritor> verticeB = grafo.criarVertice(b);
		verticeB.fixarPeso(Numero.um());
		assertFalse(verticeA.mesmoPesoQue(verticeB).avaliar());
		assertFalse(verticeB.mesmoPesoQue(verticeA).avaliar());
		assertFalse(verticeA.maisPesadoQue(verticeB).avaliar());
		assertTrue(verticeB.maisPesadoQue(verticeA).avaliar());
		assertTrue(verticeA.menosPesadoQue(verticeB).avaliar());
		assertFalse(verticeB.menosPesadoQue(verticeA).avaliar());
	}

	@Test
	public void descritor() {
		Vertice<Descritor> vertice = grafo.criarVertice(a);
		assertTrue(vertice.fornecerDescritor().igual(a).avaliar());
	}

	@Test
	public void coresIguais() {
		assertTrue(Cor.TRANSPARENTE.igual(Cor.TRANSPARENTE).avaliar());
	}

	@Test
	public void coresDiferentes() {
		assertFalse(Cor.TRANSPARENTE.igual(Cor.BRANCO).avaliar());
	}

}
