package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;

public final class Vertice<T> extends Componente<T> implements Igualavel<Vertice<T>> {

	private Vertice(T descritor) {
		super(descritor);
	}

	static <T> Vertice<T> criar(T descritor) {
		return new Vertice<T>(descritor);
	}

	@Override
	public Booleano igual(Vertice<T> outro) {
		return Booleano.mesmo(this, outro);
	}

}
