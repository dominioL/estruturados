package br.dominioL.estruturados;

import br.dominioL.estruturados.elemento.Igualavel;

public interface Posicionavel<E extends Igualavel<E>> {
	public void inserirNaPosicao(Integer posicao, E elemento);
	
	public E removerDaPosicao(Integer posicao);
	
	public E fornecerDaPosicao(Integer posicao);
}
