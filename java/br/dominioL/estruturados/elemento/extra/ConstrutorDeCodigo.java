package br.dominioL.estruturados.elemento.extra;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;

public final class ConstrutorDeCodigo implements Codificavel {

	private static final Numero FATOR_PRIMO = Numero.criar(31);
	private static final Integer PADRAO_NULO = 0;

	private Numero codigo = Numero.um();

	public Numero codificar() {
		return codigo.resto(Numero.maiorInteiro());
	}

	public void comAtributo(Codificavel codificavel) {
		codigo = FATOR_PRIMO.multiplicar(codigo);
		if (Booleano.nulo(codificavel).negar().avaliar()) {
			Numero fragmentoDeCodigo = codificavel.codificar();
			if (Booleano.nulo(fragmentoDeCodigo).negar().avaliar()) {
				codigo = codigo.somar(codificavel.codificar().absoluto());
			}
		}
	}

	public void comAtributo(Object objeto) {
		Integer hashCode = Booleano.nulo(objeto).avaliar() ? PADRAO_NULO : objeto.hashCode();
		comAtributo(Numero.criar(hashCode));
	}

}
