package br.dominioL.estruturados.colecao.fila;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Igualavel;

public interface Fila<E extends Igualavel<E>> extends Colecao<E> {
	public void enfileirar(E elemento);

	public E desenfileirar();

	public E fornecerDoInicio();

	public E fornecerDoFim();

	public Boolean estaNoInicio(E elemento);

	public Boolean estaNoFim(E elemento);
}
