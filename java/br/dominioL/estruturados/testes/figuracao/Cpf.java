package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;

public final class Cpf implements Igualavel<Cpf>, Codificavel {
	private final Integer cpf;
	
	public Cpf(Integer cpf) {
		this.cpf = cpf;
	}

	@Override
	public Boolean igual(Cpf outro) {
		return (cpf == outro.cpf);
	};

	@Override
	public Integer fornecerCodigo() {
		return (cpf % 10);
	}
}
