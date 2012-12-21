package br.opp.estruturados.colecao.fila;

import br.opp.estruturados.colecao.Colecao;
import br.opp.estruturados.elemento.Igualavel;

public interface Fila<E extends Igualavel<E>> extends Colecao<E> {
	public void enfilerar(E elemento);
	
	public E desenfilerar();
	
	public E fornecerDoInicio();
	
	public E fornecerDoFim();
	
	public boolean estaNoInicio(E elemento);
	
	public boolean estaNoFim(E elemento);
}

