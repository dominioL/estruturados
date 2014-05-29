package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.grafo.excecoes.ExcecaoVerticeNaoPertencenteAoGrafo;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;

public final class Digrafo<V, A> {
	private Integer numeroDeVertices;
	private Integer numeroDeArestas;
	private Mapa<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> sucessores;
	private Mapa<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> antecessores;

	private Digrafo() {
		numeroDeArestas = 0;
		numeroDeVertices = 0;
		MapaLista<Vertice<V>, Aresta<A>> valorNulo = MapaLista.criar();
		sucessores = MapaLista.criar();
		antecessores = MapaLista.criar();
		sucessores.fixarValorNulo(valorNulo);
		antecessores.fixarValorNulo(valorNulo);
	}

	public static <U, B> Digrafo<U, B> criar() {
		return new Digrafo<U, B>();
	}

	public Integer contarVertices() {
		return numeroDeVertices;
	}

	public Integer contarArestas() {
		return numeroDeArestas;
	}

	public Vertice<V> criarVertice(V descritor) {
		Vertice<V> vertice = Vertice.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> sucessoresDoVertice = MapaLista.criar();
		MapaLista<Vertice<V>, Aresta<A>> antecessoresDoVertice = MapaLista.criar();
		sucessores.inserir(vertice, sucessoresDoVertice);
		antecessores.inserir(vertice, antecessoresDoVertice);
		numeroDeVertices++;
		return vertice;
	}

	public Aresta<A> criarAresta(Vertice<V> origem, Vertice<V> destino, A descritor) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		Aresta<A> aresta = Aresta.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> sucessoresDaOrigem = sucessores.fornecer(origem);
		MapaLista<Vertice<V>, Aresta<A>> antecessoresDoDestino = antecessores.fornecer(destino);
		if (!sucessoresDaOrigem.contem(destino)) {
			numeroDeArestas++;
		}
		sucessoresDaOrigem.inserir(destino, aresta);
		antecessoresDoDestino.inserir(origem, aresta);
		return aresta;
	}

	public void removerVertice(Vertice<V> vertice) {
		veriticarSeVerticesPertencemAoGrafo(vertice);
		MapaLista<Vertice<V>, Aresta<A>> sucessoresDoVertice = sucessores.fornecer(vertice);
		MapaLista<Vertice<V>, Aresta<A>> antecessoresDoVertice = antecessores.fornecer(vertice);
		for (Par<Vertice<V>, Aresta<A>> par : sucessoresDoVertice) {
			antecessores.fornecer(par.fornecerChave()).remover(vertice);
		}
		for (Par<Vertice<V>, Aresta<A>> par : antecessoresDoVertice) {
			sucessores.fornecer(par.fornecerChave()).remover(vertice);
		}
		sucessores.remover(vertice);
		antecessores.remover(vertice);
		numeroDeVertices--;
	}

	public void removerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		if (sucessores.fornecer(origem).remover(destino)) {
			antecessores.fornecer(destino).remover(origem);
			numeroDeArestas--;
		}
	}

	public Boolean contemVertice(Vertice<V> vertice) {
		return sucessores.contem(vertice);
	}

	public Boolean contemAresta(Vertice<V> origem, Vertice<V> destino) {
		return sucessores.fornecer(origem).contem(destino);
	}

	public Aresta<A> fornecerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		return sucessores.fornecer(origem).fornecer(destino);
	}

	public ListaEncadeada<Vertice<V>> sucessores(Vertice<V> origem) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		return sucessores.fornecer(origem).chaves();
	}

	public ListaEncadeada<Vertice<V>> antecessores(Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(destino);
		return antecessores.fornecer(destino).chaves();
	}

	private void veriticarSeVerticesPertencemAoGrafo(Vertice<V> vertice) {
		if (!contemVertice(vertice)) {
			throw new ExcecaoVerticeNaoPertencenteAoGrafo();
		}
	}
}
