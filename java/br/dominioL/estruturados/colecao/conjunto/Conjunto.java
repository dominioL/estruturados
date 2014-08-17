package br.dominioL.estruturados.colecao.conjunto;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Igualavel;

public interface Conjunto<E extends Igualavel<E>> extends Colecao<E> {
	public E reter(E elemento);
}
