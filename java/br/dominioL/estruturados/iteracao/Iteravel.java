package br.dominioL.estruturados.iteracao;

public interface Iteravel<T> extends Iterable<T> {

	public Iterador<T> fornecerIterador();

}
