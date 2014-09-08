package br.dominioL.estruturados.colecao.fila;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;

public interface Fila<E extends Igualavel<E>> extends Colecao<E> {

	public void enfileirar(E elemento);

	public E desenfileirar();

	public E fornecerDoInicio();

	public E fornecerDoFim();

	public Booleano estaNoInicio(E elemento);

	public Booleano estaNoFim(E elemento);

}
