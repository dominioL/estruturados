package br.opp.estruturados.testes;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import br.opp.estruturados.excecoes.ExcecaoDeChaveNula;
import br.opp.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.opp.estruturados.mapa.MapaLista;
import br.opp.estruturados.testes.figuracao.Cpf;
import br.opp.estruturados.testes.figuracao.Pessoa;

public class TesteMapaLista {
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
		assertThat(mapa.fornecerQuantidade(), is(0));
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
		assertThat(mapa.fornecerQuantidade(), is(1));
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
		assertThat(mapa.fornecerQuantidade(), is(0));
	}
	
	@Test
	public void fornecerElementoInseridoRetornaOElemento() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		assertSame(mapa.fornecer(cpfDoJoao), joao);
		assertThat(mapa.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void inserirDoisElementosComChavesIgualsMantemApenasOUltimoInserido() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDoJoao, jose);
		assertSame(mapa.fornecer(cpfDoJoao), jose);
		assertThat(mapa.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void inserirDoisElementosComChavesColidiveisMantemOsDoisElementos() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDoJose, jose);
		assertSame(mapa.fornecer(cpfDoJoao), joao);
		assertSame(mapa.fornecer(cpfDoJose), jose);
		assertThat(mapa.fornecerQuantidade(), is(2));
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
		assertThat(mapa.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void removerElementoExistenteRetornaTrue() {
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDaMaria, maria);
		assertTrue(mapa.remover(cpfDaMaria));
		assertThat(mapa.fornecerQuantidade(), is(1));
	}
	
	@Test
	public void limparRemoveTodosOsElementos() {
		//TODO
		/*
		MapaLista<Cpf, Pessoa> mapa = MapaLista.criar();
		mapa.inserir(cpfDoJoao, joao);
		mapa.inserir(cpfDoJose, jose);
		mapa.inserir(cpfDaMaria, maria);
		mapa.limpar();
		assertSame(mapa.fornecer(cpfDoJoao), null);
		assertSame(mapa.fornecer(cpfDoJose), null);
		assertSame(mapa.fornecer(cpfDaMaria), null);
		assertThat(mapa.fornecerQuantidade(), is(0));
		*/
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
}

