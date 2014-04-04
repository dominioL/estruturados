package br.dominioL.estruturados.iteracao;

import java.util.Iterator;

public interface Iterador<E> extends Iterator<E> {
	public Boolean possuiProximo();

	public E iterarProximo();

	public E remover();

	public E substituir(E substituto); 
}
