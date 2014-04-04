package br.dominioL.estruturados.colecao.arvore;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.colecao.arvore.Arvore;
import br.dominioL.estruturados.elemento.Comparavel;
import br.dominioL.estruturados.iteracao.Iterador;

public final class ArvoreB<E extends Comparavel<E>> extends ColecaoAbstrata<E> implements Arvore<E> {
	private static final Integer ORDEM_PADRAO = 3;
	private static final Integer ORDEM_MINIMA = 3;
//	private Integer ordem;
	private Integer quantidadeDeElementos;

	private ArvoreB(Integer ordem) {
//		this.ordem = ordem;
		this.quantidadeDeElementos = 0;
	}

	public static <F extends Comparavel<F>> ArvoreB<F> criar() {
		return new ArvoreB<F>(ORDEM_PADRAO);
	}

	public static <F extends Comparavel<F>> ArvoreB<F> criar(Integer ordem) {
		return new ArvoreB<F>((ordem >= ORDEM_MINIMA) ? ordem : ORDEM_PADRAO);
	}

	@Override
	public E reter(E elemento) {
		//TODO
		return null;
	}

	@Override
	public Integer fornecerQuantidade() {
		return quantidadeDeElementos;
	}

	@Override
	public Boolean contem(E elemento) {
		//TODO
		return false;
	}

	@Override
	public void inserir(E elemento) {
		//TODO
	}

	@Override
	public Boolean remover(E elemento) {
		//TODO
		return false;
	}

	@Override
	public Iterador<E> fornecerIterador() {
		//TODO
		return null;
	}

//	private final class Nodo {
//		//TODO
//	}

//	private final class Entrada {
////		private E valor;
////		private Nodo proximoNodo;
//	}
}
