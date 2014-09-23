package br.dominioL.estruturados.iteracao;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;

public final class Contador extends IteravelAbstrato<Numero> implements Iteravel<Numero> {

	private Numero primeiro;
	private Numero ultimo;

	private Contador(Numero inicio, Numero fim) {
		this.primeiro = inicio;
		this.ultimo = fim;
	}

	public static ContadorDe de(Numero inicio) {
		return new ContadorDe(inicio);
	}

	public static Contador ate(Numero fim) {
		return new ContadorDe(Numero.zero()).ate(fim);
	}

	public static Contador ateCom(Numero fim) {
		return new ContadorDe(Numero.zero()).ateCom(fim);
	}

	@Override
	public Iterador<Numero> fornecerIterador() {
		return primeiro.maiorQue(ultimo).avaliar() ? new ContagemInvertida() : new Contagem();
	}

	public static class ContadorDe {

		private Numero primeiro;

		private ContadorDe(Numero fim) {
			this.primeiro = fim;
		}

		public Contador ate(Numero limite) {
			return new Contador(primeiro, limite);
		}

		public Contador ateCom(Numero fim) {
			Numero fimCom = primeiro.maiorQue(fim).avaliar() ? fim.decrementar() : fim.incrementar();
			return new Contador(primeiro, fimCom);
		}

	}

	private abstract class ContagemAbstrata extends IteradorAbstrato<Numero> implements Iterador<Numero> {

		protected Numero cursor;
		protected Numero cursorFinal;

		public ContagemAbstrata() {
			this.cursor = primeiro;
			this.cursorFinal = ultimo;
		}

		@Override
		public final Numero iterarProximo() {
			if (possuiProximo().negar().avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			Numero valor = cursor;
			cursor = avancarCursor();
			return valor;
		}

		@Override
		public final Numero remover() {
			throw new ExcecaoIteracaoInvalida();
		}

		@Override
		public final Numero substituir(Numero substituto) {
			throw new ExcecaoIteracaoInvalida();
		}

		public abstract Numero avancarCursor();

	}

	private class Contagem extends ContagemAbstrata implements Iterador<Numero> {

		@Override
		public Booleano possuiProximo() {
			return cursor.menorQue(cursorFinal);
		}

		@Override
		public Numero avancarCursor() {
			return cursor.incrementar();
		}

	}

	private class ContagemInvertida extends ContagemAbstrata implements Iterador<Numero> {

		@Override
		public Booleano possuiProximo() {
			return cursor.maiorQue(cursorFinal);
		}

		@Override
		public Numero avancarCursor() {
			return cursor.decrementar();
		}

	}

}
