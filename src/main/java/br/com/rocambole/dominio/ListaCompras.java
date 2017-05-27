package br.com.rocambole.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaCompras {

	private List<Produto> lista = new ArrayList<>();

	private final List<Promocao> listaPromocoes;

	private final List<Produto> estoque;

	/**
	 * Como o sistema ainda não implementa nenhum serviço de armazenamento de
	 * dados, o estoque e a lista de promoções são injetadas.
	 * 
	 * @param listaPromocoes
	 *            Lista de promoções ativas que devem ser aplicadas no cálculo
	 *            de valor total.
	 * @param estoque
	 *            Lista com os produtos disponíveis a serem adicionados
	 */
	public ListaCompras(List<Promocao> listaPromocoes, List<Produto> estoque) {
		super();
		this.estoque = estoque == null ? Collections.emptyList() : estoque;
		this.listaPromocoes = listaPromocoes == null ? Collections.emptyList() : listaPromocoes;
	}

	private Produto encontrarNoEstoquePorCodigo(final String codigo) {
		final Produto resultado = estoque.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().get();

		if (resultado == null) {
			throw new IllegalArgumentException("Não existe nenhum produto no estoque com o código: " + codigo);
		}

		return resultado;
	}

	public void add(final String codigo) {
		throw new RuntimeException("Não implementado");
	}

	public Double getTotalPrice() {
		throw new RuntimeException("Não implementado");
	}

	public Double getTotalDiscount() {
		throw new RuntimeException("Não implementado");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((listaPromocoes == null) ? 0 : listaPromocoes.hashCode());
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
		ListaCompras other = (ListaCompras) obj;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (listaPromocoes == null) {
			if (other.listaPromocoes != null)
				return false;
		} else if (!listaPromocoes.equals(other.listaPromocoes))
			return false;
		return true;
	};

}
