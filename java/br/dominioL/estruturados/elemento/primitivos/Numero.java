package br.dominioL.estruturados.elemento.primitivos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import br.dominioL.estruturados.elemento.Codificavel;
import br.dominioL.estruturados.elemento.Comparavel;
import br.dominioL.estruturados.elemento.Igualavel;

public class Numero implements Igualavel<Numero>, Comparavel<Numero>, Codificavel {

	private BigDecimal valor;

	private Numero(String valor) {
		this.valor = new BigDecimal(valor);
	}

	private Numero(BigDecimal valor) {
		this.valor = valor;
	}

	private Numero(Integer valor) {
		this.valor = new BigDecimal(valor);
	}

	private Numero(Double valor) {
		this.valor = new BigDecimal(valor);
	}

	public static Numero zero() {
		return new Numero(0);
	}

	public static Numero um() {
		return new Numero(1);
	}

	public static Numero criar(String valor) {
		return new Numero(valor);
	}

	public static Numero criar(BigDecimal valor) {
		return new Numero(valor);
	}

	public static Numero criar(Integer valor) {
		return new Numero(valor);
	}

	public static Numero criar(Double valor) {
		return new Numero(valor);
	}

	public Numero incrementar() {
		return new Numero(valor.add(BigDecimal.ONE));
	}

	public Numero decrementar() {
		return new Numero(valor.subtract(BigDecimal.ONE));
	}

	public Numero negativo() {
		return new Numero(valor.negate());
	}

	public Numero resto(Numero outro) {
		return Numero.criar(valor.remainder(outro.valor));
	}

	public Numero dividir(Numero outro) {
		return Numero.criar(valor.divide(outro.valor));
	}

	public Numero multiplicar(Numero outro) {
		return Numero.criar(valor.multiply(outro.valor));
	}

	public Numero somar(Numero outro) {
		return Numero.criar(valor.add(outro.valor));
	}

	public Integer inteiro() {
		return valor.intValue();
	}

	public Double real() {
		return valor.doubleValue();
	}

	public String monetario() {
		return new DecimalFormat("0.00").format(valor);
	}

	public Booleano igualZero() {
		return Booleano.criar(valor.equals(BigDecimal.ZERO));
	}

	public Booleano maiorOuIgualQueZero() {
		return Booleano.criar(valor.compareTo(BigDecimal.ZERO) >= 0);
	}

	public Booleano maiorQueZero() {
		return Booleano.criar(valor.compareTo(BigDecimal.ZERO) > 0);
	}

	public Booleano menorOuIgualQueZero() {
		return Booleano.criar(valor.compareTo(BigDecimal.ZERO) <= 0);
	}

	public Booleano menorQueZero() {
		return Booleano.criar(valor.compareTo(BigDecimal.ZERO) < 0);
	}

	public Booleano menorOuIgualQue(Numero outro) {
		return Booleano.criar(valor.compareTo(outro.valor) <= 0);
	}

	public Booleano maiorOuIgualQue(Numero outro) {
		return Booleano.criar(valor.compareTo(outro.valor) >= 0);
	}

	@Override
	public Booleano maiorQue(Numero outro) {
		return Booleano.criar(valor.compareTo(outro.valor) > 0);
	}

	@Override
	public Booleano menorQue(Numero outro) {
		return Booleano.criar(valor.compareTo(outro.valor) < 0);
	}

	public Texto comoTexto() {
		return Texto.criar(valor.toString());
	}

	@Override
	public Booleano igual(Numero outro) {
		return Booleano.criar(valor.compareTo(outro.valor) == 0);
	}

	@Override
	public Numero codificar() {
		return Numero.criar(valor.hashCode());
	}

	@Override
	public boolean equals(Object outro) {
		if (outro instanceof Numero) {
			return valor.equals(((Numero) outro).valor);
		}
		return false;
	}

	@Override
	public String toString() {
		return valor.toString();
	}

}
