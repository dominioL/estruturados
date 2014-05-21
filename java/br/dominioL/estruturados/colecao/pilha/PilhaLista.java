package br.dominioL.estruturados.colecao.pilha;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.iteracao.Iterador;

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
	public Boolean estaNoTopo(E elemento) {
		return (!elementos.vazio() && elementos.fornecerDoInicio().igual(elemento));
	}

	@Override
	public Integer contarElementos() {
		 return elementos.contarElementos();
	}

	@Override
	public Boolean contem(E elemento) {
		return elementos.contem(elemento);
	}

	@Override
	public void inserir(E elemento) {
		empilhar(elemento);
	}

	@Override
	public Boolean remover(E elemento) {
		return elementos.remover(elemento);
	}

	@Override
	public Iterador<E> fornecerIterador() {
		return elementos.fornecerIterador();
	}
}
