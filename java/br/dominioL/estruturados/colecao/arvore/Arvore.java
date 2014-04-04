package br.dominioL.estruturados.colecao.arvore;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Comparavel;

public interface Arvore<E extends Comparavel<E>> extends Colecao<E> {
	public E reter(E elemento);
}
