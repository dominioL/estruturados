package br.dominioL.estruturados.testes;

import org.junit.Test;

import br.dominioL.estruturados.excecoes.ExcecaoDeChaveNula;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.testes.figuracao.Cpf;
import br.dominioL.estruturados.testes.figuracao.Pessoa;
import static org.hamcrest.core.Is.*;

import static org.junit.Assert.*;

public final class TesteMapaLista {
	private Pessoa joao = new Pessoa("João");
	private Pessoa jose = new Pessoa("José");
	private Pessoa maria = new Pessoa("Maria");
	private Pessoa pessoaNula = null;
	private Cpf cpfDoJoao = new Cpf(1);
	private Cpf cpfDoJose = new Cpf(11);
	private Cpf cpfDaMaria = new Cpf(5);
	private Cpf cpfNulo = null;

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
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		assertNull(mapa.fornecer(cpfDoJoao));
		mapa.fixarValorNulo(jose);
		assertNotNull(mapa.fornecer(cpfDoJoao));
		assertTrue(jose.igual(mapa.fornecer(cpfDoJoao)));
	}
}
