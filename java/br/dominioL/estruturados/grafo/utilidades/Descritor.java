package br.dominioL.estruturados.grafo.utilidades;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Igualavel;

public class Descritor implements Igualavel<Descritor>, Codificavel {
	private static final String SIMBOLO_VERTICE = "v";
	private static final String SIMBOLO_ARESTA = "a";

	private static Integer VERTICES = 1;
	private static Integer ARESTAS = 1;

	private String descritor;

	public Descritor(String descritor) {
		this.descritor = descritor;
	}

	public static Descritor deVertice() {
		return new Descritor(SIMBOLO_VERTICE + VERTICES++);
	}

	public static Descritor deAresta() {
		return new Descritor(SIMBOLO_ARESTA + ARESTAS++);
	}

	public String fornecerTexto() {
		return descritor;
	}
	
	public static void reiniciarContagem() {
		VERTICES = 1;
		ARESTAS = 1;
	}

	@Override
	public Boolean igual(Descritor outro) {
		return (this.descritor.equals(outro.descritor));
	}

	@Override
	public Integer codificar() {
		Integer primo = 31;
		Integer resultado = 1;
		resultado = primo * resultado + ((descritor == null) ? 0 : descritor.hashCode());
		return resultado;
	}
}
