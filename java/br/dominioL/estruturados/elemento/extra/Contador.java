package br.dominioL.estruturados.elemento.extra;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;
import br.dominioL.estruturados.iteracao.Iteravel;
import br.dominioL.estruturados.iteracao.IteravelAbstrato;

public final class Contador extends IteravelAbstrato<Numero> implements Iteravel<Numero> {

	private Numero inicio;
	private Numero limite;

	private Contador(Numero inicio, Numero limite) {
		this.inicio = inicio;
		this.limite = limite;
	}

	public static ContadorDe de(Numero inicio) {
		return new ContadorDe(inicio);
	}

	public static Contador ate(Numero limite) {
		return new ContadorDe(Numero.zero()).ate(limite);
	}

	@Override
	public Iterador<Numero> fornecerIterador() {
		return new Contagem();
	}

	public static class ContadorDe {

		private Numero inicio;

		private ContadorDe(Numero inicio) {
			this.inicio = inicio;
		}

		public Contador ate(Numero limite) {
			return new Contador(inicio, limite);
		}

	}

	private class Contagem extends IteradorAbstrato<Numero> implements Iterador<Numero> {

		private Numero cursor = inicio;

		@Override
		public Booleano possuiProximo() {
			return cursor.menorQue(limite);
		}

		@Override
		public Numero iterarProximo() {
			if (cursor.maiorOuIgualQue(limite).avaliar()) {
				throw new ExcecaoIteracaoInvalida();
			}
			Numero valor = cursor;
			cursor = cursor.incrementar();
			return valor;
		}

		@Override
		public Numero remover() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Numero substituir(Numero substituto) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
