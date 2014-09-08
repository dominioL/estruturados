package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Numero;

public interface Lista<E extends Igualavel<E>> extends Colecao<E> {

	public Numero contarElemento(E elemento);

	public E reter(E elemento);

}
