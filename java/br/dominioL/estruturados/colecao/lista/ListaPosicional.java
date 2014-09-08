package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoIndiceForaDosLimites;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.excecoes.ExcecaoTamanhoInvalido;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;

public final class ListaPosicional<E extends Igualavel<E>> extends ListaAbstrata<E> implements Igualavel<ListaPosicional<E>> {

	private static final Numero TAMANHO_PADRAO = Numero.criar(10);
	private static final Numero FATOR_DE_CRESCIMENTO = Numero.criar(2);

	private Object[] elementos;
	private Numero quantidadeDeElementos;

	private ListaPosicional(Numero tamanho) {
		if (tamanho.menorOuIgualQueZero().avaliar()) {
			throw new ExcecaoTamanhoInvalido();
		}
		elementos = new Object[tamanho.inteiro()];
		quantidadeDeElementos = Numero.zero();
	}

	public static <F extends Igualavel<F>> ListaPosicional<F> criar() {
		return new ListaPosicional<F>(TAMANHO_PADRAO);
	}

	public static <F extends Igualavel<F>> ListaPosicional<F> criar(Numero tamanho) {
		return new ListaPosicional<F>(tamanho);
	}

	public void inserirNaPosicao(Numero posicao, E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		lancarExcecaoDeIndiceForaDosLimitesSeNecessario(posicao);
		if (Booleano.nulo(obterElemento(posicao)).avaliar()) {
			quantidadeDeElementos = quantidadeDeElementos.incrementar();
		}
		atribuirElemento(posicao, elemento);
	}

	public E fornecerDaPosicao(Numero posicao) {
		lancarExcecaoDeIndiceForaDosLimitesSeNecessario(posicao);
		return castrar(obterElemento(posicao));
	}

	public E removerDaPosicao(Numero posicao) {
		lancarExcecaoDeIndiceForaDosLimitesSeNecessario(posicao);
		Object elemento = obterElemento(posicao);
		if (Booleano.nulo(elemento).negar().avaliar()) {
			quantidadeDeElementos = quantidadeDeElementos.decrementar();
		}
		atribuirElemento(posicao, null);
		return castrar(elemento);
	}

	public Numero fornecerTamanho() {
		return obterTamanho();
	}

	public void fixarValorNulo(E elemento) {
		valorNulo = elemento;
	}

	@Override
	public Numero contarElementos() {
		return quantidadeDeElementos;
	}

	@Override
	public void inserir(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		crescerSeNecessario();
		if (Booleano.nulo(obterElemento(quantidadeDeElementos)).avaliar()) {
			atribuirElemento(quantidadeDeElementos, elemento);
		} else {
			Booleano encontrouPosicao = Booleano.falso();
			Numero posicao = Numero.zero();
			while (encontrouPosicao.negar().avaliar()) {
				if (Booleano.nulo(obterElemento(posicao)).avaliar()) {
					atribuirElemento(posicao, elemento);
					encontrouPosicao = Booleano.verdadeiro();
				}
				posicao = posicao.incrementar();
			}
		}
		quantidadeDeElementos = quantidadeDeElementos.incrementar();
	}

	@Override
	public Iterador<E> fornecerIterador() {
		return new IteradorDeListaPosicionnal();
	}

	@Override
	public Booleano igual(ListaPosicional<E> outro) {
		return Booleano.mesmo(this, outro);
	}

	private void crescerSeNecessario() {
		if (quantidadeDeElementos.igual(obterTamanho()).avaliar()) {
			Object[] novoElementos = new Object[obterTamanho().multiplicar(FATOR_DE_CRESCIMENTO).inteiro()];
			Numero posicao = Numero.zero();
			for (Object elemento : elementos) {
				novoElementos[posicao.inteiro()] = elemento;
				posicao = posicao.incrementar();
			}
			elementos = novoElementos;
		}
	}

	private void lancarExcecaoDeIndiceForaDosLimitesSeNecessario(Numero indice) {
		if (indice.menorQueZero().avaliar() || indice.maiorOuIgualQue(obterTamanho()).avaliar()) {
			throw new ExcecaoIndiceForaDosLimites();
		}
	}

	private Object obterElemento(Numero posicao) {
		return elementos[posicao.inteiro()];
	}

	private void atribuirElemento(Numero posicao, E elemento) {
		elementos[posicao.inteiro()] = elemento;
	}

	private Numero obterTamanho() {
		return Numero.criar(elementos.length);
	}

	@SuppressWarnings("unchecked")
	private E castrar(Object elemento) {
		return (Booleano.nulo(elemento).avaliar()) ? valorNulo : (E) elemento;
	}

	private final class IteradorDeListaPosicionnal extends IteradorAbstrato<E> implements Iterador<E> {

		private Numero cursor;
		private Numero cursorAnterior;
		private Booleano removeu;
		private Booleano substituiu;

		private IteradorDeListaPosicionnal() {
			cursor = Numero.um().negativo();
			avancarCursor();
			removeu = Booleano.falso();
			substituiu = Booleano.falso();
		}

		@Override
		public Booleano possuiProximo() {
			return (cursor.menorQue(obterTamanho()));
		}

		@Override
		public E iterarProximo() {
			if (possuiProximo().negar().avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			removeu = Booleano.falso();
			substituiu = Booleano.falso();
			avancarCursor();
			return fornecerDaPosicao(cursorAnterior);
		}

		@Override
		public E remover() {
			if (removeu.ou(naoHouveIteracao()).avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			removeu = Booleano.verdadeiro();
			return removerDaPosicao(cursorAnterior);
		}

		@Override
		public E substituir(E substituto) {
			if (removeu.ou(substituiu).ou(naoHouveIteracao()).avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			substituiu = Booleano.verdadeiro();
			E elemento = removerDaPosicao(cursorAnterior);
			inserirNaPosicao(cursorAnterior, substituto);
			return elemento;
		}

		private Booleano naoHouveIteracao() {
			return cursorAnterior.igual(Numero.criar(-1));
		}

		private void avancarCursor() {
			cursor = cursor.incrementar();
			cursorAnterior = cursor.decrementar();
			while (cursor.menorQue(obterTamanho()).avaliar() && Booleano.nulo(obterElemento(cursor)).avaliar()) {
				cursor = cursor.incrementar();
			}
		}

	}

}
