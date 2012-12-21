package br.dominioL.estruturados.elemento;

public interface Comparavel<T> extends Igualavel<T> {
	public Boolean maiorque(T outro);
	
	public Boolean menorQue(T outro);
}
