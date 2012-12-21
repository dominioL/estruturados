package br.opp.estruturados;

import br.opp.estruturados.elemento.Igualavel;

public interface Encadeavel<E extends Igualavel<E>> {
	public void inserirNoInicio(E elemento);
	
	public void inserirNoFim(E elemento);
	
	public E fornecerDoInicio();
	
	public E fornecerDoFim();
	
	public E removerDoInicio();
	
	public E removerDoFim();
}

