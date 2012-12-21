package br.opp.estruturados.colecao.lista;

import br.opp.estruturados.colecao.Colecao;
import br.opp.estruturados.elemento.Igualavel;

public interface Lista<E extends Igualavel<E>> extends Colecao<E> {
	public int contarElemento(E elemento);
	
	public E reter(E elemento);
}

