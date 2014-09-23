package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;

public enum Cor implements Igualavel<Cor> {

	TRANSPARENTE,
	BRANCO,
	CINZA,
	PRETO,
	AZUL,
	ROXO,
	VERDE,
	VERMELHO,
	ROSA,
	AMARELO;

	@Override
	public Booleano igual(Cor outro) {
		return Booleano.mesmo(this, outro);
	}

}
