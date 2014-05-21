package br.dominioL.estruturados.json;

import java.math.BigDecimal;

public final class ConstrutorJsonDeObjeto extends ConstrutorJson<ObjetoJson> {
	private ObjetoJson objeto;

	protected ConstrutorJsonDeObjeto() {
		objeto = Json.criarObjeto();
	}

	public ObjetoJson construir() {
		return objeto;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, ValorJson valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(IdentificadorJson identificador, ValorJson valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, String valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(IdentificadorJson identificador, String valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, BigDecimal valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(IdentificadorJson identificador, BigDecimal valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, Integer valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(IdentificadorJson identificador, Integer valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, Double valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(IdentificadorJson identificador, Double valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, Boolean valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(IdentificadorJson identificador, Boolean valor) {
		objeto.inserir(identificador, valor);
		return this;
	}
}