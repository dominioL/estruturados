package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class ConstrutorJsonDeObjeto extends ConstrutorJson<ObjetoJson> {

	private ObjetoJson objeto;

	protected ConstrutorJsonDeObjeto() {
		objeto = Json.criarObjeto();
	}

	public ObjetoJson construir() {
		return objeto;
	}

	public ConstrutorJsonDeObjeto inserir(Texto identificador, ValorJson valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(Texto identificador, Texto valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(Texto identificador, Numero valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(Texto identificador, Booleano valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, ValorJson valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, Texto valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, Numero valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

	public ConstrutorJsonDeObjeto inserir(String identificador, Booleano valor) {
		objeto.inserir(identificador, valor);
		return this;
	}

}
