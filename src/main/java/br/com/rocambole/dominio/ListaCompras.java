package br.com.rocambole.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Contém as compras em lista e faz os cálculos de preços, com as informações
 * injetadas de estoque e promoções ativas.
 */
public class ListaCompras {

	/**
	 * Compras no "carrinho".
	 */
	private List<Produto> listaCompras = new ArrayList<>();

	/**
	 * Promoções ativas que possam valer para as compras no "carrinho"
	 */
	private final List<Promocao> listaPromocoes;

	/**
	 * Estoque de todos os Produtos possiveis que possam ser inseridos na lista.
	 */
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

	/**
	 * Encontra no estoque um produto com o código passado por argumento.
	 * 
	 * @param codigo
	 *            Codigo do produto a ser procurado.
	 * @return Produto do estoque.
	 */
	private Produto encontrarNoEstoquePorCodigo(final String codigo) {
		final Produto resultado = estoque.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().get();

		if (resultado == null) {
			throw new IllegalArgumentException("Não existe nenhum produto no estoque com o código: " + codigo);
		}

		return resultado;
	}

	/**
	 * Adiciona um produto presente no estoque que tenha o mesmo codigo.
	 * 
	 * @param codigo
	 *            Codigo do produto a ser procurado no estoque e inserido.
	 */
	public void add(final String codigo) {
		listaCompras.add(encontrarNoEstoquePorCodigo(codigo));
	}

	/**
	 * Remove o primeiro caso que encontrar com o codigo passado por argumento.
	 * 
	 * @param codigo
	 *            Codigo do produto a ser procurado na lista de compras e
	 *            removido.
	 */
	public void remove(final String codigo) {
		final Iterator<Produto> iterador = listaCompras.iterator();
		Produto ponteiro = iterador.next();

		while (iterador.hasNext()) {
			if (ponteiro.getCodigo().equals(codigo)) {
				iterador.remove();
				break;
			}
			ponteiro = iterador.next();
		}
	}

	/**
	 * Calcula o preço total a se pagar pelas compras com descontos
	 * promocionais.
	 * 
	 * @return Total a se pagar.
	 */
	public Double getTotalPrice() {
		final Double totalSemDescontos = listaCompras.stream().mapToDouble(Produto::getPrecoUnitario).sum();
		final Double somaDescontos = getTotalDiscount();

		return totalSemDescontos - somaDescontos;
	}

	/**
	 * Retorna o máximo de desconto possível com as compras na lista atual.
	 * 
	 * @return Total de descontos.
	 */
	public Double getTotalDiscount() {
		final Double soma = listaPromocoes.stream().mapToDouble(p -> p.descontoPromocional(listaCompras)).sum();
		return soma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((listaCompras == null) ? 0 : listaCompras.hashCode());
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
		if (listaCompras == null) {
			if (other.listaCompras != null)
				return false;
		} else if (!listaCompras.equals(other.listaCompras))
			return false;
		if (listaPromocoes == null) {
			if (other.listaPromocoes != null)
				return false;
		} else if (!listaPromocoes.equals(other.listaPromocoes))
			return false;
		return true;
	};

}
