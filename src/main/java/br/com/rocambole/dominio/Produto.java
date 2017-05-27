package br.com.rocambole.dominio;

public class Produto {

	private final String codigo;

	private final Double precoUnitario;

	public Produto(String codigo, Double precoUnitario) {
		this.codigo = codigo;
		this.precoUnitario = precoUnitario;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

}
