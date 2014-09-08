package br.dominioL.estruturados.grafo.utilidades;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public class Descritor implements Igualavel<Descritor>, Codificavel {

	private static final Texto SIMBOLO_VERTICE = Texto.criar("v");
	private static final Texto SIMBOLO_ARESTA = Texto.criar("a");

	private static Numero VERTICES = Numero.um();
	private static Numero ARESTAS = Numero.um();

	private Texto descritor;

	public Descritor(Texto descritor) {
		this.descritor = descritor;
	}

	public static Descritor deVertice() {
		VERTICES = VERTICES.incrementar();
		return new Descritor(SIMBOLO_VERTICE.concatenar(VERTICES));
	}

	public static Descritor deAresta() {
		ARESTAS = ARESTAS.incrementar();
		return new Descritor(SIMBOLO_ARESTA.concatenar(SIMBOLO_ARESTA));
	}

	public Texto fornecerTexto() {
		return descritor;
	}

	public static void reiniciarContagem() {
		VERTICES = Numero.um();
		ARESTAS = Numero.um();
	}

	@Override
	public Booleano igual(Descritor outro) {
		return Booleano.iguais(this.descritor, outro.descritor);
	}

	@Override
	public Numero codificar() {
		Numero primo = Numero.criar(31);
		Numero resultado = Numero.um();
		Numero valorDoDescritor = Numero.zero();
		if (Booleano.nulo(descritor).negar().avaliar()) {
			valorDoDescritor = Numero.criar(descritor.hashCode());
		}
		resultado = primo.multiplicar(resultado).somar(valorDoDescritor);
		return resultado;
	}
}
