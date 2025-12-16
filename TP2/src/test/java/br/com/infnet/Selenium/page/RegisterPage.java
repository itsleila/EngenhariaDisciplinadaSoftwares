package br.com.infnet.Selenium.page;

import br.com.infnet.Selenium.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {
    private static final String URL_SINGUP = "https://automationexercise.com/signup";
    private final By inputGenderMrs = By.id("id_gender2");
    private final By inputName = By.cssSelector("input[data-qa='signup-name']");
    private final By inputEmail = By.cssSelector("input[data-qa='signup-email']");
    private final By emailExistenteMsg = By.xpath("//p[contains(text(),'Email Address already exist')]");
    private final By buttonSignup = By.cssSelector("button[data-qa='signup-button']");
    private final By inputPassword= By.id("password");
    private final By inputFirstName= By.id("first_name");
    private final By inputLastName = By.id("last_name");
    private final By inputAdress = By.id("address1");
    private final By selectCountryLocator = By.id("country");
    private final By inputState = By.id("state");
    private final By inputCity = By.id("city");
    private final By inputZipcode = By.id("zipcode");
    private final By inputMobileNumber = By.id("mobile_number");
    private final By buttonCreateAccount = By.cssSelector("button[data-qa='create-account']");


    public RegisterPage (WebDriver driver) {
        super(driver);
    }

    public void acessarPaginaSingUp() {
        driver.get(URL_SINGUP);
    }

    public RegisterPage selecionarTituloMrs() {
        clicarElemento(inputGenderMrs);
        return this;
    }
    public RegisterPage  preencherNome(String nome) {
        esperarElementoVisivel(inputName).sendKeys(nome);
        return this;
    }

    public RegisterPage  clicarBotaoSingUp() {
        clicarElemento(buttonSignup);
        return this;
    }

    public RegisterPage  preencherEmail(String email) {
        esperarElementoVisivel(inputEmail).sendKeys(email);
        return this;
    }

    public RegisterPage  preencherSenha(String senha) {
        esperarElementoVisivel(inputPassword).sendKeys(senha);
        return this;
    }

    public RegisterPage  preencherPrimeiroNome(String primeiroNome) {
        esperarElementoVisivel(inputFirstName).sendKeys(primeiroNome);
        return this;
    }

    public RegisterPage  preencherUltimoNome(String ultimoNome) {
        esperarElementoVisivel(inputLastName).sendKeys(ultimoNome);
        return this;
    }

    public RegisterPage  preencherEndereco(String endereco) {
        esperarElementoVisivel(inputAdress).sendKeys(endereco);
        return this;
    }

    public RegisterPage  selecionarPais(String pais) {
        Select selectCountry = new Select(esperarElementoVisivel(selectCountryLocator));
        selectCountry.selectByVisibleText(pais);
        return this;
    }

    public RegisterPage  preencherEstado(String estado) {
        esperarElementoVisivel(inputState).sendKeys(estado);
        return this;
    }

    public RegisterPage  preencherCidade(String cidade) {
        esperarElementoVisivel(inputCity).sendKeys(cidade);
        return this;
    }

    public RegisterPage  preencherZipcode(String zipcode) {
        esperarElementoVisivel(inputZipcode).sendKeys(zipcode);
        return this;
    }

    public RegisterPage  preencherNumeroCelular(String numeroCelular) {
        esperarElementoVisivel(inputMobileNumber).sendKeys(numeroCelular);
        return this;
    }

    public void clicarBotaoCriarConta() {
        clicarElemento(buttonCreateAccount);
    }

    public boolean verificarContaCriadaComSucesso() {
        By mensagemContaCriadaLocator = By.cssSelector("h2[data-qa='account-created']");
        try {
            esperarElementoVisivel(mensagemContaCriadaLocator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verificarEmailExistente() {
        try {
            esperarElementoVisivel(emailExistenteMsg);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
