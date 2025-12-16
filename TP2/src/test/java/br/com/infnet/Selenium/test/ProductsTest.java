package br.com.infnet.Selenium.test;

import br.com.infnet.Selenium.core.BaseTest;
import br.com.infnet.Selenium.page.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProductsTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeEach
    public void setUp() {
        productsPage = new ProductsPage(driver);
    }

    @Test
    @DisplayName("Acessar pagina de produtos com sucesso")
    public void testAcessarPaginaProducts() {
        productsPage.acessarPaginaProducts();
        Assertions.assertTrue(productsPage.verificarTituloAllProducts());
    }

    @Test
    @DisplayName("Buscar produto existente retornando lista de produtos")
    public void testBuscarProdutoExistente() {
        String produto = "Frozen Tops For Kids";
        productsPage.acessarPaginaProducts();
        productsPage.preencherCampoBusca(produto);
        productsPage.clicarBotaoSearch();
        Assertions.assertTrue(productsPage.verificarTituloSearchedProducts());
        Assertions.assertTrue(productsPage.verificarProdutoNaLista());
    }

    @Test
    @DisplayName("Buscar produto inexistentes não retorna lista de produtos")
    public void testBuscarProdutoInexistente() {
        String produto = "Moana Tops For Kids";
        productsPage.acessarPaginaProducts();
        productsPage.preencherCampoBusca(produto);
        productsPage.clicarBotaoSearch();
        Assertions.assertTrue(productsPage.verificarTituloSearchedProducts());
        Assertions.assertFalse(productsPage.verificarProdutoNaLista());

    }

    @Test
    @DisplayName("Adicionar produto ao carrinho apartir da página de Products")
    public void testAdicionarProdutoAoCarrinho() {
        testBuscarProdutoExistente();
        productsPage.clicarBotaoAddToCart();
        Assertions.assertTrue(productsPage.verificarModalProdutoAdicionado());
        productsPage.clicarBotaoContinueShopping();
    }
}