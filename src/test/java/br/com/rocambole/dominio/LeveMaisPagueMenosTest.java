package br.com.rocambole.dominio;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeveMaisPagueMenosTest {

	private static Long QUANTIDADE_MINIMA_PROMOCAO = 3L;
	private static Double PRECO_PROMOCIONAL_ESPERADO_PROMOCAO = 130D;

	private static String CODIGO_PRODUTO_A = "A";
	private static Double PRECO_PRODUTO_A = 50D;

	/**
	 * Promocao a ser testada.
	 */
	private LeveMaisPagueMenos promocao;

	/**
	 * Produto ao qual a promoção se refere.
	 */
	private Produto produto;


	/**
	 * Para garantir que os dados fiquem consistentes entre os testes, eles são
	 * resetados cada iteração.
	 */
	@Before
	public void inicializarVariaveis() {
		produto = new Produto(CODIGO_PRODUTO_A, PRECO_PRODUTO_A);
		promocao = new LeveMaisPagueMenos(produto, QUANTIDADE_MINIMA_PROMOCAO, PRECO_PROMOCIONAL_ESPERADO_PROMOCAO);
	}

	@Test
	public void precoPromocinalDeveriaSerZeroQuandoListaVazia() {
		final Double resultado = promocao.precoPromocional(Collections.emptyList());

		Assert.assertEquals((Double) 0D, resultado);
	}

	@Test
	public void precoPromocinalDeveriaSerZeroQuandoNaoAtingeQuantidadeMinima() {
		final Double resultado = promocao.precoPromocional(Arrays.asList(produto));

		Assert.assertEquals((Double) 0D, resultado);
	}

	@Test
	public void precoPromocinalDeveriaSerPrecoPromocionalEsperadoQuandoAtingeQuantidadeMinima() {
		final Double resultado = promocao.precoPromocional(Arrays.asList(produto, produto, produto));

		Assert.assertEquals(PRECO_PROMOCIONAL_ESPERADO_PROMOCAO, resultado);
	}

	@Test
	public void precoPromocinalDeveriaSerPrecoPromocionalEsperadoMesmoQuandoPossivelMaisDeUmaPromocao() {
		final Double resultado = promocao.precoPromocional(
				Arrays.asList(produto, produto, produto, produto, produto, produto, produto, produto, produto));

		Assert.assertEquals(PRECO_PROMOCIONAL_ESPERADO_PROMOCAO, resultado);
	}

}
