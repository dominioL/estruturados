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
	private ListaPosicional<ListaEncadeada<Caixa>> elementos;
	
	private MapaLista() {
		quantidadeDeElementos = 0;
		elementos = ListaPosicional.criar(TAMANHO_INICIAL);
	}
	
	public static <D extends Codificavel<D>, U extends Igualavel<U>> MapaLista<D, U> criar() {
		return new MapaLista<D, U>();
	}
	
	@Override
	public Iterador<V> fornecerIterador() {
		//TODO
		return null;
	}
	
	@Override
	public Iterator<V> iterator() {
		//TODO
		return null;
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
		return obterGrupo(chave).contem(new Caixa(chave));
	}
	
	@Override
	public Boolean remover(C chave) {
		checarChave(chave);
		Boolean removido = obterGrupo(chave).remover(new Caixa(chave));
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
		ListaEncadeada<Caixa> grupo = obterGrupo(chave);
		Caixa elemento = new Caixa(chave, valor);
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
		ListaEncadeada<Caixa> grupo = obterGrupo(chave);
		Caixa elemento = grupo.reter(new Caixa(chave));
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
	
	private ListaEncadeada<Caixa> obterGrupo(C chave) {
		Integer posicaoDoGrupo = (chave.fornecerCodigo() % elementos.fornecerTamanho());
		ListaEncadeada<Caixa> grupo = elementos.fornecerDaPosicao(posicaoDoGrupo);
		if (grupo == null) {
			grupo = ListaEncadeada.criar();
			elementos.inserirNaPosicao(posicaoDoGrupo, grupo);
		}
		return grupo;
	}
	
	private void crescerSeNecessario() {
		if ((quantidadeDeElementos / elementos.fornecerTamanho()) >= FATOR_DE_CARGA) {
			ListaPosicional<ListaEncadeada<Caixa>> elementosAntigos = elementos;
			elementos = ListaPosicional.criar(quantidadeDeElementos * FATOR_DE_CRESCIMENTO);
			quantidadeDeElementos = 0;
			for (ListaEncadeada<Caixa> grupo : elementosAntigos) {
				for (Caixa caixa : grupo) {
					inserir(caixa.chave, caixa.valor);
				}
			}
		}
	}
	
	private final class Caixa implements Igualavel<Caixa> {
		public C chave;
		public V valor;
		
		private Caixa(C chave, V valor) {
			this.chave = chave;
			this.valor = valor;
		}
		
		private Caixa(C chave) {
			this.chave = chave;
			this.valor = null;
		}
		
		@Override
		public Boolean igual(Caixa outra) {
			return chave.igual(outra.chave);
		}
	}
}

