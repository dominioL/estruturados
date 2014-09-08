package br.dominioL.estruturados.colecao.conjunto;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
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
	public Numero contarElementos() {
		return elementos.contarElementos();
	}

	@Override
	public Booleano contem(E elemento) {
		return elementos.contem(elemento);
	}

	@Override
	public void inserir(E elemento) {
		elementos.remover(elemento);
		elementos.inserir(elemento);
	}

	@Override
	public Booleano remover(E elemento) {
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

	@Override
	public Booleano igual(ConjuntoLista<E> outro) {
		return Booleano.mesmo(this, outro);
	}

}
