package br.dominioL.estruturados.colecao.arvore;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Comparavel;
import br.dominioL.estruturados.elemento.Igualavel;

public interface Arvore<E extends Igualavel<E> & Comparavel<E>> extends Colecao<E> {
	public E reter(E elemento);
}