package br.dominioL.estruturados.colecao.conjunto;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.iteracao.Iterador;

public final class ConjuntoLista<E extends Igualavel<E>> extends ConjuntoAbstrato<E> implements Igualavel<ConjuntoLista<E>> {
	private ListaEncadeada<E> elementos;

	private ConjuntoLista() {
		elementos = ListaEncadeada.criar();
	}

	public static <F extends Igualavel<F>> ConjuntoLista<F> criar() {
		return new ConjuntoLista<F>();
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
		elementos.remover(elemento);
		elementos.inserir(elemento);
	}

	@Override
	public Boolean remover(E elemento) {
		return elementos.remover(elemento);
	}

	@Override
	public Iterador<E> fornecerIterador() {
		return elementos.fornecerIterador();
	}

	@Override
	public E reter(E elemento) {
		return elementos.reter(elemento);
	}

//	@Override
//	public Integer codificar() {
//		return elementos.codificar();
//	}

	@Override
	public Boolean igual(ConjuntoLista<E> outro) {
		return (this == outro);
	}
}
