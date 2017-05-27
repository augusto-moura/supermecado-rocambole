package br.com.rocambole.dominio;

import java.util.List;

/**
 * Tipo de Promocao que ao alcançar certa quantia de produtos na lista de
 * compras, ganha-se um desconto na compra do conjunto.
 */
public class LeveMaisPagueMenos implements Promocao {

	/**
	 * Produto para o qual a promocao é válida
	 */
	private final Produto produtoAlvo;

	/**
	 * Quantidade mínima de produtos para que a promoção passe a valer.
	 */
	private final Long quantidadeMinima;

	/**
	 * Preço após desconto. Nota: Não se trata do desconto, mas sim do valor dos
	 * produtos em conjunto após o abatimento do desconto.
	 */
	private final Double novoPreco;

	public LeveMaisPagueMenos(Produto produtoAlvo, Long quantidadeMinima, Double novoPreco) {
		super();
		this.produtoAlvo = produtoAlvo;
		this.quantidadeMinima = quantidadeMinima;
		this.novoPreco = novoPreco;
	}

	/**
	 * Calcula com base na quantidade de produtos na promoção, a quantidade
	 * necessária para alcançar o desconto e o preço atual do produto.
	 */
	@Override
	public Double descontoPromocional(List<Produto> alvo) {
		final Long quantidadeProdutosNaLista = alvo.stream().filter(produtoAlvo::equals).count();

		if (quantidadeProdutosNaLista >= quantidadeMinima) {
			return ((quantidadeMinima * produtoAlvo.getPrecoUnitario()) - novoPreco)
					* Math.floor(quantidadeProdutosNaLista / quantidadeMinima);
		}

		return 0D;
	}

	public Produto getProdutoAlvo() {
		return produtoAlvo;
	}

	public Long getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public Double getNovoPreco() {
		return novoPreco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((novoPreco == null) ? 0 : novoPreco.hashCode());
		result = prime * result + ((produtoAlvo == null) ? 0 : produtoAlvo.hashCode());
		result = prime * result + ((quantidadeMinima == null) ? 0 : quantidadeMinima.hashCode());
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
		LeveMaisPagueMenos other = (LeveMaisPagueMenos) obj;
		if (novoPreco == null) {
			if (other.novoPreco != null)
				return false;
		} else if (!novoPreco.equals(other.novoPreco))
			return false;
		if (produtoAlvo == null) {
			if (other.produtoAlvo != null)
				return false;
		} else if (!produtoAlvo.equals(other.produtoAlvo))
			return false;
		if (quantidadeMinima == null) {
			if (other.quantidadeMinima != null)
				return false;
		} else if (!quantidadeMinima.equals(other.quantidadeMinima))
			return false;
		return true;
	}

}
