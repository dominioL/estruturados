package br.dominioL.estruturados.mapa;

import java.util.Iterator;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoChaveNula;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;
import br.dominioL.estruturados.iteracao.Iteravel;

public final class MapaLista<C extends Codificavel & Igualavel<C>, V extends Igualavel<V>> implements Mapa<C, V>, Igualavel<MapaLista<C, V>> {

	private final static Numero TAMANHO_INICIAL = Numero.criar(10);
	private final static Numero FATOR_DE_CARGA = Numero.criar(1);
	private final static Numero FATOR_DE_CRESCIMENTO = Numero.criar(2);

	private Numero quantidadeDeElementos;
	private ListaPosicional<ListaEncadeada<ParDeMapaLista<C, V>>> elementos;
	private V valorNulo;

	private MapaLista() {
		quantidadeDeElementos = Numero.zero();
		elementos = ListaPosicional.criar(TAMANHO_INICIAL);
	}

	public static <D extends Igualavel<D> & Codificavel, U extends Igualavel<U>> MapaLista<D, U> criar() {
		return new MapaLista<D, U>();
	}

	@Override
	public Numero contarElementos() {
		return quantidadeDeElementos;
	}

	@Override
	public Booleano vazio() {
		return quantidadeDeElementos.igualZero();
	}

	@Override
	public Booleano contem(C chave) {
		checarChave(chave);
		return obterGrupo(chave).contem(new ParDeMapaLista<C, V>(chave));
	}

	@Override
	public Booleano remover(C chave) {
		checarChave(chave);
		Booleano removido = obterGrupo(chave).remover(new ParDeMapaLista<C, V>(chave));
		if (removido.avaliar()) {
			quantidadeDeElementos = quantidadeDeElementos.decrementar();
		}
		return removido;
	}

	@Override
	public void inserir(C chave, V valor) {
		checarChaveEValor(chave, valor);
		ListaEncadeada<ParDeMapaLista<C, V>> grupo = obterGrupo(chave);
		ParDeMapaLista<C, V> elemento = new ParDeMapaLista<C, V>(chave, valor);
		Booleano existia = grupo.remover(elemento);
		grupo.inserirNoInicio(elemento);
		if (existia.negar().avaliar()) {
			quantidadeDeElementos = quantidadeDeElementos.incrementar();
			crescerSeNecessario();
		}
	}

	@Override
	public void inserir(Iteravel<Par<C, V>> elementos) {
		for (Par<C, V> par : elementos) {
			inserir(par.fornecerChave(), par.fornecerValor());
		}
	}

	@Override
	public V fornecer(C chave) {
		checarChave(chave);
		ListaEncadeada<ParDeMapaLista<C, V>> grupo = obterGrupo(chave);
		ParDeMapaLista<C, V> elemento = grupo.reter(new ParDeMapaLista<C, V>(chave));
		if (Booleano.nulo(elemento).negar().avaliar()) {
			return elemento.valor;
		}
		return valorNulo;
	}

	@Override
	public void fixarValorNulo(V valor) {
		this.valorNulo = valor;
	}

	@Override
	public ListaEncadeada<C> chaves() {
		ListaEncadeada<C> chaves = ListaEncadeada.criar();
		for (Par<C, V> par : this) {
			chaves.inserirNoInicio(par.fornecerChave());
		}
		return chaves;
	}

	@Override
	public ListaEncadeada<V> valores() {
		ListaEncadeada<V> valores = ListaEncadeada.criar();
		for (Par<C, V> par : this) {
			valores.inserirNoInicio(par.fornecerValor());
		}
		return valores;
	}

	@Override
	public Iterador<Par<C, V>> fornecerIterador() {
		return new IteradorDeMapaLista();
	}

	@Override
	public Iterator<Par<C, V>> iterator() {
		return fornecerIterador();
	}

	private void checarChave(C chave) {
		if (Booleano.nulo(chave).avaliar()) {
			throw new ExcecaoChaveNula();
		}
	}

	private void checarValor(V valor) {
		if (Booleano.nulo(valor).avaliar()) {
			throw new ExcecaoElementoNulo();
		}
	}

	private void checarChaveEValor(C chave, V valor) {
		checarChave(chave);
		checarValor(valor);
	}

	private ListaEncadeada<ParDeMapaLista<C, V>> obterGrupo(C chave) {
		Numero posicaoDoGrupo = chave.codificar().resto(elementos.fornecerTamanho());
		ListaEncadeada<ParDeMapaLista<C, V>> grupo = elementos.fornecerDaPosicao(posicaoDoGrupo);
		if (Booleano.nulo(grupo).avaliar()) {
			grupo = ListaEncadeada.criar();
			elementos.inserirNaPosicao(posicaoDoGrupo, grupo);
		}
		return grupo;
	}

	private void crescerSeNecessario() {
		if (quantidadeDeElementos.dividir(elementos.fornecerTamanho()).maiorOuIgualQue(FATOR_DE_CARGA).avaliar()) {
			ListaPosicional<ListaEncadeada<ParDeMapaLista<C, V>>> elementosAntigos = elementos;
			elementos = ListaPosicional.criar(quantidadeDeElementos.multiplicar(FATOR_DE_CRESCIMENTO));
			quantidadeDeElementos = Numero.zero();
			for (ListaEncadeada<ParDeMapaLista<C, V>> grupo : elementosAntigos) {
				for (ParDeMapaLista<C, V> par : grupo) {
					inserir(par.chave, par.valor);
				}
			}
		}
	}

	@Override
	public Booleano igual(MapaLista<C, V> outro) {
		return Booleano.mesmo(this, outro);
	}

	private final class IteradorDeMapaLista extends IteradorAbstrato<Par<C, V>> implements Iterador<Par<C, V>> {

		private Iterador<ListaEncadeada<ParDeMapaLista<C, V>>> iteradorDosGrupos;
		private Iterador<ParDeMapaLista<C, V>> iteradorDoGrupo;
		private Iterador<ParDeMapaLista<C, V>> iteradorAtual;

		private IteradorDeMapaLista() {
			iteradorDosGrupos = elementos.fornecerIterador();
			if (iteradorDosGrupos.possuiProximo().avaliar()) {
				do {
					iteradorDoGrupo = iteradorDosGrupos.iterarProximo().fornecerIterador();
				} while (iteradorDoGrupo.possuiProximo().negar().avaliar() && iteradorDosGrupos.possuiProximo().avaliar());
			}
		}

		@Override
		public Booleano possuiProximo() {
			return Booleano.criar(Booleano.nulo(iteradorDoGrupo).negar().avaliar() && iteradorDoGrupo.possuiProximo().avaliar());
		}

		@Override
		public Par<C, V> iterarProximo() {
			if (possuiProximo().avaliar()) {
				iteradorAtual = iteradorDoGrupo;
				Par<C, V> elemento = iteradorAtual.iterarProximo();
				while (iteradorDoGrupo.possuiProximo().negar().avaliar() && iteradorDosGrupos.possuiProximo().avaliar()) {
					iteradorDoGrupo = iteradorDosGrupos.iterarProximo().fornecerIterador();
				}
				return elemento;
			}
			throw new ExcecaoIteracaoInvalida();
		}

		@Override
		public Par<C, V> remover() {
			if (Booleano.nulo(iteradorAtual).negar().avaliar()) {
				return iteradorAtual.remover();
			}
			throw new ExcecaoIteracaoInvalida();
		}

		@Override
		public Par<C, V> substituir(Par<C, V> substituto) {
			throw new ExcecaoIteracaoInvalida();
		}

	}

	private final class ParDeMapaLista<D extends Igualavel<D> & Codificavel, U extends Igualavel<U>> implements Par<C, V>, Igualavel<ParDeMapaLista<C, V>> {

		private C chave;
		private V valor;

		private ParDeMapaLista(C chave, V valor) {
			this.chave = chave;
			this.valor = valor;
		}

		private ParDeMapaLista(C chave) {
			this.chave = chave;
			this.valor = null;
		}

		@Override
		public C fornecerChave() {
			return chave;
		}

		@Override
		public V fornecerValor() {
			return valor;
		}

		@Override
		public Booleano igual(ParDeMapaLista<C, V> outra) {
			return chave.igual(outra.chave);
		}

	}

}
