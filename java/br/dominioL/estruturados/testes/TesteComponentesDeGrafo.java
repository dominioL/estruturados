package br.dominioL.estruturados.testes;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.grafo.Aresta;
import br.dominioL.estruturados.grafo.Cor;
import br.dominioL.estruturados.grafo.Peso;
import br.dominioL.estruturados.grafo.Vertice;
import br.dominioL.estruturados.grafo.utilidades.Descritor;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

public class TesteComponentesDeGrafo {
	private Descritor a;
	private Descritor a1;
	private Descritor a2;
	private Descritor b;

	@Before
	public void iniciar() {
		a = new Descritor("a");
		a1 = new Descritor("a");
		a2 = new Descritor("a");
		b = new Descritor("b");
	}

	@Test
	public void descritorIgualAEleMesmo() {
		assertTrue(a.igual(a));
	}

	@Test
	public void descritoresDiferentes() {
		assertFalse(a.igual(b));
		assertFalse(b.igual(a));
	}

	@Test
	public void descritoresguais() {
		assertTrue(a1.igual(a2));
		assertTrue(a2.igual(a1));
	}

	@Test
	public void verticeIGualAEleMesmo() {
		Vertice<Descritor> vertice = Vertice.criar(a);
		assertTrue(vertice.igual(vertice));
	}

	@Test
	public void verticesDiferentesComDescritoresDiferentes() {
		Vertice<Descritor> verticeA = Vertice.criar(a);
		Vertice<Descritor> verticeB = Vertice.criar(b);
		assertFalse(verticeA.igual(verticeB));
		assertFalse(verticeB.igual(verticeA));
	}

	@Test
	public void verticesDiferentesComDescritoresIguais() {
		Vertice<Descritor> verticeA = Vertice.criar(a);
		Vertice<Descritor> verticeB = Vertice.criar(a);
		assertFalse(verticeA.igual(verticeB));
		assertFalse(verticeB.igual(verticeA));
	}

	@Test
	public void arestaIgualAElaMesma() {
		Aresta<Descritor> aresta = Aresta.unidirecional(a);
		assertTrue(aresta.igual(aresta));
	}

	@Test
	public void arestasDiferentesComDescritoresDiferentes() {
		Aresta<Descritor> arestaA = Aresta.unidirecional(a);
		Aresta<Descritor> arestaB = Aresta.unidirecional(b);
		assertFalse(arestaA.igual(arestaB));
		assertFalse(arestaB.igual(arestaA));
	}

	@Test
	public void arestasDiferentesComDescritoresIguais() {
		Aresta<Descritor> arestaA = Aresta.unidirecional(a);
		Aresta<Descritor> arestaB = Aresta.unidirecional(a);
		assertFalse(arestaA.igual(arestaB));
		assertFalse(arestaB.igual(arestaA));
	}

	@Test
	public void criarDescitorDeVertice() {
		Descritor descritor1 = Descritor.deVertice();
		Descritor descritor2 = Descritor.deVertice();
		assertThat(descritor1.fornecerTexto(), is(equalTo("v1")));
		assertThat(descritor2.fornecerTexto(), is(equalTo("v2")));
	}

	@Test
	public void criarDescitorDeAresta() {
		Descritor descritor1 = Descritor.deAresta();
		Descritor descritor2 = Descritor.deAresta();
		assertThat(descritor1.fornecerTexto(), is(equalTo("a1")));
		assertThat(descritor2.fornecerTexto(), is(equalTo("a2")));
	}

	@Test
	public void componenteComCorPadrao() {
		Vertice<Descritor> vertice = Vertice.criar(a);
		assertTrue(vertice.coloridoCom(Cor.TRANSPARENTE));
	}

	@Test
	public void colorirComponente() {
		Vertice<Descritor> vertice = Vertice.criar(a);
		vertice.colorir(Cor.BRANCO);
		assertTrue(vertice.coloridoCom(Cor.BRANCO));
		vertice.colorir(Cor.PRETO);
		assertTrue(vertice.coloridoCom(Cor.PRETO));
	}

	@Test
	public void componenteNaoVisitado() {
		Vertice<Descritor> vertice = Vertice.criar(a);
		assertFalse(vertice.marcado());
	}

	@Test
	public void visitarComponente() {
		Vertice<Descritor> vertice = Vertice.criar(a);
		vertice.marcar();
		assertTrue(vertice.marcado());
	}

	@Test
	public void componenteComPesoPadrao() {
		Vertice<Descritor> verticeA = Vertice.criar(a);
		Vertice<Descritor> verticeB = Vertice.criar(b);
		verticeB.fixarPeso(Peso.zero());
		assertTrue(verticeA.mesmoPesoQue(verticeB));
		assertTrue(verticeB.mesmoPesoQue(verticeA));
		assertFalse(verticeA.maisPesadoQue(verticeB));
		assertFalse(verticeB.maisPesadoQue(verticeA));
		assertFalse(verticeA.menosPesadoQue(verticeB));
		assertFalse(verticeB.menosPesadoQue(verticeA));
	}

	@Test
	public void componenteComPesoDiferenteDoPadrao() {
		Vertice<Descritor> verticeA = Vertice.criar(a);
		Vertice<Descritor> verticeB = Vertice.criar(b);
		verticeB.fixarPeso(Peso.um());
		assertFalse(verticeA.mesmoPesoQue(verticeB));
		assertFalse(verticeB.mesmoPesoQue(verticeA));
		assertFalse(verticeA.maisPesadoQue(verticeB));
		assertTrue(verticeB.maisPesadoQue(verticeA));
		assertTrue(verticeA.menosPesadoQue(verticeB));
		assertFalse(verticeB.menosPesadoQue(verticeA));
	}

	@Test
	public void descritor() {
		Vertice<Descritor> vertice = Vertice.criar(a);
		assertTrue(vertice.fornecerDescritor().igual(a));
	}

	@Test
	public void coresIguais() {
		assertTrue(Cor.TRANSPARENTE.igual(Cor.TRANSPARENTE));
	}

	@Test
	public void coresDiferentes() {
		assertFalse(Cor.TRANSPARENTE.igual(Cor.BRANCO));
	}

	@Test
	public void maisInfinito() {
		Peso maisInfinito = Peso.maisInfinito();
		Peso menosInfinito = Peso.menosInfinito();
		Peso maiorValorMenosUm = Peso.comValor(Integer.MAX_VALUE - 1);
		assertTrue(maisInfinito.maiorQue(maiorValorMenosUm));
		assertFalse(maisInfinito.menorQue(maiorValorMenosUm));
		assertFalse(maisInfinito.igual(maiorValorMenosUm));
		assertTrue(maisInfinito.maiorQue(menosInfinito));
		assertFalse(maisInfinito.menorQue(menosInfinito));
		assertFalse(maisInfinito.igual(menosInfinito));
	}

	@Test
	public void menosInfinito() {
		Peso menosInfinito = Peso.menosInfinito();
		Peso menorMaisUm = Peso.comValor(Integer.MIN_VALUE + 1);
		Peso maisInfinito = Peso.maisInfinito();
		assertFalse(menosInfinito.maiorQue(menorMaisUm));
		assertTrue(menosInfinito.menorQue(menorMaisUm));
		assertFalse(menosInfinito.igual(menorMaisUm));
		assertFalse(menosInfinito.maiorQue(maisInfinito));
		assertTrue(menosInfinito.menorQue(maisInfinito));
		assertFalse(menosInfinito.igual(maisInfinito));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPodeCriarPesoComValorMaximo() {
		Peso.comValor(Integer.MAX_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPodeCriarPesoComValorMinimo() {
		Peso.comValor(Integer.MIN_VALUE);
	}
}
