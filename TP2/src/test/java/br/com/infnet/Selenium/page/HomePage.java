package br.com.infnet.Selenium.page;

import br.com.infnet.Selenium.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String URL_PAGE = "https://automationexercise.com/";
    private final By footer = By.id("footer");
    private final By inputSubscribeEmail = By.id("susbscribe_email");
    private final By buttonSubscribe = By.id("subscribe");
    private final By confirmationMessage = By.id("success-subscribe");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acessarPaginaHome() {
        driver.get(URL_PAGE);
    }

    public boolean verificarFooterVisivel() {
        try {
            return esperarElementoVisivel(footer).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public HomePage preencherEmailParaAssinatura(String email) {
        esperarElementoVisivel(inputSubscribeEmail).sendKeys(email);
        return this;
    }

    public HomePage clicarBotaoSubscribe() {
        clicarElemento(buttonSubscribe);
        return this;
    }

    public boolean verificarMensagemConfirmacao() {
        try {
            return esperarElementoVisivel(confirmationMessage).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
