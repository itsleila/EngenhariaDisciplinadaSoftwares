package br.com.infnet.Selenium.test;

import br.com.infnet.Selenium.core.BaseTest;
import br.com.infnet.Selenium.page.ContactUsPage;
import br.com.infnet.Selenium.page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Verificar se o footer da página inicial está visível")
    public void testVerificarFooterVisivel() {
        homePage.acessarPaginaHome();
        Assertions.assertTrue(homePage.verificarFooterVisivel());
    }

    @Test
    @DisplayName("Testar subscription no footer da página inicial")
    public void testSubscriptionHome() {
        String email = "AjuliaCosta@example.com";
        homePage.acessarPaginaHome();
        testVerificarFooterVisivel();
        homePage.preencherEmailParaAssinatura(email);
        homePage.clicarBotaoSubscribe();
        Assertions.assertTrue(homePage.verificarMensagemConfirmacao());
    }
}
