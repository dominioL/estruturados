package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Igualavel;

public final class Vertice<T> extends Componente<T> implements Igualavel<Vertice<T>> {
	private Vertice(T descritor) {
		super(descritor);
	}

	static <T> Vertice<T> criar(T descritor) {
		return new Vertice<T>(descritor);
	}

	@Override
	public Boolean igual(Vertice<T> outro) {
		return (this == outro);
	}
}
