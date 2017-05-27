package br.com.rocambole.dominio;

import java.util.List;

/**
 * Promoções recebem uma lista de produtos e calculam o valor máximo de desconto que podem aplicar a esta lista.
 */
public interface Promocao {

	/**
	 * A promoção pode validar a compra da forma que achar necessário.
	 * O valor de desconto deve ser abatido do valor final.
	 * 
	 * @param alvo
	 *            Produto em que a Promocao será aplicada.
	 * @return Preço máximo de desconto possível para a lista de produtos.
	 */
	public Double descontoPromocional(final List<Produto> alvo);

}
