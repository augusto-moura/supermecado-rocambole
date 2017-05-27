package br.com.rocambole.dominio;

import java.util.List;

public class LeveMaisPagueMenos implements Promocao {
	
	private final Produto alvo;

	private final Long quantidadeCritica;

	private final Double novoPeso;

	public LeveMaisPagueMenos(Produto alvo, Long quantidadeCritica, Double modificadorPreco) {
		this.alvo = alvo;
		this.quantidadeCritica = quantidadeCritica;
		this.novoPeso = modificadorPreco;
	}

	@Override
	public Double precoPromocional(List<Produto> alvo) {
		throw new RuntimeException("Não implementado");
	}

	public Produto getAlvo() {
		return alvo;
	}

	public Long getQuantidadeCritica() {
		return quantidadeCritica;
	}

	public Double getModificadorPreco() {
		return novoPeso;
	}

}
