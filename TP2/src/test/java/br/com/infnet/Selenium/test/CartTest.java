package br.com.infnet.Selenium.test;

import br.com.infnet.Selenium.core.BaseTest;
import br.com.infnet.Selenium.page.CartPage;
import br.com.infnet.Selenium.page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {
    private CartPage cartPage;


    @BeforeEach
    public void setupTest() {
        cartPage = new CartPage(driver);
    }

    @Test
    @DisplayName("Adicionar produto no carrinho apartir da pagina home e verificar se o produto foi adicionado")
    public void testAdicionarProdutoNoCarrinho() {
        cartPage.paginaInicial();
        cartPage.clicarViewProduct()
                .clicarBotaoAddToCart()
                .clicarBotaoContinueShopping()
                .acessarPaginaCart();

        Assertions.assertTrue(cartPage.verificarProdutoNaTabela(), "O produto não foi adicionado ao carrinho.");
    }

    @Test
    @DisplayName("Deve adicionar um produto ao carrinho e verificar a quantidade")
    public void testAdicionarProdutoAoCarrinhoComQuantidade() {
        String quantidade = "3";
        cartPage.paginaInicial();
        cartPage.clicarViewProduct()
                .preencherQuantidadeProduto(quantidade)
                .clicarBotaoAddToCart()
                .clicarBotaoContinueShopping()
                .acessarPaginaCart();
        Assertions.assertTrue(cartPage.verificarQuantidadeProduto(quantidade), "A quantidade do produto no carrinho está incorreta.");
    }

    @Test
    @DisplayName("Remover produto do carrinho e verificar se o carrinho está vazio")
    public void testRemoverProdutoDoCarrinho() {
        testAdicionarProdutoNoCarrinho();
        cartPage.clicarBotaoRemoveProduto();
        Assertions.assertTrue(cartPage.verificarCarrinhoVazio(), "O carrinho não está vazio após remover o produto.");
    }
}
