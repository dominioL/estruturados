package br.dominioL.estruturados.mapa;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.iteracao.Iteravel;

public interface Mapa<C extends Igualavel<C> & Codificavel, V extends Igualavel<V>> extends Iteravel<Par<C, V>> {

	public Numero contarElementos();

	public Booleano vazio();

	public Booleano contem(C chave);

	public Booleano remover(C chave);

	public void inserir(C chave, V valor);

	public void inserir(Iteravel<Par<C, V>> elementos);

	public V fornecer(C chave);

	public void fixarValorNulo(V valor);

	public ListaEncadeada<C> chaves();

	public ListaEncadeada<V> valores();

}
