package br.dominioL.estruturados.colecao.pilha;

import br.dominioL.estruturados.colecao.Colecao;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;

public interface Pilha<E extends Igualavel<E>> extends Colecao<E> {

	public void empilhar(E elemento);

	public E desempilhar();

	public E fornecerDoTopo();

	public Booleano estaNoTopo(E elemento);

}
