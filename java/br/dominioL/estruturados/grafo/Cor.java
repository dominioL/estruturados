package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Igualavel;

public enum Cor implements Igualavel<Cor> {
	TRANSPARENTE,
	BRANCO,
	CINZE,
	PRETO,
	AZUL,
	ROXO,
	VERDE,
	VERMELHO,
	ROSA,
	AMARELO;

	@Override
	public Boolean igual(Cor outro) {
		return (this == outro);
	}
}
