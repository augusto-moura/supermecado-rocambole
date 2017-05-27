package br.com.rocambole.dominio;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListaComprasTest {

	private static final String CODIGO_PRODUTO_A = "A";
	private static final Double PRECO_UNITARIO_PRODUTO_A = 50D;

	private static final String CODIGO_PRODUTO_B = "B";
	private static final Double PRECO_UNITARIO_PRODUTO_B = 30D;

	private static final String CODIGO_PRODUTO_C = "C";
	private static final Double PRECO_UNITARIO_PRODUTO_C = 20D;

	private static final String CODIGO_PRODUTO_D = "D";
	private static final Double PRECO_UNITARIO_PRODUTO_D = 15D;

	private static final Long QUANTIDADE_MINIMA_PROMOCAO_A = 3L;
	private static final Double PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_A = 130D;

	private static final Long QUANTIDADE_MINIMA_PROMOCAO_B = 2L;
	private static final Double PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_B = 45D;

	private static final Long QUANTIDADE_MINIMA_PROMOCAO_C = 3L;
	private static final Double FATOR_MULTIPLICACAO_PRECO_PROMOCIONAL_PROMOCAO_C = 2D;

	/**
	 * Lista de compras a serem feitos os testes.
	 */
	private ListaCompras listaCompras;

	private Produto produtoA;
	private Produto produtoB;
	private Produto produtoC;
	private Produto produtoD;

	private Promocao promocaoA;
	private Promocao promocaoB;
	private Promocao promocaoC;

	/**
	 * Para garantir que os dados fiquem consistentes entre os testes, eles são
	 * resetados cada iteração.
	 */
	@Before
	public void iniciaTransientes() {
		produtoA = new Produto(CODIGO_PRODUTO_A, PRECO_UNITARIO_PRODUTO_A);
		produtoB = new Produto(CODIGO_PRODUTO_B, PRECO_UNITARIO_PRODUTO_B);
		produtoC = new Produto(CODIGO_PRODUTO_C, PRECO_UNITARIO_PRODUTO_C);
		produtoD = new Produto(CODIGO_PRODUTO_D, PRECO_UNITARIO_PRODUTO_D);

		promocaoA = new LeveMaisPagueMenos(produtoA, QUANTIDADE_MINIMA_PROMOCAO_A,
				PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_A);

		promocaoB = new LeveMaisPagueMenos(produtoB, QUANTIDADE_MINIMA_PROMOCAO_B,
				PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_B);

		promocaoC = new LeveMaisPagueMenos(produtoC, QUANTIDADE_MINIMA_PROMOCAO_C,
				produtoC.getPrecoUnitario() * FATOR_MULTIPLICACAO_PRECO_PROMOCIONAL_PROMOCAO_C);

		listaCompras = new ListaCompras(Arrays.asList(promocaoA, promocaoB, promocaoC),
				Arrays.asList(produtoA, produtoB, produtoC, produtoD));
	}

	@Test
	public void totalPriceDeveriaSerZeroQuandoListaVazia() {
		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals((Double) 0D, resultado);
	}

	@Test
	public void totalPriceDeveriaTerOPrecoDoUnicoProdutoNaLista() {
		listaCompras.add(CODIGO_PRODUTO_A);

		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals(produtoA.getPrecoUnitario(), resultado);
	}

	@Test
	public void totalPriceDeveriaSerASomaDosProdutosNaLista() {
		final Double somaReal = produtoA.getPrecoUnitario() + produtoB.getPrecoUnitario();

		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_B);

		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals(somaReal, resultado);
	}

	@Test
	public void totalDiscountDeveriaSerZeroQuandoNenhumaPromocaoValida() {
		final Double resultado = listaCompras.getTotalDiscount();

		Assert.assertEquals((Double) 0D, resultado);
	}

	@Test
	public void totalDiscountDeveriaSerMesmoValorDaPromocaoMenosValorUnitarioVezesQuantidadeQuandoUnicaPromocaoValida() {
		final Double esperado = (QUANTIDADE_MINIMA_PROMOCAO_A * PRECO_UNITARIO_PRODUTO_A)
				- PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_A;

		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_A);

		final Double resultado = listaCompras.getTotalDiscount();

		Assert.assertEquals(esperado, resultado, 0);
	}

	@Test
	public void totalDiscountDeveriaSerSomaDosValoresDasPromocoesMenosValorUnitarioVezesQuantidadeQuandoVariasPromocoesValidas() {
		final Double esperadoA = ((QUANTIDADE_MINIMA_PROMOCAO_A * PRECO_UNITARIO_PRODUTO_A)
				- PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_A);

		final Double esperadoB = ((QUANTIDADE_MINIMA_PROMOCAO_B * PRECO_UNITARIO_PRODUTO_B)
				- PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_B);

		final Double somaPromocoes = esperadoA + esperadoB;

		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_A);

		listaCompras.add(CODIGO_PRODUTO_B);
		listaCompras.add(CODIGO_PRODUTO_B);

		final Double resultado = listaCompras.getTotalDiscount();

		Assert.assertEquals(somaPromocoes, resultado);
	}

	@Test
	public void totalPriceDeveriaSerASomaDosProdutosNaListaMenosDescontos() {
		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_A);
		listaCompras.add(CODIGO_PRODUTO_A);

		listaCompras.add(CODIGO_PRODUTO_B);

		final Double somaReal = (produtoA.getPrecoUnitario() * 3) + produtoB.getPrecoUnitario()
				- ((QUANTIDADE_MINIMA_PROMOCAO_A * PRECO_UNITARIO_PRODUTO_A) - PRECO_PROMOCIONAL_ESPERADO_PROMOCAO_A);

		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals(somaReal, resultado);
	}

}
