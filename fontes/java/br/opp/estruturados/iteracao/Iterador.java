package br.opp.estruturados.iteracao;

import java.util.Iterator;

public interface Iterador<E> extends Iterator<E> {
	public boolean possuiProximo();
	
	public E iterarProximo();
	
	public E remover();
	
	public E substituir(E substituto); 
}

