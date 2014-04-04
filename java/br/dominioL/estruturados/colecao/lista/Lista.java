package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Igualavel;

public interface Lista<E extends Igualavel<E>> extends Colecao<E> {
	public Integer contarElemento(E elemento);

	public E reter(E elemento);
}
