package br.opp.estruturados.testes.figuracao;

import br.opp.estruturados.elemento.Codificavel;

public final class Cpf implements Codificavel<Cpf> {
	private final int cpf;
	
	public Cpf(int cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public boolean igual(Cpf outro) {
		return (cpf == outro.cpf);
	};
	
	@Override
	public int fornecerCodigo() {
		return (cpf % 10);
	}
}

