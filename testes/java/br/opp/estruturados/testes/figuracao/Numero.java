package br.opp.estruturados.testes.figuracao;

import br.opp.estruturados.elemento.Igualavel;

public final class Numero implements Igualavel<Numero> {
	private final int valor;
	
	public Numero(int numero) {
		this.valor = numero;
	}
	
	@Override
	public boolean igual(Numero outro) {
		return (this.valor == outro.valor);
	}
}

