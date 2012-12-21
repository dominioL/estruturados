package br.opp.estruturados.elemento;

public interface Codificavel<T> extends Igualavel<T> {
	public int fornecerCodigo(); 
}

