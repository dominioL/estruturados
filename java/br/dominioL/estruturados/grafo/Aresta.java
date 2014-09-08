package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;

public final class Aresta<T> extends Componente<T> implements Igualavel<Aresta<T>> {

	private Aresta(T descritor) {
		super(descritor);
	}

	static <U> Aresta<U> criar(U descritor) {
		return new Aresta<U>(descritor);
	}

	@Override
	public Booleano igual(Aresta<T> outro) {
		return Booleano.mesmo(this, outro);
	}

}
