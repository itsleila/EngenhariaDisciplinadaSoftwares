package br.com.infnet.Selenium.page;

import br.com.infnet.Selenium.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String URL_PAGE= "https://automationexercise.com/";
    private final By buttonProducts = By.cssSelector("a[href='/products']");
    private final By titleAllProducts = By.xpath("//h2[text()='All Products']");
    private final By inputSearchProduct = By.cssSelector("input[id='search_product']");
    private final By buttonSearch = By.cssSelector("button[id='submit_search']");
    private final By titleSearchedProducts = By.xpath("//h2[text()='Searched Products']");
    private final By listSearchedProducts = By.cssSelector("div[class='features_items']");
    private final By cardFirstSearchedProduct = By.cssSelector("div[class='product-image-wrapper']");
    private final By divOverlay = By.cssSelector("div[class='single-products']");
    private final By buttonAddToCart = By.cssSelector("a[class='btn btn-default add-to-cart']");
    private final By modalProductAdded = By.cssSelector("div[class='modal show']");
    private final By buttonContinueShopping = By.cssSelector("button[class='btn btn-success close-modal btn-block']");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void acessarPaginaProducts() {
        driver.get(URL_PAGE);
        clicarElemento(buttonProducts);
    }

    public boolean verificarTituloAllProducts() {
        try {
            return esperarElementoVisivel(titleAllProducts).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public ProductsPage preencherCampoBusca(String produto) {
        esperarElementoVisivel(inputSearchProduct).sendKeys(produto);
        return this;
    }

    public ProductsPage clicarBotaoSearch() {
        clicarElemento(buttonSearch);
        return this;
    }

    public boolean verificarTituloSearchedProducts() {
        try {
            return esperarElementoVisivel(titleSearchedProducts).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verificarProdutoNaLista() {
        try {
            esperarElementoVisivel(listSearchedProducts);
            return esperarElementoVisivel(cardFirstSearchedProduct).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clicarBotaoAddToCart() {
        clicarElementoAposHover(divOverlay, buttonAddToCart);
    }
    public boolean verificarModalProdutoAdicionado() {
        try {
            return esperarElementoVisivel(modalProductAdded).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clicarBotaoContinueShopping() {
        clicarElemento(buttonContinueShopping);
    }



}