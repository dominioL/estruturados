package br.dominioL.estruturados.elemento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;

public interface Igualavel<T> {

	public Booleano igual(T outro);

}
