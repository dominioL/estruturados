package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Booleano;

public class TesteBooleano {

	private Booleano verdadeiro;
	private Booleano falso;

	@Before
	public void criarFigurantes() {
		verdadeiro = Booleano.verdadeiro();
		falso = Booleano.falso();
	}

	@Test
	public void igualdadeDoJava() {
		assertThat(verdadeiro, is(equalTo(Booleano.verdadeiro())));
		assertThat(verdadeiro, is(not(equalTo(Booleano.falso()))));
		assertThat(verdadeiro, is(not(equalTo(null))));
		assertThat(falso, is(not(equalTo(Booleano.verdadeiro()))));
		assertThat(falso, is(equalTo(Booleano.falso())));
		assertThat(falso, is(not(equalTo(null))));
	}

	@Test
	public void igualdadeDoEstruturados() {
		assertTrue(verdadeiro.igual(Booleano.verdadeiro()).avaliar());
		assertFalse(verdadeiro.igual(Booleano.falso()).avaliar());
		assertFalse(falso.igual(Booleano.verdadeiro()).avaliar());
		assertTrue(falso.igual(Booleano.falso()).avaliar());
		assertFalse(verdadeiro.igual(null).avaliar());
		assertFalse(falso.igual(null).avaliar());
	}

	@Test
	public void e() {
		assertThat(verdadeiro.e(falso), is(equalTo(Booleano.falso())));
		assertThat(verdadeiro, is(equalTo(Booleano.verdadeiro())));
		assertThat(verdadeiro.e(verdadeiro), is(equalTo(Booleano.verdadeiro())));
		assertThat(verdadeiro, is(equalTo(Booleano.verdadeiro())));
	}

	@Test
	public void ou() {
		assertThat(falso.ou(verdadeiro), is(equalTo(Booleano.verdadeiro())));
		assertThat(falso, is(equalTo(Booleano.falso())));
		assertThat(falso.ou(falso), is(equalTo(Booleano.falso())));
		assertThat(falso, is(equalTo(Booleano.falso())));
	}
	
	@Test
	public void nao() {
		assertThat(verdadeiro.negar(), is(equalTo(Booleano.falso())));
		assertThat(verdadeiro, is(equalTo(Booleano.verdadeiro())));
		assertThat(falso.negar(), is(equalTo(Booleano.verdadeiro())));
		assertThat(falso, is(equalTo(Booleano.falso())));
	}

	@Test(expected = NullPointerException.class)
	public void eVerdadeiroComNulo() {
		verdadeiro.e(null);
	}

	@Test(expected = NullPointerException.class)
	public void eFalsoComNulo() {
		falso.e(null);
	}

	@Test(expected = NullPointerException.class)
	public void ouVerdadeiroComNulo() {
		verdadeiro.ou(null);
	}

	@Test(expected = NullPointerException.class)
	public void ouFalsoComNulo() {
		falso.ou(null);
	}

}
