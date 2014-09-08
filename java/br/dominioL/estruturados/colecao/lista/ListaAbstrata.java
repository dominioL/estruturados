package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.iteracao.Iterador;

public abstract class ListaAbstrata<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Lista<E> {

	protected ListaAbstrata() {

	}

	@Override
	public final Numero contarElemento(E elemento) {
		Numero quantidade = Numero.zero();
		Iterador<E> iterador = fornecerIterador();
		while (iterador.possuiProximo().avaliar()) {
			if (iterador.iterarProximo().igual(elemento).avaliar()) {
				quantidade = quantidade.incrementar();
			}
		}
		return quantidade;
	}

	@Override
	public final E reter(E elemento) {
		Iterador<E> iterador = fornecerIterador();
		while (iterador.possuiProximo().avaliar()) {
			E elementoEncontrado = iterador.iterarProximo();
			if (elementoEncontrado.igual(elemento).avaliar()) {
				return elementoEncontrado;
			}
		}
		return null;
	}

	@Override
	public final Booleano contem(E elemento) {
		E elementoEncontrado = reter(elemento);
		return Booleano.nulo(elementoEncontrado).negar();
	}

	@Override
	public final Booleano remover(E elemento) {
		if (Booleano.nulo(elemento).negar().avaliar()) {
			Iterador<E> iterador = fornecerIterador();
			while (iterador.possuiProximo().avaliar()) {
				E elementoEncontrado = iterador.iterarProximo();
				if (elementoEncontrado.igual(elemento).avaliar()) {
					iterador.remover();
					return Booleano.verdadeiro();
				}
			}
		}
		return Booleano.falso();
	}

}
