package br.dominioL.estruturados.colecao.lista;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;

public final class ListaEncadeada<E extends Igualavel<E>> extends ListaAbstrata<E> implements Igualavel<ListaEncadeada<E>> {
	private Integer quantidadeDeElementos;
	private Caixa caixaDoInicio;
	private Caixa caixaDoFim;

	private ListaEncadeada() {
		quantidadeDeElementos = 0;
	}

	public static <F extends Igualavel<F>> ListaEncadeada<F> criar() {
		return new ListaEncadeada<F>();
	}

	public void inserirNoInicio(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		if (quantidadeDeElementos == 0) {
			caixaDoInicio = new Caixa(elemento);
			caixaDoFim = caixaDoInicio;
		} else {
			Caixa caixaDoInicioAntiga = caixaDoInicio;
			caixaDoInicio = new Caixa(elemento);
			caixaDoInicio.fixarCaixaDaDireita(caixaDoInicioAntiga);
			caixaDoInicioAntiga.fixarCaixaDaEsquerda(caixaDoInicio);
		}
		quantidadeDeElementos++;
	}

	public void inserirNoFim(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		if (quantidadeDeElementos == 0) {
			caixaDoFim = new Caixa(elemento);
			caixaDoInicio = caixaDoFim;
		} else {
			Caixa caixaDoFimAntiga = caixaDoFim;
			caixaDoFim = new Caixa(elemento);
			caixaDoFim.fixarCaixaDaEsquerda(caixaDoFimAntiga);
			caixaDoFimAntiga.fixarCaixaDaDireita(caixaDoFim);
		}
		quantidadeDeElementos++;
	}

	public E removerDoInicio() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		Caixa caixaDoInicioNova = caixaDoInicio.fornecerCaixaDaDireita();
		E elemento = caixaDoInicio.fornecerElemento();
		caixaDoInicio.removerPonteiros();
		caixaDoInicio = caixaDoInicioNova;
		if (caixaDoInicio != null) {
			caixaDoInicio.fixarCaixaDaEsquerda(null);
		} else {
			caixaDoFim = null;
		}
		quantidadeDeElementos--;
		return elemento;
	}

	public E removerDoFim() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		Caixa caixaDoFimNova = caixaDoFim.fornecerCaixaDaEsquerda();
		E elemento = caixaDoFim.fornecerElemento();
		caixaDoFim.removerPonteiros();
		caixaDoFim = caixaDoFimNova;
		if (caixaDoFim != null) {
			caixaDoFim.fixarCaixaDaDireita(null);
		} else {
			caixaDoInicio = null;
		}
		quantidadeDeElementos--;
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
	public Integer contarElementos() {
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
	public Boolean igual(ListaEncadeada<E> outro) {
		return (this == outro);
	}

//	@Override
//	public Integer codificar() {
//		Integer primo = 31;
//		Integer resultado = 1;
//		resultado = primo * resultado + ((caixaDoFim == null) ? 0 : caixaDoFim.codificar());
//		resultado = primo * resultado + ((caixaDoInicio == null) ? 0 : caixaDoInicio.codificar());
//		resultado = primo * resultado + ((quantidadeDeElementos == null) ? 0 : quantidadeDeElementos.hashCode());
//		return resultado;
//	}

	private final class IteradorDeListaEncadeada extends IteradorAbstrato<E> implements Iterador<E> {
		private Caixa cursor;
		private Caixa cursorAnterior;
		private Boolean removeu;
		private Boolean substituiu;

		private IteradorDeListaEncadeada() {
			cursor = caixaDoInicio;
			cursorAnterior = null;
			removeu = false;
			substituiu = false;
		}

		@Override
		public Boolean possuiProximo() {
			return (cursor != null);
		}

		@Override
		public E iterarProximo() {
			if (!possuiProximo()) {
				throw new ExcecaoIteracaoInvalida();
			}
			E elementoAtual = cursor.fornecerElemento();
			cursorAnterior = cursor;
			cursor = cursor.fornecerCaixaDaDireita();
			removeu = false;
			substituiu = false;
			return elementoAtual;
		}

		@Override
		public E remover() {
			if (removeu || cursorAnterior == null) {
				throw new ExcecaoIteracaoInvalida();
			}
			E elementoRemovido = cursorAnterior.fornecerElemento();
			Caixa caixaDaEsquerdaNova = cursorAnterior.fornecerCaixaDaEsquerda();
			if (cursor != null) {
				cursor.fixarCaixaDaEsquerda(caixaDaEsquerdaNova);
			} else {
				caixaDoFim = caixaDaEsquerdaNova;
			}
			if (caixaDaEsquerdaNova != null) {
				caixaDaEsquerdaNova.fixarCaixaDaDireita(cursor);
			} else {
				caixaDoInicio = cursor;
			}
			cursorAnterior.removerPonteiros();
			removeu = true;
			quantidadeDeElementos--;
			return elementoRemovido;
		}

		@Override
		public E substituir(E substituto) {
			lancarExcecaoDeElementoNuloSeNecessario(substituto);
			if (removeu || substituiu || cursorAnterior == null) {
				throw new ExcecaoIteracaoInvalida();
			}
			E elementoSubstituido = cursorAnterior.fornecerElemento();
			cursorAnterior.fixarElemento(substituto);
			substituiu = true;
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

//		@Override
//		public Integer codificar() {
//			Integer primo = 31;
//			Integer codigo = 1;
//			codigo = primo * codigo + ListaEncadeada.this.hashCode();
//			if (elemento != null) {
//				codigo = primo * codigo;
//			} else if (elemento instanceof Codificavel) {
//				codigo = primo * codigo + ((Codificavel) elemento).codificar();
//			} else {
//				codigo = primo * codigo + elemento.hashCode();
//			}
//			return codigo;
//		}
	}
}
