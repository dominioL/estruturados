package br.dominioL.estruturados.colecao.conjunto;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.elemento.Igualavel;

public abstract class ConjuntoAbstrato<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Conjunto<E> {

}
