package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.extra.ConstrutorDeCodigo;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;

abstract class Componente<T> implements Codificavel {

	private T descritor;
	private Numero peso;
	private Cor cor;
	private Booleano marcacao;

	Componente(T descritor) {
		this.descritor = descritor;
		peso = Numero.zero();
		cor = Cor.TRANSPARENTE;
		marcacao = Booleano.falso();
	}

	@Override
	public Numero codificar() {
		ConstrutorDeCodigo construtor = new ConstrutorDeCodigo();
		construtor.comAtributo(cor);
		construtor.comAtributo(descritor);
		construtor.comAtributo(marcacao);
		construtor.comAtributo(peso);
		return construtor.codificar();
	}

	public final T fornecerDescritor() {
		return descritor;
	}

	public final void fixarPeso(Numero peso) {
		this.peso = peso;
	}

	public final Booleano mesmoPesoQue(Componente<T> componente) {
		return peso.igual(componente.peso);
	}

	public final Booleano maisPesadoQue(Componente<T> componente) {
		return peso.maiorQue(componente.peso);
	}

	public final Booleano menosPesadoQue(Componente<T> componente) {
		return peso.menorQue(componente.peso);
	}

	public final void colorir(Cor cor) {
		this.cor = cor;
	}

	public final Booleano coloridoCom(Cor cor) {
		return this.cor.igual(cor);
	}

	public final void marcar() {
		this.marcacao = Booleano.verdadeiro();
	}

	public final void desmarcar() {
		this.marcacao = Booleano.falso();
	}

	public final Booleano marcado() {
		return marcacao;
	}

}
