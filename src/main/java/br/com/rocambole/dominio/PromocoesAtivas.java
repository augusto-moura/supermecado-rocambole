package br.com.rocambole.dominio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A lista de Promocoes ativas pode ser qualquer serviço de armazenamento de
 * dados persistente.
 */
public enum PromocoesAtivas {

	ALPHA(new LeveMaisPagueMenos(Estoque.A.getProduto(), 3L, 130D)),
	BETA(new LeveMaisPagueMenos(Estoque.B.getProduto(), 2L, 45D)),
	CHARLIE(new LeveMaisPagueMenos(Estoque.C.getProduto(), 2L, Estoque.C.getProduto().getPrecoUnitario() * 2));

	private final Promocao promocao;

	private PromocoesAtivas(Promocao promocao) {
		this.promocao = promocao;
	}
	
	public static List<Promocao> getPromocoesAtivas() {
		return Arrays.stream(values()).map(PromocoesAtivas::getPromocao).collect(Collectors.toList());
	}

	public Promocao getPromocao() {
		return promocao;
	}

}
