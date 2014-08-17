package br.dominioL.estruturados.testes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import br.dominioL.estruturados.elemento.Numero;

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
		assertThat(inteiroZero.valorInteiro(), is(equalTo(new Integer(0))));
		assertThat(inteiroZero.valorReal(), is(equalTo(new Double(0.0))));
		assertThat(inteiroZero.valorMonetario(), is(equalTo("0,00")));
		assertThat(realZero.valorInteiro(), is(equalTo(new Integer(0))));
		assertThat(realZero.valorReal(), is(equalTo(new Double(0.0))));
		assertThat(realZero.valorMonetario(), is(equalTo("0,00")));
		assertThat(decimaZero.valorInteiro(), is(equalTo(new Integer(0))));
		assertThat(decimaZero.valorReal(), is(equalTo(new Double(0.0))));
		assertThat(decimaZero.valorMonetario(), is(equalTo("0,00")));
	}

	@Test
	public void numeroPositivo() {
		assertThat(inteiroDez.valorInteiro(), is(equalTo(new Integer(10))));
		assertThat(inteiroDez.valorReal(), is(equalTo(new Double(10.0))));
		assertThat(inteiroDez.valorMonetario(), is(equalTo("10,00")));
		assertThat(realDezPontoSeis.valorInteiro(), is(equalTo(new Integer(10))));
		assertThat(realDezPontoSeis.valorReal(), is(equalTo(new Double(10.6))));
		assertThat(realDezPontoSeis.valorMonetario(), is(equalTo("10,60")));
		assertThat(decimalDezPontoSeicentosEDesoito.valorInteiro(), is(equalTo(new Integer(10))));
		assertThat(decimalDezPontoSeicentosEDesoito.valorReal(), is(equalTo(new Double(10.618))));
		assertThat(decimalDezPontoSeicentosEDesoito.valorMonetario(), is(equalTo("10,62")));
	}
}
