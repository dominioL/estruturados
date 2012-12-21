package br.opp.estruturados.testes.figuracao;

import br.opp.estruturados.elemento.Igualavel;

public final class Pessoa implements Igualavel<Pessoa> {
	private final String nome;
	
	public Pessoa(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean igual(Pessoa outro) {
		return nome.equals(outro.nome);
	}
}

