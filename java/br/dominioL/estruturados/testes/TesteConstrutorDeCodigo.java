package br.dominioL.estruturados.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.extra.ConstrutorDeCodigo;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.testes.figuracao.Codigo;
import br.dominioL.estruturados.testes.figuracao.HashCode;

public class TesteConstrutorDeCodigo {

	private Codificavel comCodigoUm = new Codigo(Numero.um());
	private Codificavel comCodigoDois = new Codigo(Numero.criar(2));
	private Codificavel comCodigoUmNegativo = new Codigo(Numero.menosUm());
	private Codificavel comCodigoMaiorInteiro = new Codigo(Numero.maiorInteiro());
	private Codificavel comCodigoMaiorQueOMaiorInteiro = new Codigo(Numero.maiorInteiro().incrementar());
	private Codificavel comCodigoNulo = new Codigo(null);
	private Codificavel codigoNulo = null;
	private Object comHashCodeTres = new HashCode(3);
	private Object comHashCodeUmNegativo = new HashCode(-1);
	private Object hashCodeNulo = null;
	private ConstrutorDeCodigo construtor;

	@Before
	public void prepararCenario() {
		construtor = new ConstrutorDeCodigo();
	}

	@Test
	public void semNenhumAtributo() {
		assertEquals(Numero.um(), construtor.codificar());
	}

	@Test
	public void comAtributoComCodigoUm() {
		construtor.comAtributo(comCodigoUm);
		assertEquals(Numero.criar(32), construtor.codificar());
	}

	@Test
	public void comAtributoComCodigoMaiorInteiro() {
		construtor.comAtributo(comCodigoMaiorInteiro);
		assertEquals(Numero.criar(31), construtor.codificar());
	}

	@Test
	public void comAtributoComCodigoMaiorQueOMaiorInteiro() {
		construtor.comAtributo(comCodigoMaiorQueOMaiorInteiro);
		assertEquals(Numero.criar(32), construtor.codificar());
	}

	@Test
	public void comAtributoComCodigoUmEAtributoComCodigoDois() {
		construtor.comAtributo(comCodigoUm);
		construtor.comAtributo(comCodigoDois);
		assertEquals(Numero.criar(994), construtor.codificar());
	}

	@Test
	public void comAtributoComCodigoUmNegativo() {
		construtor.comAtributo(comCodigoUmNegativo);
		assertEquals(Numero.criar(32), construtor.codificar());
	}

	@Test
	public void comAtributoComCodigoNulo() {
		construtor.comAtributo(comCodigoNulo);
		assertEquals(Numero.criar(31), construtor.codificar());
	}

	@Test
	public void comAtributoComHashCodeTres() {
		assertEquals(3, Numero.criar(3).hashCode());
		construtor.comAtributo(comHashCodeTres);
		assertEquals(Numero.criar(34), construtor.codificar());
	}

	@Test
	public void comAtributoComHashDoisNegativo() {
		construtor.comAtributo(comHashCodeUmNegativo);
		assertEquals(Numero.criar(32), construtor.codificar());
	}

	@Test
	public void comCodigoNulo() {
		construtor.comAtributo(codigoNulo);
		assertEquals(Numero.criar(31), construtor.codificar());
	}

	@Test
	public void comHashCodeNulo() {
		construtor.comAtributo(hashCodeNulo);
		assertEquals(Numero.criar(31), construtor.codificar());
	}

}
