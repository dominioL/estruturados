package br.dominioL.estruturados.colecao;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoDeEstruturaVazia;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.Iteravel;
import br.dominioL.estruturados.iteracao.IteravelAbstrato;

public abstract class ColecaoAbstrata<E extends Igualavel<E>> extends IteravelAbstrato<E> implements Colecao<E> {
	protected ColecaoAbstrata() {
		
	}
	
	@Override
	public final Boolean vazio() {
		return (fornecerQuantidade() == 0);
	}
	
	@Override
	public final void inserir(Iteravel<E> elementos) {
		for (E elemento : elementos) {
			inserir(elemento);
		}
	}
	
	@Override
	public final void limpar() {
		Iterador<E> iterador = fornecerIterador();
		while (iterador.possuiProximo()) {
			iterador.iterarProximo();
			iterador.remover();
		}
	}
	
	protected final void lancarExcecaoDeElementoNuloSeNecessario(E elemento) {
		if (elemento == null) {
			throw new ExcecaoDeElementoNulo();
		}
	};
	
	protected final void lancarExcecaoDeEstruturaVaziaSeNecessario() {
		if (vazio()) {
			throw new ExcecaoDeEstruturaVazia();
		}
	}
}
