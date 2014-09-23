package br.dominioL.estruturados.testes.figuracao;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.primitivos.Numero;

public class Codigo implements Codificavel {

	private Numero retorno;

	public Codigo(Numero retorno) {
		this.retorno = retorno;
	}

	@Override
	public Numero codificar() {
		return retorno;
	}

}
