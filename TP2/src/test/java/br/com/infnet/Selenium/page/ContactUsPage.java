package br.com.infnet.Selenium.page;

import br.com.infnet.Selenium.core.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {
    private static final String URL_PAGE= "https://automationexercise.com/";
    private final By buttonContactUs = By.cssSelector("a[href='/contact_us']");
    private final By inputName = By.cssSelector("input[data-qa='name']");
    private final By inputEmail = By.cssSelector("input[data-qa='email']");
    private final By inputSubject = By.cssSelector("input[data-qa='subject']");
    private final By inputMessage = By.cssSelector("textarea[data-qa='message']");
    private final By buttonSubmit = By.cssSelector("input[data-qa='submit-button']");
    private final By successMessage = By.cssSelector("div.status.alert.alert-success");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void acessarPaginaContactUs() {
        driver.get(URL_PAGE);
        clicarElemento(buttonContactUs);
    }

    public ContactUsPage preencherNome(String nome) {
        esperarElementoVisivel(inputName).sendKeys(nome);
        return this;
    }

    public ContactUsPage preencherEmail(String email) {
        esperarElementoVisivel(inputEmail).sendKeys(email);
        return this;
    }

    public ContactUsPage preencherAssunto(String assunto) {
        esperarElementoVisivel(inputSubject).sendKeys(assunto);
        return this;
    }

    public ContactUsPage preencherMensagem(String mensagem) {
        esperarElementoVisivel(inputMessage).sendKeys(mensagem);
        return this;
    }

    public ContactUsPage clicarBotaoSubmit() {
        clicarElemento(buttonSubmit);
        return this;
    }

    public ContactUsPage validarAlerta() {
        Alert alerta = capturarAlerta();
        alerta.accept();
        return this;
    }

    public boolean verificarMensagemSucesso() {
        try {
            return esperarElementoVisivel(successMessage).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
