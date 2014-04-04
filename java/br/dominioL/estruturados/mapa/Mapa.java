package br.dominioL.estruturados.mapa;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.iteracao.Iteravel;

public interface Mapa<C extends Codificavel<C>, V extends Igualavel<V>> extends Iteravel<Par<C, V>> {
	public Integer fornecerQuantidade();

	public Boolean vazio();

	public Boolean contem(C chave);

	public Boolean remover(C chave);

	public void inserir(C chave, V valor);

	public void inserir(Iteravel<Par<C, V>> elementos);

	public V fornecer(C chave);
}
