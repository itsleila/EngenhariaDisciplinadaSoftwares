package br.com.infnet.Selenium.page;

import br.com.infnet.Selenium.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final String URL_PAGE = "https://automationexercise.com/";
    private final By buttonCart = By.cssSelector("a[href='/view_cart']");
    private final By viewProduct = By.cssSelector("a[href='/product_details/1']");
    private final By inputQuantity = By.id("quantity");
    private final By buttonAddToCart = By.cssSelector("button[class='btn btn-default cart']");
    private final By continueShopping = By.cssSelector("button[class='btn btn-success close-modal btn-block']");
    private final By produtoNaTabela = By.cssSelector("td[class='cart_description'] h4 a");
    private final By quantidadeProduto = By.cssSelector("button.disabled");
    private final By removeProduto = By.cssSelector("a[class='cart_quantity_delete']");
    private final By cartEmptyMessage = By.id("empty_cart");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void paginaInicial() {
        driver.get(URL_PAGE);
    }


    public CartPage acessarPaginaCart() {
        clicarElemento(buttonCart);
        return this;
    }

    public CartPage clicarViewProduct() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", esperarElementoVisivel(viewProduct));
        clicarElemento(viewProduct);
        return this;
    }

    public CartPage preencherQuantidadeProduto(String quantidade) {
        esperarElementoVisivel(inputQuantity).clear();
        esperarElementoVisivel(inputQuantity).sendKeys(quantidade);
        return this;
    }

    public CartPage clicarBotaoAddToCart() {
        clicarElemento(buttonAddToCart);
        return this;
    }

    public CartPage clicarBotaoContinueShopping() {
        clicarElemento(continueShopping);
        return this;
    }

    public boolean verificarProdutoNaTabela() {
        return elementoEstaVisivel(produtoNaTabela);
    }

    public boolean verificarQuantidadeProduto(String quantidadeEsperada) {
        String quantidadeAtual = obterTextoElemento(quantidadeProduto);
        return quantidadeAtual.equals(quantidadeEsperada);
    }

    public CartPage clicarBotaoRemoveProduto() {
        clicarElemento(removeProduto);
        return this;
    }

    public boolean verificarCarrinhoVazio() {
        return elementoEstaVisivel(cartEmptyMessage);
    }



}
