package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;

public final class Digrafo<V, A> extends GrafoAbstrato<V, A> {
	private MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> sucessores;
	private MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> antecessores;

	private Digrafo() {
		MapaLista<Vertice<V>, Aresta<A>> valorNulo = MapaLista.criar();
		sucessores = MapaLista.criar();
		antecessores = MapaLista.criar();
		sucessores.fixarValorNulo(valorNulo);
		antecessores.fixarValorNulo(valorNulo);
	}

	public static <U, B> Digrafo<U, B> criar() {
		return new Digrafo<U, B>();
	}

	@Override
	public Vertice<V> criarVertice(V descritor) {
		Vertice<V> vertice = Vertice.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> sucessoresDoVertice = MapaLista.criar();
		MapaLista<Vertice<V>, Aresta<A>> antecessoresDoVertice = MapaLista.criar();
		sucessores.inserir(vertice, sucessoresDoVertice);
		antecessores.inserir(vertice, antecessoresDoVertice);
		numeroDeVertices++;
		return vertice;
	}

	@Override
	public void removerVertice(Vertice<V> vertice) {
		veriticarSeVerticesPertencemAoGrafo(vertice);
		MapaLista<Vertice<V>, Aresta<A>> sucessoresDoVertice = sucessores.fornecer(vertice);
		MapaLista<Vertice<V>, Aresta<A>> antecessoresDoVertice = antecessores.fornecer(vertice);
		for (Par<Vertice<V>, Aresta<A>> par : sucessoresDoVertice) {
			removerAresta(vertice, par.fornecerChave());
		}
		for (Par<Vertice<V>, Aresta<A>> par : antecessoresDoVertice) {
			removerAresta(par.fornecerChave(), vertice);
		}
		sucessores.remover(vertice);
		antecessores.remover(vertice);
		numeroDeVertices--;
	}

	public ListaEncadeada<Vertice<V>> sucessores(Vertice<V> origem) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		return sucessores.fornecer(origem).chaves();
	}

	public ListaEncadeada<Vertice<V>> antecessores(Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(destino);
		return antecessores.fornecer(destino).chaves();
	}

	@Override
	MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoAlfa() {
		return sucessores;
	}

	@Override
	MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoBeta() {
		return antecessores;
	}
}
