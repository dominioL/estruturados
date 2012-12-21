package br.opp.estruturados.elemento;

public interface Comparavel<T> extends Igualavel<T> {
	public boolean maiorque(T outro);
	
	public boolean menorQue(T outro);
}

