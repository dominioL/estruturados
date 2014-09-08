package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;

public final class ListaEncadeada<E extends Igualavel<E>> extends ListaAbstrata<E> implements Igualavel<ListaEncadeada<E>> {

	private Numero quantidadeDeElementos;
	private Caixa caixaDoInicio;
	private Caixa caixaDoFim;

	private ListaEncadeada() {
		quantidadeDeElementos = Numero.zero();
	}

	public static <F extends Igualavel<F>> ListaEncadeada<F> criar() {
		return new ListaEncadeada<F>();
	}

	public void inserirNoInicio(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		if (quantidadeDeElementos.igualZero().avaliar()) {
			caixaDoInicio = new Caixa(elemento);
			caixaDoFim = caixaDoInicio;
		} else {
			Caixa caixaDoInicioAntiga = caixaDoInicio;
			caixaDoInicio = new Caixa(elemento);
			caixaDoInicio.fixarCaixaDaDireita(caixaDoInicioAntiga);
			caixaDoInicioAntiga.fixarCaixaDaEsquerda(caixaDoInicio);
		}
		quantidadeDeElementos = quantidadeDeElementos.incrementar();
	}

	public void inserirNoFim(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		if (quantidadeDeElementos.igualZero().avaliar()) {
			caixaDoFim = new Caixa(elemento);
			caixaDoInicio = caixaDoFim;
		} else {
			Caixa caixaDoFimAntiga = caixaDoFim;
			caixaDoFim = new Caixa(elemento);
			caixaDoFim.fixarCaixaDaEsquerda(caixaDoFimAntiga);
			caixaDoFimAntiga.fixarCaixaDaDireita(caixaDoFim);
		}
		quantidadeDeElementos = quantidadeDeElementos.incrementar();
	}

	public E removerDoInicio() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		Caixa caixaDoInicioNova = caixaDoInicio.fornecerCaixaDaDireita();
		E elemento = caixaDoInicio.fornecerElemento();
		caixaDoInicio.removerPonteiros();
		caixaDoInicio = caixaDoInicioNova;
		if (Booleano.nulo(caixaDoInicio).negar().avaliar()) {
			caixaDoInicio.fixarCaixaDaEsquerda(null);
		} else {
			caixaDoFim = null;
		}
		quantidadeDeElementos = quantidadeDeElementos.decrementar();
		return elemento;
	}

	public E removerDoFim() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		Caixa caixaDoFimNova = caixaDoFim.fornecerCaixaDaEsquerda();
		E elemento = caixaDoFim.fornecerElemento();
		caixaDoFim.removerPonteiros();
		caixaDoFim = caixaDoFimNova;
		if (Booleano.nulo(caixaDoFim).negar().avaliar()) {
			caixaDoFim.fixarCaixaDaDireita(null);
		} else {
			caixaDoInicio = null;
		}
		quantidadeDeElementos = quantidadeDeElementos.decrementar();
		return elemento;
	}

	public E fornecerDoInicio() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		return caixaDoInicio.fornecerElemento();
	}

	public E fornecerDoFim() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		return caixaDoFim.fornecerElemento();
	}

	@Override
	public Numero contarElementos() {
		return quantidadeDeElementos;
	}

	@Override
	public void inserir(E elemento) {
		inserirNoFim(elemento);
	}

	@Override
	public Iterador<E> fornecerIterador() {
		return new IteradorDeListaEncadeada();
	}

	@Override
	public Booleano igual(ListaEncadeada<E> outro) {
		return Booleano.mesmo(this, outro);
	}

	private final class IteradorDeListaEncadeada extends IteradorAbstrato<E> implements Iterador<E> {

		private Caixa cursor;
		private Caixa cursorAnterior;
		private Booleano removeu;
		private Booleano substituiu;

		private IteradorDeListaEncadeada() {
			cursor = caixaDoInicio;
			cursorAnterior = null;
			removeu = Booleano.falso();
			substituiu = Booleano.falso();
		}

		@Override
		public Booleano possuiProximo() {
			return Booleano.nulo(cursor).negar();
		}

		@Override
		public E iterarProximo() {
			if (possuiProximo().negar().avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			E elementoAtual = cursor.fornecerElemento();
			cursorAnterior = cursor;
			cursor = cursor.fornecerCaixaDaDireita();
			removeu = Booleano.falso();
			substituiu = Booleano.falso();
			return elementoAtual;
		}

		@Override
		public E remover() {
			if (removeu.ou(Booleano.nulo(cursorAnterior)).avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			E elementoRemovido = cursorAnterior.fornecerElemento();
			Caixa caixaDaEsquerdaNova = cursorAnterior.fornecerCaixaDaEsquerda();
			ajustarPonteiroDoCursor(caixaDaEsquerdaNova);
			ajustarPonteiroDoProximoCursor(caixaDaEsquerdaNova);
			cursorAnterior.removerPonteiros();
			removeu = Booleano.verdadeiro();
			quantidadeDeElementos = quantidadeDeElementos.decrementar();
			return elementoRemovido;
		}

		private void ajustarPonteiroDoProximoCursor(Caixa caixaDaEsquerdaNova) {
			if (Booleano.nulo(caixaDaEsquerdaNova).negar().avaliar()) {
				caixaDaEsquerdaNova.fixarCaixaDaDireita(cursor);
			} else {
				caixaDoInicio = cursor;
			}
		}

		private void ajustarPonteiroDoCursor(Caixa caixaDaEsquerdaNova) {
			if (Booleano.nulo(cursor).negar().avaliar()) {
				cursor.fixarCaixaDaEsquerda(caixaDaEsquerdaNova);
			} else {
				caixaDoFim = caixaDaEsquerdaNova;
			}
		}

		@Override
		public E substituir(E substituto) {
			lancarExcecaoDeElementoNuloSeNecessario(substituto);
			if (removeu.ou(substituiu).ou(Booleano.nulo(cursorAnterior)).avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			E elementoSubstituido = cursorAnterior.fornecerElemento();
			cursorAnterior.fixarElemento(substituto);
			substituiu = Booleano.verdadeiro();
			return elementoSubstituido;
		}

	}

	private final class Caixa {

		private E elemento;
		private Caixa caixaDaEsquerda;
		private Caixa caixaDaDireita;

		private Caixa(E elemento) {
			this.elemento = elemento;
		}

		private void fixarCaixaDaEsquerda(Caixa caixa) {
			this.caixaDaEsquerda = caixa;
		}

		private void fixarCaixaDaDireita(Caixa caixa) {
			this.caixaDaDireita = caixa;
		}

		private void fixarElemento(E elemento) {
			this.elemento = elemento;
		}

		private Caixa fornecerCaixaDaEsquerda() {
			return caixaDaEsquerda;
		}

		private Caixa fornecerCaixaDaDireita() {
			return caixaDaDireita;
		}

		private E fornecerElemento() {
			return elemento;
		}

		private void removerPonteiros() {
			elemento = null;
			caixaDaEsquerda = null;
			caixaDaDireita = null;
		}

	}

}
