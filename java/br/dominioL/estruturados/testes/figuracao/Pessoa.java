package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Igualavel;

public final class Pessoa implements Igualavel<Pessoa> {
	private final String nome;

	public Pessoa(String nome) {
		this.nome = nome;
	}

	@Override
	public Boolean igual(Pessoa outro) {
		return nome.equals(outro.nome);
	}
}

