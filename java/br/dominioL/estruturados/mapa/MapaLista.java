package br.dominioL.estruturados.mapa;

import java.util.Iterator;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.excecoes.ExcecaoDeChaveNula;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.IteradorAbstrato;
import br.dominioL.estruturados.iteracao.Iteravel;

public final class MapaLista<C extends Codificavel & Igualavel<C>, V extends Igualavel<V>> implements Mapa<C, V>,
		Igualavel<MapaLista<C, V>>, Codificavel {
	private final static Integer TAMANHO_INICIAL = 10;
	private final static Integer FATOR_DE_CARGA = 1;
	private final static Integer FATOR_DE_CRESCIMENTO = 2;
	private Integer quantidadeDeElementos;
	private ListaPosicional<ListaEncadeada<ParDeMapaLista<C, V>>> elementos;
	private V valorNulo;

	private MapaLista() {
		quantidadeDeElementos = 0;
		elementos = ListaPosicional.criar(TAMANHO_INICIAL);
	}

	public static <D extends Igualavel<D> & Codificavel, U extends Igualavel<U>> MapaLista<D, U> criar() {
		return new MapaLista<D, U>();
	}

	@Override
	public Integer contarElementos() {
		return quantidadeDeElementos;
	}

	@Override
	public Boolean vazio() {
		return (quantidadeDeElementos == 0);
	}

	@Override
	public Boolean contem(C chave) {
		checarChave(chave);
		return obterGrupo(chave).contem(new ParDeMapaLista<C, V>(chave));
	}

	@Override
	public Boolean remover(C chave) {
		checarChave(chave);
		Boolean removido = obterGrupo(chave).remover(new ParDeMapaLista<C, V>(chave));
		if (removido) {
			quantidadeDeElementos--;
		}
		return removido;
	}

	@Override
	public void inserir(C chave, V valor) {
		checarChaveEValor(chave, valor);
		ListaEncadeada<ParDeMapaLista<C, V>> grupo = obterGrupo(chave);
		ParDeMapaLista<C, V> elemento = new ParDeMapaLista<C, V>(chave, valor);
		Boolean existia = grupo.remover(elemento);
		grupo.inserirNoInicio(elemento);
		if (!existia) {
			quantidadeDeElementos++;
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
		if (elemento != null) {
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
		if (chave == null) {
			throw new ExcecaoDeChaveNula();
		}
	}

	private void checarValor(V valor) {
		if (valor == null) {
			throw new ExcecaoDeElementoNulo();
		}
	}

	private void checarChaveEValor(C chave, V valor) {
		checarChave(chave);
		checarValor(valor);
	}

	private ListaEncadeada<ParDeMapaLista<C, V>> obterGrupo(C chave) {
		Integer posicaoDoGrupo = Math.abs(chave.codificar() % elementos.fornecerTamanho());
		ListaEncadeada<ParDeMapaLista<C, V>> grupo = elementos.fornecerDaPosicao(posicaoDoGrupo);
		if (grupo == null) {
			grupo = ListaEncadeada.criar();
			elementos.inserirNaPosicao(posicaoDoGrupo, grupo);
		}
		return grupo;
	}

	private void crescerSeNecessario() {
		if ((quantidadeDeElementos / elementos.fornecerTamanho()) >= FATOR_DE_CARGA) {
			ListaPosicional<ListaEncadeada<ParDeMapaLista<C, V>>> elementosAntigos = elementos;
			elementos = ListaPosicional.criar(quantidadeDeElementos * FATOR_DE_CRESCIMENTO);
			quantidadeDeElementos = 0;
			for (ListaEncadeada<ParDeMapaLista<C, V>> grupo : elementosAntigos) {
				for (ParDeMapaLista<C, V> par : grupo) {
					inserir(par.chave, par.valor);
				}
			}
		}
	}

	@Override
	public Boolean igual(MapaLista<C, V> outro) {
		return (this == outro);
	}

	@Override
	public Integer codificar() {
		Integer primo = 31;
		Integer codigo = 1;
		codigo = primo * codigo + ((elementos == null) ? 0 : elementos.codificar());
		codigo = primo * codigo + ((quantidadeDeElementos == null) ? 0 : quantidadeDeElementos.hashCode());
		return codigo;
	}

	private final class IteradorDeMapaLista extends IteradorAbstrato<Par<C, V>> implements Iterador<Par<C, V>> {
		private Iterador<ListaEncadeada<ParDeMapaLista<C, V>>> iteradorDosGrupos;
		private Iterador<ParDeMapaLista<C, V>> iteradorDoGrupo;
		private Iterador<ParDeMapaLista<C, V>> iteradorAtual;

		private IteradorDeMapaLista() {
			iteradorDosGrupos = elementos.fornecerIterador();
			if (iteradorDosGrupos.possuiProximo()) {
				do {
					iteradorDoGrupo = iteradorDosGrupos.iterarProximo().fornecerIterador();
				} while (!iteradorDoGrupo.possuiProximo() && iteradorDosGrupos.possuiProximo());
			}
		}

		@Override
		public Boolean possuiProximo() {
			return (iteradorDoGrupo != null && iteradorDoGrupo.possuiProximo());
		}

		@Override
		public Par<C, V> iterarProximo() {
			if (possuiProximo()) {
				iteradorAtual = iteradorDoGrupo;
				Par<C, V> elemento = iteradorAtual.iterarProximo();
				while (!iteradorDoGrupo.possuiProximo() && iteradorDosGrupos.possuiProximo()) {
					iteradorDoGrupo = iteradorDosGrupos.iterarProximo().fornecerIterador();
				}
				return elemento;
			}
			throw new ExcecaoDeIteracaoInvalida();
		}

		@Override
		public Par<C, V> remover() {
			if (iteradorAtual != null) {
				return iteradorAtual.remover();
			}
			throw new ExcecaoDeIteracaoInvalida();
		}

		@Override
		public Par<C, V> substituir(Par<C, V> substituto) {
			throw new ExcecaoDeIteracaoInvalida();
		}
	}

	private final class ParDeMapaLista<D extends Igualavel<D> & Codificavel, U extends Igualavel<U>> implements Par<C, V>,
			Igualavel<ParDeMapaLista<C, V>> {
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
		public Boolean igual(ParDeMapaLista<C, V> outra) {
			return chave.igual(outra.chave);
		}
	}
}
