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
	};

}
