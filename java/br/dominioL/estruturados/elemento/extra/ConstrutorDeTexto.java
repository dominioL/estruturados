package br.dominioL.estruturados.elemento.extra;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class ConstrutorDeTexto {

	private StringBuilder construtor;

	public ConstrutorDeTexto() {
		construtor = new StringBuilder();
	}

	public Texto construir() {
		return Texto.criar(construtor.toString());
	}

	public void anexar(String anexo) {
		construtor.append(anexo);
	}

	public void anexar(Texto anexo) {
		construtor.append(anexo.valor());
	}

}
