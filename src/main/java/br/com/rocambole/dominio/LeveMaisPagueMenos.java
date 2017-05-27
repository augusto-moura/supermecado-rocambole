package br.com.rocambole.dominio;

import java.util.List;

public class LeveMaisPagueMenos implements Promocao {
	
	private final Produto produtoAlvo;

	private final Long quantidadeMinima;

	private final Double novoPreco;

	public LeveMaisPagueMenos(Produto produtoAlvo, Long quantidadeMinima, Double novoPreco) {
		super();
		this.produtoAlvo = produtoAlvo;
		this.quantidadeMinima = quantidadeMinima;
		this.novoPreco = novoPreco;
	}

	@Override
	public Double precoPromocional(List<Produto> alvo) {
		final Long quantidadeProdutosNaLista = alvo.stream().filter(produtoAlvo::equals).count();
		
		if (quantidadeProdutosNaLista >= quantidadeMinima) {
			return novoPreco;
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
