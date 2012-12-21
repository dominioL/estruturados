package br.opp.estruturados.colecao.lista;

import br.opp.estruturados.colecao.ColecaoAbstrata;
import br.opp.estruturados.elemento.Igualavel;
import br.opp.estruturados.iteracao.Iterador;

public abstract class ListaAbstrata<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Lista<E> {
	protected ListaAbstrata() {
		
	}
	
	@Override
	public final boolean remover(E elemento) {
		boolean removeu = false;
		Iterador<E> iterador = fornecerIterador();
		while (!removeu && iterador.possuiProximo()) {
			if (iterador.iterarProximo().igual(elemento)) {
				iterador.remover();
				removeu = true;
			}
		}
		return removeu;
	}
	
	@Override
	public final boolean contem(E elemento) {
		E elementoEncontrado = reter(elemento);
		if (elementoEncontrado == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public int contarElemento(E elemento) {
		//TODO
		return 0;
	}
	
	@Override
	public E reter(E elemento) {
		Iterador<E> iterador = fornecerIterador();
		while (iterador.possuiProximo()) {
			E elementoEncontrado = iterador.iterarProximo();
			if (elementoEncontrado.igual(elemento)) {
				return elementoEncontrado;
			}
		}
		return null;
	}
}

