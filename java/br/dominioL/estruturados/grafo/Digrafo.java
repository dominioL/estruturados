package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;

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

	public Aresta<A> criarAresta(Vertice<V> origem, Vertice<V> destino, A descritor) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		Aresta<A> aresta = Aresta.unidirecional(descritor);
		MapaLista<Vertice<V>, Aresta<A>> sucessores = adjacencia.fornecer(origem);
		if (!sucessores.contem(destino)) {
			numeroDeArestas++;
		}
		sucessores.inserir(destino, aresta);
		return aresta;
	}

	public void removerVertice(Vertice<V> vertice) {
		veriticarSeVerticesPertencemAoGrafo(vertice);
		adjacencia.remover(vertice);
		for (Par<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> par : adjacencia) {
			par.fornecerValor().remover(vertice);
		}
	}

	public void removerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		Boolean removido = adjacencia.fornecer(origem).remover(destino);
		if (removido ) {
			numeroDeArestas--;
		}
	}

	public Boolean contemVertice(Vertice<V> vertice) {
		return adjacencia.contem(vertice);
	}

	public Boolean contemAresta(Vertice<V> origem, Vertice<V> destino) {
		return adjacencia.fornecer(origem).contem(destino);
	}

	public Aresta<A> fornecerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		return adjacencia.fornecer(origem).fornecer(destino);
	}

	public ListaEncadeada<Vertice<V>> sucessores(Vertice<V> origem) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		return adjacencia.fornecer(origem).chaves();
	}

	public ListaEncadeada<Vertice<V>> antecessores(Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(destino);
		ListaEncadeada<Vertice<V>> antecessores = ListaEncadeada.criar();
		for (Par<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> par : adjacencia) {
			if (par.fornecerValor().contem(destino)) {
				antecessores.inserirNoFim(par.fornecerChave());
			}
		}
		return antecessores;
	}

	private void veriticarSeVerticesPertencemAoGrafo(Vertice<V> vertice) {
		if (!contemVertice(vertice)) {
			throw new ExcecaoVerticeNaoPertencenteAoGrafo();
		}
	}
}
