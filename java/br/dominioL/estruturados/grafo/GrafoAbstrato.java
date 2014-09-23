package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.grafo.excecoes.ExcecaoVerticeNaoPertencenteAoGrafo;
import br.dominioL.estruturados.mapa.MapaLista;

abstract class GrafoAbstrato<V, A> {

	Numero numeroDeVertices;
	Numero numeroDeArestas;

	GrafoAbstrato() {
		numeroDeArestas = Numero.zero();
		numeroDeVertices = Numero.zero();
	}

	public final Numero contarVertices() {
		return numeroDeVertices;
	}

	public final Numero contarArestas() {
		return numeroDeArestas;
	}

	public abstract Vertice<V> criarVertice(V descritor);

	public final Aresta<A> criarAresta(Vertice<V> origem, Vertice<V> destino, A descritor) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		Aresta<A> aresta = Aresta.criar(descritor);
		MapaLista<Vertice<V>, Aresta<A>> elementosDeAlfa = particaoAlfa().fornecer(origem);
		MapaLista<Vertice<V>, Aresta<A>> elementosDeBeta = particaoBeta().fornecer(destino);
		if (elementosDeAlfa.contem(destino).negar().avaliar()) {
			numeroDeArestas = numeroDeArestas.incrementar();
		}
		elementosDeAlfa.inserir(destino, aresta);
		elementosDeBeta.inserir(origem, aresta);
		return aresta;
	}

	public abstract void removerVertice(Vertice<V> vertice);

	public final void removerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		if (particaoAlfa().fornecer(origem).remover(destino).avaliar()) {
			particaoBeta().fornecer(destino).remover(origem);
			numeroDeArestas = numeroDeArestas.decrementar();
		}
	}

	public final Booleano contemVertice(Vertice<V> vertice) {
		return particaoAlfa().contem(vertice);
	}

	public final Booleano contemAresta(Vertice<V> origem, Vertice<V> destino) {
		return particaoAlfa().fornecer(origem).contem(destino);
	}

	public final Aresta<A> fornecerAresta(Vertice<V> origem, Vertice<V> destino) {
		veriticarSeVerticesPertencemAoGrafo(origem);
		veriticarSeVerticesPertencemAoGrafo(destino);
		return particaoAlfa().fornecer(origem).fornecer(destino);
	}

	final void veriticarSeVerticesPertencemAoGrafo(Vertice<V> vertice) {
		if (contemVertice(vertice).negar().avaliar()) {
			throw new ExcecaoVerticeNaoPertencenteAoGrafo();
		}
	}

	abstract MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoAlfa();

	abstract MapaLista<Vertice<V>, MapaLista<Vertice<V>, Aresta<A>>> particaoBeta();

}
