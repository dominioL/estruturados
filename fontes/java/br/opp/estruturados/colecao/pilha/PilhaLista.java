package br.opp.estruturados.colecao.pilha;

import br.opp.estruturados.colecao.ColecaoAbstrata;
import br.opp.estruturados.colecao.lista.ListaEncadeada;
import br.opp.estruturados.elemento.Igualavel;
import br.opp.estruturados.iteracao.Iterador;

public final class PilhaLista<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Pilha<E> {
	private ListaEncadeada<E> elementos;
	
	private PilhaLista() {
		elementos = ListaEncadeada.criar();
	}
	
	public static <F extends Igualavel<F>> PilhaLista<F> criar() {
		return new PilhaLista<F>();
	}
	
	@Override
	public void empilhar(E elemento) {
		elementos.inserirNoInicio(elemento);
	}
	
	@Override
	public E desempilhar() {
		return elementos.removerDoInicio();
	}
	
	@Override
	public E fornecerDoTopo() {
		return elementos.fornecerDoInicio();
	}
	
	@Override
	public boolean estaNoTopo(E elemento) {
		return (!elementos.vazio() && elementos.fornecerDoInicio().igual(elemento));
	}
	
	@Override
	public int fornecerQuantidade() {
		 return elementos.fornecerQuantidade();
	}
	
	@Override
	public boolean contem(E elemento) {
		return elementos.contem(elemento);
	}
	
	@Override
	public boolean remover(E elemento) {
		return elementos.remover(elemento);
	}
	
	@Override
	public void inserir(E elemento) {
		empilhar(elemento);
	}
	
	@Override
	public Iterador<E> fornecerIterador() {
		return elementos.fornecerIterador();
	}
}

