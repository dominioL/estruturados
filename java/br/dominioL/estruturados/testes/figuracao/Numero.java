package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Igualavel;

public final class Numero implements Igualavel<Numero> {
	private final Integer valor;

	public Numero(Integer numero) {
		this.valor = numero;
	}

	@Override
	public Boolean igual(Numero outro) {
		return (this.valor == outro.valor);
	}
}

