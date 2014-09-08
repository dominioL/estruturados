package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;

public final class Cpf implements Igualavel<Cpf>, Codificavel {

	private final Integer cpf;

	public Cpf(Integer cpf) {
		this.cpf = cpf;
	}

	@Override
	public Booleano igual(Cpf outro) {
		return Booleano.criar(cpf == outro.cpf);
	}

	@Override
	public Numero codificar() {
		return Numero.criar(cpf % 10);
	}

}
