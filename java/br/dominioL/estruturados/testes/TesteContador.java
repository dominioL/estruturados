package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Contador;
import br.dominioL.estruturados.iteracao.Iterador;

public class TesteContador {

	private Numero zero = Numero.zero();
	private Numero um = Numero.um();
	private Numero dois = Numero.criar(2);
	private Contador contador;

	@Test
	public void contarAteUm() {
		contador = Contador.ate(Numero.um());
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(zero)));
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void contarAteUmEmUmContadorAteZero() {
		contador = Contador.ate(zero);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertFalse(iterador.possuiProximo().avaliar());
		iterador.iterarProximo();
	}

	@Test
	public void contarDeUmAteDois() {
		contador = Contador.de(um).ate(dois);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(um)));
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void contarDeUmAteZero() {
		contador = Contador.de(um).ate(zero);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(um)));
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void remover() {
		contador = Contador.de(zero).ate(um);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(zero)));
		iterador.remover();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void substituir() {
		contador = Contador.de(zero).ate(um);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(zero)));
		iterador.substituir(dois);
	}

	@Test
	public void contarAteComUm() {
		contador = Contador.ateCom(um);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(zero)));
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(um)));
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void contarDeUmAteComZero() {
		contador = Contador.de(um).ateCom(zero);
		Iterador<Numero> iterador = contador.fornecerIterador();
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(um)));
		assertTrue(iterador.possuiProximo().avaliar());
		assertThat(iterador.iterarProximo(), is(equalTo(zero)));
		assertFalse(iterador.possuiProximo().avaliar());
	}

}
