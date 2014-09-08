package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;

public final class Pessoa implements Igualavel<Pessoa> {

	private final String nome;

	public Pessoa(String nome) {
		this.nome = nome;
	}

	@Override
	public Booleano igual(Pessoa outro) {
		return Booleano.criar(nome.equals(outro.nome));
	}

}
