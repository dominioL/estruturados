package br.opp.estruturados.mapa;

import br.opp.estruturados.elemento.Codificavel;
import br.opp.estruturados.elemento.Igualavel;
import br.opp.estruturados.iteracao.Iteravel;

public interface Mapa<C extends Codificavel<C>, V extends Igualavel<V>> extends Iteravel<V> {
	public int fornecerQuantidade();
	
	public boolean vazio();
	
	public boolean contem(C chave);
	
	public boolean remover(C chave);
	
	public void limpar();
	
	public void inserir(C chave, V valor);
	
	public void inserir(Mapa<C, V> elementos);
	
	public V fornecer(C chave);
}

