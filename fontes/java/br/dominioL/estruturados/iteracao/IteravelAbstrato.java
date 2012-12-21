package br.dominioL.estruturados.iteracao;

import java.util.Iterator;

public abstract class IteravelAbstrato<T> implements Iteravel<T> {
	protected IteravelAbstrato() {
		
	}
	
	@Override
	public final Iterator<T> iterator() {
		return fornecerIterador();
	}
}
