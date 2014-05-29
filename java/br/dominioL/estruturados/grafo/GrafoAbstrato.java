package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.grafo.excecoes.ExcecaoVerticeNaoPertencenteAoGrafo;
import br.dominioL.estruturados.mapa.MapaLista;

abstract class GrafoAbstrato<V, A> {
	Integer numeroDeVertices;
	Integer numeroDeArestas;

	GrafoAbstrato() {
		numeroDeArestas = 0;
		numeroDeVertices = 0;
	}

	public final Integer contarVertices() {
		return numeroDeVertices;
	}

	public final Integer contarArestas() {
		return numeroDeArestas;
	}

	public abstract Vertice<V> criarVertice(V descritor);

	public final Aresta<A> criarAresta(Vertice<V> origem, Vertice<V> destino, A descritor) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		Aresta<A> aresta = Aresta.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> elementosDeAlfa = particaoAlfa().fornecer(origem);
		MapaLista<Vertice<V>, Aresta<A>> elementosDeBeta = particaoBeta().fornecer(destino);
		if (!elementosDeAlfa.contem(destino)) {
			numeroDeArestas++;
		}
		elementosDeAlfa.inserir(destino, aresta);
		elementosDeBeta.inserir(origem, aresta);
		return aresta;
	}

	public abstract void removerVertice(Vertice<V> vertice);

	public final void removerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		if (particaoAlfa().fornecer(origem).remover(destino)) {
			particaoBeta().fornecer(destino).remover(origem);
			numeroDeArestas--;
		}
	}

	public final Boolean contemVertice(Vertice<V> vertice) {
		return particaoAlfa().contem(vertice);
	}

	public final Boolean contemAresta(Vertice<V> origem, Vertice<V> destino) {
		return particaoAlfa().fornecer(origem).contem(destino);
	}

	public final Aresta<A> fornecerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		return particaoAlfa().fornecer(origem).fornecer(destino);
	}

	final void veriticarSeVerticesPertencemAoGrafo(Vertice<V> vertice) {
		if (!contemVertice(vertice)) {
			throw new ExcecaoVerticeNaoPertencenteAoGrafo();
		}
	}

	abstract MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoAlfa();

	abstract MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoBeta();
}
