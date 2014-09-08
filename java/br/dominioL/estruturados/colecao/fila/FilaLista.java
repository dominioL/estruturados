package br.dominioL.estruturados.colecao.fila;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.iteracao.Iterador;

public final class FilaLista<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Fila<E> {

	private ListaEncadeada<E> elementos;

	private FilaLista() {
		elementos = ListaEncadeada.criar();
	}

	public static <F extends Igualavel<F>> FilaLista<F> criar() {
		return new FilaLista<F>();
	}

	@Override
	public void enfileirar(E elemento) {
		elementos.inserirNoFim(elemento);
	}

	@Override
	public E desenfileirar() {
		return elementos.removerDoInicio();
	}

	@Override
	public E fornecerDoInicio() {
		return elementos.fornecerDoInicio();
	}

	@Override
	public E fornecerDoFim() {
		return elementos.fornecerDoFim();
	}

	@Override
	public Booleano estaNoInicio(E elemento) {
		if (vazio().negar().e(Booleano.nulo(elemento).negar()).avaliar()) {
			return elementos.fornecerDoInicio().igual(elemento);
		}
		return Booleano.falso();
	}

	@Override
	public Booleano estaNoFim(E elemento) {
		if (vazio().negar().e(Booleano.nulo(elemento).negar()).avaliar()) {
			return elementos.fornecerDoFim().igual(elemento);
		}
		return Booleano.falso();
	}

	@Override
	public Numero contarElementos() {
		return elementos.contarElementos();
	}

	@Override
	public Booleano contem(E elemento) {
		if (Booleano.nulo(elemento).negar().avaliar()) {
			return elementos.contem(elemento);
		}
		return Booleano.falso();
	}

	@Override
	public void inserir(E elemento) {
		enfileirar(elemento);
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
