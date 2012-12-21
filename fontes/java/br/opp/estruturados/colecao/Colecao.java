package br.opp.estruturados.colecao;

import br.opp.estruturados.elemento.Igualavel;
import br.opp.estruturados.iteracao.Iteravel;

public interface Colecao<E extends Igualavel<E>> extends Iteravel<E> {
	public int fornecerQuantidade();
	
	public boolean vazio();
	
	public boolean contem(E elemento);
	
	public boolean remover(E elemento);
	
	public void limpar();
	
	public void inserir(Iteravel<E> elementos);
	
	public void inserir(E elemento);
}

