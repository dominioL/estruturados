package br.dominioL.estruturados.testes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import br.dominioL.estruturados.elemento.primitivos.Numero;

public class TesteNumero {

	private Numero inteiroZero;
	private Numero inteiroDez;
	private Numero realZero;
	private Numero realDezPontoSeis;
	private Numero decimaZero;
	private Numero decimalDezPontoSeicentosEDesoito;

	@Before
	public void criarFigurantes() {
		inteiroZero = Numero.criar(0);
		inteiroDez = Numero.criar(10);
		realZero = Numero.criar(0.0);
		realDezPontoSeis = Numero.criar(10.6);
		decimaZero = Numero.criar("0");
		decimalDezPontoSeicentosEDesoito = Numero.criar("10.618");
	}

	@Test
	public void numeroZero() {
		assertThat(inteiroZero.inteiro(), is(equalTo(new Integer(0))));
		assertThat(inteiroZero.real(), is(equalTo(new Double(0.0))));
		assertThat(inteiroZero.monetario(), is(equalTo("0,00")));
		assertThat(realZero.inteiro(), is(equalTo(new Integer(0))));
		assertThat(realZero.real(), is(equalTo(new Double(0.0))));
		assertThat(realZero.monetario(), is(equalTo("0,00")));
		assertThat(decimaZero.inteiro(), is(equalTo(new Integer(0))));
		assertThat(decimaZero.real(), is(equalTo(new Double(0.0))));
		assertThat(decimaZero.monetario(), is(equalTo("0,00")));
	}

	@Test
	public void numeroPositivo() {
		assertThat(inteiroDez.inteiro(), is(equalTo(new Integer(10))));
		assertThat(inteiroDez.real(), is(equalTo(new Double(10.0))));
		assertThat(inteiroDez.monetario(), is(equalTo("10,00")));
		assertThat(realDezPontoSeis.inteiro(), is(equalTo(new Integer(10))));
		assertThat(realDezPontoSeis.real(), is(equalTo(new Double(10.6))));
		assertThat(realDezPontoSeis.monetario(), is(equalTo("10,60")));
		assertThat(decimalDezPontoSeicentosEDesoito.inteiro(), is(equalTo(new Integer(10))));
		assertThat(decimalDezPontoSeicentosEDesoito.real(), is(equalTo(new Double(10.618))));
		assertThat(decimalDezPontoSeicentosEDesoito.monetario(), is(equalTo("10,62")));
	}

}
