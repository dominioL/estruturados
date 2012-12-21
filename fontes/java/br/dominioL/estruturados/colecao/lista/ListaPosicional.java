package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.Posicionavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.excecoes.ExcecaoDeIndiceForaDosLimites;
import br.dominioL.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.dominioL.estruturados.excecoes.ExcecaoDeTamanhoInvalido;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;

public final class ListaPosicional<E extends Igualavel<E>> extends ListaAbstrata<E> implements Posicionavel<E>, Igualavel<ListaPosicional<E>> {
	private static final Integer TAMANHO_PADRAO = 10;
	private static final Integer FATOR_DE_CRESCIMENTO = 2;
	private Object[] elementos;
	private Integer quantidadeDeElementos;
	
	private ListaPosicional(Integer tamanho) {
		if (tamanho <= 0) {
			throw new ExcecaoDeTamanhoInvalido();
		}
		elementos = new Object[tamanho];
		quantidadeDeElementos = 0;
	}
	
	public static <F extends Igualavel<F>> ListaPosicional<F> criar() {
		return new ListaPosicional<F>(TAMANHO_PADRAO);
	}
	
	public static <F extends Igualavel<F>> ListaPosicional<F> criar(Integer tamanho) {
		return new ListaPosicional<F>(tamanho);
	}
	
	@Override
	public void inserirNaPosicao(Integer posicao, E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		lancarExcecaoDeIndiceForaDosLimitesSeNecessario(posicao);
		if (elementos[posicao] == null) {
			quantidadeDeElementos++;
		}
		elementos[posicao] = elemento;
	}
	
	@Override
	public E fornecerDaPosicao(Integer posicao) {
		lancarExcecaoDeIndiceForaDosLimitesSeNecessario(posicao);
		return castrar(elementos[posicao]);
	}
	
	@Override
	public E removerDaPosicao(Integer posicao) {
		lancarExcecaoDeIndiceForaDosLimitesSeNecessario(posicao);
		Object elemento = elementos[posicao];
		if (elemento != null) {
			quantidadeDeElementos--;
		}
		elementos[posicao] = null;
		return castrar(elemento);
	}
	
	@Override
	public Integer fornecerQuantidade() {
		return quantidadeDeElementos;
	}
	
	public Integer fornecerTamanho() {
		return elementos.length;
	}
	
	@Override
	public void inserir(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		crescerSeNecessario();
		if (elementos[quantidadeDeElementos] == null) {
			elementos[quantidadeDeElementos] = elemento;
		} else {
			Boolean encontrouPosicao = false;
			Integer posicao = 0;
			while (!encontrouPosicao) {
				if (elementos[posicao] == null) {
					elementos[posicao] = elemento;
					encontrouPosicao = true;
				}
				posicao++;
			}
		}
		quantidadeDeElementos++;
	}
	
	@Override
	public Iterador<E> fornecerIterador() {
		return new IteradorDeListaPosicionnal();
	}
	
	private void crescerSeNecessario() {
		if (quantidadeDeElementos == elementos.length) {
			Object[] novoElementos = new Object[elementos.length * FATOR_DE_CRESCIMENTO];
			Integer posicao = 0;
			for (Object elemento : elementos) {
				novoElementos[posicao++] = elemento;
			}
			elementos = novoElementos;
		}
	}
	
	private void lancarExcecaoDeIndiceForaDosLimitesSeNecessario(Integer indice) {
		if (indice < 0 || indice >= elementos.length) {
			throw new ExcecaoDeIndiceForaDosLimites();
		}
	}
	
	@SuppressWarnings("unchecked")
	private E castrar(Object elemento) {
		return (E) elemento;
	}
	
	private final class IteradorDeListaPosicionnal extends IteradorAbstrato<E> {
		private Integer cursor;
		private Integer cursorAnterior;
		private Boolean removeu;
		private Boolean substituiu;
		
		private IteradorDeListaPosicionnal() {
			cursor = -1;
			avancarCursor();
			removeu = false;
			substituiu = false;
		}
		
		@Override
		public Boolean possuiProximo() {
			return (cursor < elementos.length);
		}
		
		@Override
		public E iterarProximo() {
			if (!possuiProximo()) {
				throw new ExcecaoDeIteracaoInvalida();
			}
			removeu = false;
			substituiu = false;
			avancarCursor();
			return fornecerDaPosicao(cursorAnterior);
		}
		
		@Override
		public E remover() {
			if (removeu || cursorAnterior == -1) {
				throw new ExcecaoDeIteracaoInvalida();
			}
			removeu = true;
			return removerDaPosicao(cursorAnterior);
		}
		
		@Override
		public E substituir(E substituto) {
			if (removeu || substituiu || cursorAnterior == -1) {
				throw new ExcecaoDeIteracaoInvalida();
			}
			substituiu = true;
			E elemento = removerDaPosicao(cursorAnterior);
			inserirNaPosicao(cursorAnterior, substituto);
			return elemento;
		}
		
		private void avancarCursor() {
			cursorAnterior = cursor++;
			while (cursor < elementos.length && elementos[cursor] == null) {
				cursor++;
			}
		}
	}
	
	@Override
	public Boolean igual(ListaPosicional<E> outro) {
		return (this == outro);
	}
}
