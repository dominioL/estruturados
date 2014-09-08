package br.dominioL.estruturados.colecao;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.iteracao.Iteravel;

public interface Colecao<E extends Igualavel<E>> extends Iteravel<E> {

	public Numero contarElementos();

	public Booleano vazio();

	public Booleano contem(E elemento);

	public void inserir(E elemento);

	public void inserir(Iteravel<E> elementos);

	public Booleano remover(E elemento);

}
