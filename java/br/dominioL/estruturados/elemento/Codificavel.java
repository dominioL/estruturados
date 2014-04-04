package br.dominioL.estruturados.elemento;

public interface Codificavel<T> extends Igualavel<T> {
	public Integer fornecerCodigo(); 
}
