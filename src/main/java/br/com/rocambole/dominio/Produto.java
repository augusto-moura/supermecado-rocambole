package br.com.rocambole.dominio;

/**
 * Registro de um produto. 
 */
public class Produto {

	/**
	 * Código único identificador do Produto
	 */
	private final String codigo;

	/**
	 * Preço da unidade do produto
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((precoUnitario == null) ? 0 : precoUnitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (precoUnitario == null) {
			if (other.precoUnitario != null)
				return false;
		} else if (!precoUnitario.equals(other.precoUnitario))
			return false;
		return true;
	}

}
