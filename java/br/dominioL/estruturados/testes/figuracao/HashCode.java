package br.dominioL.estruturados.testes.figuracao;

public class HashCode {

	private int retorno;

	public HashCode(int retorno) {
		this.retorno = retorno;
	}

	@Override
	public int hashCode() {
		return retorno;
	}

}
