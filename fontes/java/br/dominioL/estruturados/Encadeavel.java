package br.dominioL.estruturados;

import br.dominioL.estruturados.elemento.Igualavel;

public interface Encadeavel<E extends Igualavel<E>> {
	public void inserirNoInicio(E elemento);
	
	public void inserirNoFim(E elemento);
	
	public E fornecerDoInicio();
	
	public E fornecerDoFim();
	
	public E removerDoInicio();
	
	public E removerDoFim();
}
