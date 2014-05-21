package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Igualavel;

public final class Aresta<T> extends ComponenteDeGrafo<T> implements Igualavel<Aresta<T>> {
	private Aresta(T descritor) {
		super(descritor);
	}

	public static <U> Aresta<U> unidirecional(U descritor) {
		return new Aresta<U>(descritor);
	}

	@Override
	public Boolean igual(Aresta<T> outro) {
		return (this == outro);
	}
}
