package br.dominioL.estruturados.testes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import br.dominioL.estruturados.elemento.extra.ConstrutorDeTexto;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public class TesteConstrutorDeTexto {

	private ConstrutorDeTexto construtor;

	@Before
	public void criarFigurantes() {
		construtor = new ConstrutorDeTexto();
	}

	@Test
	public void vazio() {
		assertThat(construtor.construir(), is(equalTo(Texto.criar())));
	}

	@Test
	public void anexar() {
		construtor.anexar("Lucas ");
		construtor.anexar(Texto.criar("Pereira"));
		assertThat(construtor.construir(), is(equalTo(Texto.criar("Lucas Pereira"))));
	}

}
