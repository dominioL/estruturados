package br.opp.estruturados.colecao;

import br.opp.estruturados.elemento.Igualavel;
import br.opp.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.opp.estruturados.excecoes.ExcecaoDeEstruturaVazia;
import br.opp.estruturados.iteracao.Iterador;
import br.opp.estruturados.iteracao.Iteravel;
import br.opp.estruturados.iteracao.IteravelAbstrato;

public abstract class ColecaoAbstrata<E extends Igualavel<E>> extends IteravelAbstrato<E> implements Colecao<E> {
	protected ColecaoAbstrata() {
		
	}
	
	@Override
	public final boolean vazio() {
		return (fornecerQuantidade() == 0);
	}
	
	@Override
	public final void inserir(Iteravel<E> elementos) {
		for (E elemento : elementos) {
			inserir(elemento);
		}
	}
	
	protected final void lancarExcecaoDeElementoNuloSeNecessario(E elemento) {
		if (elemento == null) {
			throw new ExcecaoDeElementoNulo();
		}
	};
	
	protected void lancarExcecaoDeEstruturaVaziaSeNecessario() {
		if (vazio()) {
			throw new ExcecaoDeEstruturaVazia();
		}
	}
	
	@Override
	public void limpar() {
		Iterador<E> iterador = fornecerIterador();
		while (iterador.possuiProximo()) {
			iterador.iterarProximo();
			iterador.remover();
		}
	}
}

