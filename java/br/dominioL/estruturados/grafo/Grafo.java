package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;

public final class Grafo<V, A> extends GrafoAbstrato<V, A> {

	private MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> adjacentes;

	private Grafo() {
		super();
		MapaLista<Vertice<V>, Aresta<A>> valorNulo = MapaLista.criar();
		adjacentes = MapaLista.criar();
		adjacentes.fixarValorNulo(valorNulo);
	}

	public static <U, B> Grafo<U, B> criar() {
		return new Grafo<U, B>();
	}

	@Override
	public Vertice<V> criarVertice(V descritor) {
		Vertice<V> vertice = Vertice.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> adjacentesDoVertice = MapaLista.criar();
		adjacentes.inserir(vertice, adjacentesDoVertice);
		numeroDeVertices = numeroDeVertices.incrementar();
		return vertice;
	}

	@Override
	public void removerVertice(Vertice<V> vertice) {
		veriticarSeVerticesPertencemAoGrafo(vertice);
		MapaLista<Vertice<V>, Aresta<A>> adjacentesDoVertice = adjacentes.fornecer(vertice);
		for (Par<Vertice<V>, Aresta<A>> par : adjacentesDoVertice) {
			removerAresta(vertice, par.fornecerChave());
		}
		adjacentes.remover(vertice);
		numeroDeVertices = numeroDeVertices.decrementar();
	}

	public ListaEncadeada<Vertice<V>> adjacentes(Vertice<V> vertice) {
		veriticarSeVerticesPertencemAoGrafo(vertice);
		return adjacentes.fornecer(vertice).chaves();
	}

	@Override
	MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoAlfa() {
		return adjacentes;
	}

	@Override
	MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoBeta() {
		return adjacentes;
	}

}
