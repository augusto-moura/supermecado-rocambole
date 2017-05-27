package br.com.rocambole.dominio;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListaComprasTest {
	private static final String CODIGO_A = "A";
	private static final String CODIGO_B = "B";
	private static final String CODIGO_C = "C";
	private static final String CODIGO_D = "D";

	/**
	 * Lista de compras a serem feitos os testesy
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
		// Mockando os valores, em produção eles viriam provavelmente de um
		// serviço de armazenamento de dados persistente;
		produtoA = new Produto("A", 50D);
		produtoB = new Produto("B", 30D);
		produtoC = new Produto("C", 20D);
		produtoD = new Produto("D", 15D);

		promocaoA = new LeveMaisPagueMenos(produtoA, 3L, 130D);
		promocaoB = new LeveMaisPagueMenos(produtoB, 2L, 45D);
		promocaoC = new LeveMaisPagueMenos(produtoC, 2L, produtoC.getPrecoUnitario() * 2);

		listaCompras = new ListaCompras(Arrays.asList(promocaoA, promocaoB, promocaoC),
				Arrays.asList(produtoA, produtoB, produtoC, produtoD));
	}

	@Test
	public void totalPriceDeveriaSerZeroQuandoListaVazia() {
		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals(resultado, (Double) 0D);
	}

	@Test
	public void totalPriceDeveriaTerOPrecoDoUnicoProdutoNaLista() {
		listaCompras.add(CODIGO_A);

		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals(resultado, produtoA.getPrecoUnitario());
	}

	@Test
	public void totalPriceDeveriaSerASomaDosProdutosNaLista() {
		final Double somaReal = produtoA.getPrecoUnitario() + produtoB.getPrecoUnitario();

		listaCompras.add(CODIGO_A);
		listaCompras.add(CODIGO_B);

		final Double resultado = listaCompras.getTotalPrice();

		Assert.assertEquals(resultado, somaReal);
	}

	@Test
	public void totalDiscountDeveriaSerZeroQuandoNenhumaPromocaoValida() {
		final Double resultado = listaCompras.getTotalDiscount();

		Assert.assertEquals(resultado, (Double) 0D);
	}

	@Test
	public void totalDiscountDeveriaSerMesmoValorDaPromocaoQuandoUnicaPromocaoValida() {
		listaCompras.add(CODIGO_A);
		listaCompras.add(CODIGO_A);
		listaCompras.add(CODIGO_A);

		final Double resultado = listaCompras.getTotalDiscount();

		Assert.assertEquals(resultado, promocaoA.precoPromocional(Arrays.asList(produtoA, produtoA, produtoA)));
	}

	@Test
	public void totalDiscountDeveriaSerSomaDosValoresDasPromocoesQuandoVariasPromocoesValidas() {
		final Double somaPromocoes = promocaoA.precoPromocional(Arrays.asList(produtoA, produtoA, produtoA))
				+ promocaoB.precoPromocional(Arrays.asList(produtoB, produtoB, produtoB));
		
		listaCompras.add(CODIGO_A);
		listaCompras.add(CODIGO_A);
		listaCompras.add(CODIGO_A);

		listaCompras.add(CODIGO_B);
		listaCompras.add(CODIGO_B);

		final Double resultado = listaCompras.getTotalDiscount();

		Assert.assertEquals(resultado, somaPromocoes);
	}

}
