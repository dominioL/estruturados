package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Codificavel;

public final class Cpf implements Codificavel<Cpf> {
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

