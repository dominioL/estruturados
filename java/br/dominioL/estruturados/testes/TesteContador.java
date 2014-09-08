package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.elemento.extra.Contador;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
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
	public void contarDeTrasParaFrente() {
		fail();
	}

	@Test
	public void remover() {

	}

	@Test
	public void substituir() {

	}

}
