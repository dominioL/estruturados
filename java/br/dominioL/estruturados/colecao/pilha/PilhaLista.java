package br.dominioL.estruturados.colecao.pilha;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
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
	public Booleano estaNoTopo(E elemento) {
		if (elementos.vazio().negar().avaliar()) {
			return elementos.fornecerDoInicio().igual(elemento);
		}
		return Booleano.falso();
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
		empilhar(elemento);
	}

	@Override
	public Booleano remover(E elemento) {
		return elementos.remover(elemento);
	}

	@Override
	public Iterador<E> fornecerIterador() {
		return elementos.fornecerIterador();
	}

}
