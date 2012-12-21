package br.opp.estruturados.iteracao;

public abstract class IteradorAbstrato<T> implements Iterador<T> {
	protected IteradorAbstrato() {
		
	}
	
	@Override
	public final boolean hasNext() {
		return possuiProximo();
	}
	
	@Override
	public final T next() {
		return iterarProximo();
	}
	
	@Override
	public final void remove() {
		remover();
	}
}

