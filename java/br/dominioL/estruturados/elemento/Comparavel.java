package br.dominioL.estruturados.elemento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;

public interface Comparavel<T> extends Igualavel<T> {

	public Booleano maiorQue(T outro);

	public Booleano menorQue(T outro);

}
