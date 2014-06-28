package br.dominioL.estruturados.colecao;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoEstruturaVazia;
import br.dominioL.estruturados.iteracao.Iteravel;
import br.dominioL.estruturados.iteracao.IteravelAbstrato;

public abstract class ColecaoAbstrata<E extends Igualavel<E>> extends IteravelAbstrato<E> implements Colecao<E> {
	protected E valorNulo;

	protected ColecaoAbstrata() {}

	@Override
	public final Boolean vazio() {
		return (contarElementos() == 0);
	}

	@Override
	public final void inserir(Iteravel<E> elementos) {
		for (E elemento : elementos) {
			inserir(elemento);
		}
	}

	protected final void lancarExcecaoDeElementoNuloSeNecessario(E elemento) {
		if (elemento == null) {
			throw new ExcecaoElementoNulo();
		}
	}

	protected final void lancarExcecaoDeEstruturaVaziaSeNecessario() {
		if (vazio()) {
			throw new ExcecaoEstruturaVazia();
		}
	}
}
