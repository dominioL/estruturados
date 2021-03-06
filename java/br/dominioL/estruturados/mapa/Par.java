package br.dominioL.estruturados.mapa;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;

public interface Par<C extends Igualavel<C> & Codificavel, V extends Igualavel<V>> {

	public C fornecerChave();

	public V fornecerValor();

}
