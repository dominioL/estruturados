package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;

public final class Digrafo<V, A> {
	private Integer numeroDeArestas;
	private Mapa<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> adjacencia;

	private Digrafo() {
		numeroDeArestas = 0;
		adjacencia = MapaLista.criar();
		MapaLista<Vertice<V>, Aresta<A>> valorNulo = MapaLista.criar();
		adjacencia.fixarValorNulo(valorNulo);
	}

	public static <U, B> Digrafo<U, B> criar() {
		return new Digrafo<U, B>();
	}

	public Integer contarVertices() {
		return adjacencia.contarElementos();
	}

	public Integer contarArestas() {
		return numeroDeArestas;
	}

	public Vertice<V> criarVertice(V descritor) {
		Vertice<V> vertice = Vertice.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> ligacoes = MapaLista.criar();
		adjacencia.inserir(vertice, ligacoes);
		return vertice;
	}

	public Aresta<A> criarAresta(Vertice<V> verticeDeOrigem, Vertice<V> verticeDeDestino, A descritor) {
		Aresta<A> aresta = Aresta.unidirecional(descritor);
		MapaLista<Vertice<V>, Aresta<A>> sucessores = adjacencia.fornecer(verticeDeOrigem);
		if (!sucessores.contem(verticeDeDestino)) {
			numeroDeArestas++;
		}
		sucessores.inserir(verticeDeDestino, aresta);
		return aresta;
	}

	public boolean contemVertice(Vertice<V> vertice) {
		return adjacencia.contem(vertice);
	}

	public Boolean contemAresta(Vertice<V> verticeDeOrigem, Vertice<V> verticeDeDestino) {
		return (fornecerAresta(verticeDeOrigem, verticeDeDestino) != null);
	}

	public Aresta<A> fornecerAresta(Vertice<V> origem, Vertice<V> destino) {
		return adjacencia.fornecer(origem).fornecer(destino);
	}

	public ListaEncadeada<Vertice<V>> sucessores(Vertice<V> origem) {
		return null;
	}

	public ListaEncadeada<Vertice<V>> antecessores(Vertice<V> destino) {
		return null;
	}
}
