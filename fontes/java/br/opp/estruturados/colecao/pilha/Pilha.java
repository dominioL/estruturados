package br.opp.estruturados.colecao.pilha;

import br.opp.estruturados.colecao.Colecao;
import br.opp.estruturados.elemento.Igualavel;

public interface Pilha<E extends Igualavel<E>> extends Colecao<E> {
	public void empilhar(E elemento);
	
	public E desempilhar();
	
	public E fornecerDoTopo();
	
	public boolean estaNoTopo(E elemento);
}

