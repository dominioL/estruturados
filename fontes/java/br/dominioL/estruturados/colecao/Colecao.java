package br.dominioL.estruturados.colecao;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.iteracao.Iteravel;

public interface Colecao<E extends Igualavel<E>> extends Iteravel<E> {
	public Integer fornecerQuantidade();
	
	public Boolean vazio();
	
	public Boolean contem(E elemento);
	
	public Boolean remover(E elemento);
	
	public void limpar();
	
	public void inserir(Iteravel<E> elementos);
	
	public void inserir(E elemento);
}
