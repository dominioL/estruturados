package br.dominioL.estruturados.testes;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.excecoes.ExcecaoDeChaveNula;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.testes.figuracao.Cpf;
import br.dominioL.estruturados.testes.figuracao.Pessoa;

public final class TesteMapaLista {
	private Pessoa joao;
	private Pessoa jose;
	private Pessoa maria;
	private Pessoa pessoaNula;
	private Cpf cpfDoJoao;
	private Cpf cpfDoJose;
	private Cpf cpfDaMaria;
	private Cpf cpfNulo;
	private MapaLista<Cpf, Pessoa> mapa;

	@Before
	public void inciar() {
		mapa = MapaLista.criar();
		joao = new Pessoa("João");
		jose = new Pessoa("José");
		maria = new Pessoa("Maria");
		pessoaNula = null;
		cpfDoJoao = new Cpf(1);
		cpfDoJose = new Cpf(11);
		cpfDaMaria = new Cpf(5);
		cpfNulo = null;
	}

	@Test
	public void quantidadeDeElementosInicialEZero() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		assertThat(mapa.contarElementos(), is(0));
	}

	@Test
	public void estaVazioInicialmente() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		assertTrue(mapa.vazio());
	}

	@Test
	public void inserirAumentaAQuantidadeDeElementos() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		assertThat(mapa.contarElementos(), is(1));
		assertFalse(mapa.vazio());
	}

	@Test(expected = ExcecaoDeChaveNula.class)
	public void inserirChaveNulaLancaUmaExcecao() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfNulo, joao);
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void inserirElementoNuloLancaUmaExcecao() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, pessoaNula);
	}

	@Test(expected = ExcecaoDeChaveNula.class)
	public void fornecerElementoDeChaveNulaLancaUmaExcecao() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.fornecer(cpfNulo);
	}

	@Test
	public void fonrecerElementoNaoInseridoRetornaNull() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		assertSame(mapa.fornecer(cpfDoJoao), null);
		assertThat(mapa.contarElementos(), is(0));
	}

	@Test
	public void fornecerElementoInseridoRetornaOElemento() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		assertSame(mapa.fornecer(cpfDoJoao), joao);
		assertThat(mapa.contarElementos(), is(1));
	}

	@Test
	public void inserirDoisElementosComChavesIgualsMantemApenasOUltimoInserido() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDoJoao, jose);
		assertSame(mapa.fornecer(cpfDoJoao), jose);
		assertThat(mapa.contarElementos(), is(1));
	}

	@Test
	public void inserirDoisElementosComChavesColidiveisMantemOsDoisElementos() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDoJose, jose);
		assertSame(mapa.fornecer(cpfDoJoao), joao);
		assertSame(mapa.fornecer(cpfDoJose), jose);
		assertThat(mapa.contarElementos(), is(2));
	}

	@Test(expected = ExcecaoDeChaveNula.class)
	public void removerElementoComChaveNulaLancaUmaExcecao() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.remover(cpfNulo);
	}

	@Test
	public void removerElementoInexistenteRetornaFalse() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		assertFalse(mapa.remover(cpfDaMaria));
		assertThat(mapa.contarElementos(), is(1));
	}

	@Test
	public void removerElementoExistenteRetornaTrue() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDaMaria, maria);
		assertTrue(mapa.remover(cpfDaMaria));
		assertThat(mapa.contarElementos(), is(1));
	}

	@Test(expected = ExcecaoDeChaveNula.class)
	public void contemLancaUmaExcecaoSeChaveForNula() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.contem(cpfNulo);
	}

	@Test
	public void contemRetornaFalsoSeNaoExisteUmaChaveIgualEComMesmoCodigoDaChaveFornecida() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		assertFalse(mapa.contem(cpfDoJose));
	}

	@Test
	public void contemRetornaVerdadeiroSeExisteUmaChaveIgualEComMesmoCodigoDaChaveFornecida() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		assertTrue(mapa.contem(cpfDoJoao));
	}

	@Test
	public void fixarValorNulo() {
		assertNull(mapa.fornecer(cpfDoJoao));
		mapa.fixarValorNulo(jose);
		assertNotNull(mapa.fornecer(cpfDoJoao));
		assertTrue(jose.igual(mapa.fornecer(cpfDoJoao)));
	}

	@Test
	public void fornecerChavesDeMapaVazio() {
		assertThat(mapa.chaves().contarElementos(), is(equalTo(0)));
	}

	@Test
	public void fornecerChavesDeMapaComUmElemento() {
		mapa.inserir(cpfDaMaria, maria);
		assertThat(mapa.chaves().contarElementos(), is(equalTo(1)));
		assertTrue(cpfDaMaria.igual(mapa.chaves().fornecerDoInicio()));
	}

	@Test
	public void fornecerChavesDeMapaComUmElementoSobrescrito() {
		mapa.inserir(cpfDaMaria, maria);
		mapa.inserir(cpfDaMaria, joao);
		assertThat(mapa.chaves().contarElementos(), is(equalTo(1)));
		assertTrue(cpfDaMaria.igual(mapa.chaves().fornecerDoInicio()));
	}

	@Test
	public void fornecerChavesDeMapaComUmElementoRemovido() {
		mapa.inserir(cpfDaMaria, maria);
		mapa.remover(cpfDaMaria);
		assertThat(mapa.chaves().contarElementos(), is(equalTo(0)));
	}

	@Test
	public void fornecerValoresDeMapaVazio() {
		assertThat(mapa.valores().contarElementos(), is(equalTo(0)));
	}

	@Test
	public void fornecerValoresDeMapaComUmElemento() {
		mapa.inserir(cpfDaMaria, maria);
		assertThat(mapa.valores().contarElementos(), is(equalTo(1)));
		assertTrue(maria.igual(mapa.valores().fornecerDoInicio()));
	}

	@Test
	public void fornecerValoresDeMapaComUmElementoSobrescrito() {
		mapa.inserir(cpfDaMaria, maria);
		mapa.inserir(cpfDaMaria, joao);
		assertThat(mapa.valores().contarElementos(), is(equalTo(1)));
		assertTrue(joao.igual(mapa.valores().fornecerDoInicio()));
	}

	@Test
	public void fornecerValoresDeMapaComUmElementoRemovido() {
		mapa.inserir(cpfDaMaria, maria);
		mapa.remover(cpfDaMaria);
		assertThat(mapa.valores().contarElementos(), is(equalTo(0)));
	}

	@Test
	public void fornecerValoresDeMapaComUmElementoEmDuasChaves() {
		mapa.inserir(cpfDaMaria, maria);
		mapa.inserir(cpfDoJoao, maria);
		assertThat(mapa.valores().contarElementos(), is(equalTo(2)));
		assertTrue(mapa.valores().contem(maria));
	}

	@Test
	public void fornecerValoresDeMapaComDoisElementos() {
		mapa.inserir(cpfDaMaria, maria);
		mapa.inserir(cpfDoJoao, joao);
		assertThat(mapa.valores().contarElementos(), is(equalTo(2)));
		assertTrue(mapa.valores().contem(joao));
		assertTrue(mapa.valores().contem(maria));
	}
}
