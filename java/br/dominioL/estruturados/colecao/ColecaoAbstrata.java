package br.dominioL.estruturados.colecao;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoEstruturaVazia;
import br.dominioL.estruturados.iteracao.Iteravel;
import br.dominioL.estruturados.iteracao.IteravelAbstrato;

public abstract class ColecaoAbstrata<E extends Igualavel<E>> extends IteravelAbstrato<E> implements Colecao<E> {

	protected E valorNulo;

	protected ColecaoAbstrata() {}

	@Override
	public final Booleano vazio() {
		return contarElementos().igual(Numero.zero());
	}

	@Override
	public final void inserir(Iteravel<E> elementos) {
		for (E elemento : elementos) {
			inserir(elemento);
		}
	}

	protected final void lancarExcecaoDeElementoNuloSeNecessario(E elemento) {
		if (Booleano.nulo(elemento).avaliar()) {
			throw new ExcecaoElementoNulo();
		}
	}

	protected final void lancarExcecaoDeEstruturaVaziaSeNecessario() {
		if (vazio().avaliar()) {
			throw new ExcecaoEstruturaVazia();
		}
	}

}
