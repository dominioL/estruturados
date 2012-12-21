package br.opp.estruturados;

import br.opp.estruturados.elemento.Igualavel;

public interface Posicionavel<E extends Igualavel<E>> {
	public void inserirNaPosicao(int posicao, E elemento);
	
	public E removerDaPosicao(int posicao);
	
	public E fornecerDaPosicao(int posicao);
}

