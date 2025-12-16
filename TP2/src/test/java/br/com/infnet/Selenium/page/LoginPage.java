package br.com.infnet.Selenium.page;

import br.com.infnet.Selenium.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final String urlLoginPage = "https://automationexercise.com/login";
    private final By inputEmailLocator = By.cssSelector("input[data-qa='login-email']");
    private final By inputPasswordLocator = By.cssSelector("input[data-qa='login-password']");
    private final By buttonLoginLocator = By.cssSelector("button[data-qa='login-button']");
    private final By messageErrorLoginLocator = By.xpath("//p[text()='Your email or password is incorrect!']");
    private final By elementoUsuarioLogadoLocator = By.cssSelector("a[href='/logout']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void acessarPaginaLogin() {
        driver.get(urlLoginPage);
    }

    public LoginPage preencherEmail(String email) {
        esperarElementoVisivel(inputEmailLocator).sendKeys(email);
        return this;
    }

    public LoginPage preencherSenha(String senha) {
        esperarElementoVisivel(inputPasswordLocator).sendKeys(senha);
        return this;
    }

    public LoginPage clicarBotaoLogin() {
        clicarElemento(buttonLoginLocator);
        return this;
    }

    public boolean verificarLoginComSucesso() {
        By elementoUsuarioLogadoLocator = By.cssSelector("a[href='/logout']");
        return esperarElementoVisivel(elementoUsuarioLogadoLocator).isDisplayed();
    }

    public boolean verificarMensagemErroLogin() {
        return esperarElementoVisivel(messageErrorLoginLocator).isDisplayed();
    }

    public boolean verificarElementoUsuarioLogado() {
        try {
            esperarElementoVisivel(elementoUsuarioLogadoLocator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clicarElementoUsuarioLogado() {
        clicarElemento(elementoUsuarioLogadoLocator);
    }
}
