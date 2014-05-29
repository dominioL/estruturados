package br.dominioL.estruturados.grafo;

import br.dominioL.estruturados.elemento.Codificavel;

abstract class Componente<T> implements Codificavel {
	private T descritor;
	private Peso peso;
	private Cor cor;
	private Boolean marcacao;

	Componente(T descritor) {
		this.descritor = descritor;
		peso = Peso.zero();
		cor = Cor.TRANSPARENTE;
		marcacao = false;
	}

	@Override
	public Integer codificar() {
		Integer primo = 31;
		Integer codigo = 1;
		codigo = primo * codigo + ((cor == null) ? 0 : cor.hashCode());
		if (descritor != null) {
			codigo = primo * codigo;
		} else if (descritor instanceof Codificavel) {
			codigo = primo * codigo + ((Codificavel) descritor).codificar();
		} else {
			codigo = primo * codigo + descritor.hashCode();
		}
		codigo = primo * codigo + ((marcacao == null) ? 0 : marcacao.hashCode());
		codigo = primo * codigo + ((peso == null) ? 0 : peso.codificar());
		return codigo;
	}

	public final T fornecerDescritor() {
		return descritor;
	}

	public final void fixarPeso(Peso peso) {
		this.peso = peso;
	}

	public final Boolean mesmoPesoQue(Componente<T> componente) {
		return peso.igual(componente.peso);
	}

	public final Boolean maisPesadoQue(Componente<T> componente) {
		return peso.maiorQue(componente.peso);
	}

	public final Boolean menosPesadoQue(Componente<T> componente) {
		return peso.menorQue(componente.peso);
	}

	public final void colorir(Cor cor) {
		this.cor = cor;
	}

	public final Boolean coloridoCom(Cor cor) {
		return this.cor.igual(cor);
	}

	public final void marcar() {
		this.marcacao = true;
	}

	public final void desmarcar() {
		this.marcacao = false;
	}

	public final Boolean marcado() {
		return marcacao;
	}
}
