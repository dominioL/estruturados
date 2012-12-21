package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.iteracao.Iterador;

public abstract class ListaAbstrata<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Lista<E> {
	protected ListaAbstrata() {
		
	}
	
	@Override
	public final Boolean remover(E elemento) {
		Boolean removeu = false;
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
	public final Boolean contem(E elemento) {
		E elementoEncontrado = reter(elemento);
		if (elementoEncontrado == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public final Integer contarElemento(E elemento) {
		Integer quantidade = 0;
		Iterador<E> iterador = fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo().igual(elemento)) {
				quantidade++;
			}
		}
		return quantidade;
	}
	
	@Override
	public final E reter(E elemento) {
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
