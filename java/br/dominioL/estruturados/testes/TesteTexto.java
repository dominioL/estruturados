package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public class TesteTexto {

	private Texto vazio;

	@Before
	public void prepararCenario() {
		vazio = Texto.criar();
	}

	@Test
	public void criarVazio() {
		assertThat(vazio, is(equalTo(Texto.criar(""))));
		assertTrue(vazio.igual(Texto.criar("")).avaliar());
		assertThat(vazio.valor(), is(equalTo("")));
	}

}
