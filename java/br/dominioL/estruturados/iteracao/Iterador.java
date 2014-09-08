package br.dominioL.estruturados.iteracao;

import java.util.Iterator;

import br.dominioL.estruturados.elemento.primitivos.Booleano;

public interface Iterador<E> extends Iterator<E> {

	public Booleano possuiProximo();

	public E iterarProximo();

	public E remover();

	public E substituir(E substituto);

}
