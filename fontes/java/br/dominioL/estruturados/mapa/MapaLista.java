package br.dominioL.estruturados.mapa;

import java.util.Iterator;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.excecoes.ExcecaoDeChaveNula;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.iteracao.Iterador;

public final class MapaLista<C extends Codificavel<C>, V extends Igualavel<V>> implements Mapa<C, V> {
	private final static Integer TAMANHO_INICIAL = 10;
	private final static Integer FATOR_DE_CARGA = 1;
	private final static Integer FATOR_DE_CRESCIMENTO = 2;
	private Integer quantidadeDeElementos;
	private ListaPosicional<ListaEncadeada<Caixa<C, V>>> elementos;
	
	private MapaLista() {
		quantidadeDeElementos = 0;
		elementos = ListaPosicional.criar(TAMANHO_INICIAL);
	}
	
	public static <D extends Codificavel<D>, U extends Igualavel<U>> MapaLista<D, U> criar() {
		return new MapaLista<D, U>();
	}
	
	@Override
	public Iterador<CaixaMapa<C, V>> fornecerIterador() {
		//TODO
		return null;
	}
	
	@Override
	public Iterator<CaixaMapa<C, V>> iterator() {
		return fornecerIterador();
	}
	
	@Override
	public Integer fornecerQuantidade() {
		return quantidadeDeElementos;
	}
	
	@Override
	public Boolean vazio() {
		return (quantidadeDeElementos == 0);
	}
	
	@Override
	public Boolean contem(C chave) {
		checarChave(chave);
		return obterGrupo(chave).contem(new Caixa<C, V>(chave));
	}
	
	@Override
	public Boolean remover(C chave) {
		checarChave(chave);
		Boolean removido = obterGrupo(chave).remover(new Caixa<C, V>(chave));
		if (removido) {
			quantidadeDeElementos--;
		}
		return removido;
	}
	
	@Override
	public void limpar() {
		//TODO
	}
	
	@Override
	public void inserir(C chave, V valor) {
		checarChaveEValor(chave, valor);
		ListaEncadeada<Caixa<C, V>> grupo = obterGrupo(chave);
		Caixa<C, V> elemento = new Caixa<C, V>(chave, valor);
		Boolean existia = grupo.remover(elemento);
		grupo.inserirNoInicio(elemento);
		if (!existia) {
			quantidadeDeElementos++;
			crescerSeNecessario();
		}
	}
	
	@Override
	public void inserir(Mapa<C, V> elementos) {
		//TODO
	}
	
	@Override
	public V fornecer(C chave) {
		checarChave(chave);
		ListaEncadeada<Caixa<C, V>> grupo = obterGrupo(chave);
		Caixa<C, V> elemento = grupo.reter(new Caixa<C, V>(chave));
		if (elemento != null) {
			return elemento.valor;
		}
		return null;
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
	
	private ListaEncadeada<Caixa<C, V>> obterGrupo(C chave) {
		Integer posicaoDoGrupo = (chave.fornecerCodigo() % elementos.fornecerTamanho());
		ListaEncadeada<Caixa<C, V>> grupo = elementos.fornecerDaPosicao(posicaoDoGrupo);
		if (grupo == null) {
			grupo = ListaEncadeada.criar();
			elementos.inserirNaPosicao(posicaoDoGrupo, grupo);
		}
		return grupo;
	}
	
	private void crescerSeNecessario() {
		if ((quantidadeDeElementos / elementos.fornecerTamanho()) >= FATOR_DE_CARGA) {
			ListaPosicional<ListaEncadeada<Caixa<C, V>>> elementosAntigos = elementos;
			elementos = ListaPosicional.criar(quantidadeDeElementos * FATOR_DE_CRESCIMENTO);
			quantidadeDeElementos = 0;
			for (ListaEncadeada<Caixa<C, V>> grupo : elementosAntigos) {
				for (Caixa<C, V> caixa : grupo) {
					inserir(caixa.chave, caixa.valor);
				}
			}
		}
	}
	
	private final class Caixa<C extends Codificavel<C>, V extends Igualavel<V>> implements CaixaMapa<C, V>, Igualavel<Caixa<C, V>> {
		private C chave;
		private V valor;
		
		private Caixa(C chave, V valor) {
			this.chave = chave;
			this.valor = valor;
		}
		
		private Caixa(C chave) {
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
		public Boolean igual(Caixa<C, V> outra) {
			return chave.igual(outra.chave);
		}
	}
}
