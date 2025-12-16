package br.com.infnet.Selenium.test;

import br.com.infnet.Selenium.core.BaseTest;
import br.com.infnet.Selenium.page.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
    }
    @Test
    @DisplayName("Login com credenciais válidas")
    void loginComSucesso() {
        loginPage.acessarPaginaLogin();
        loginPage.preencherEmail("tswift@example.com");
        loginPage.preencherSenha("likeDaylight");
        loginPage.clicarBotaoLogin();
        assertTrue(loginPage.verificarLoginComSucesso());
    }

    @Test
    @DisplayName("Login com credenciais incorretas")
    void loginComFalha() {
        loginPage.acessarPaginaLogin();
        loginPage.preencherEmail("tswift@example.com");
        loginPage.preencherSenha("SenhaIncorreta");
        loginPage.clicarBotaoLogin();
        assertTrue(loginPage.verificarMensagemErroLogin());
    }

    @Test
    @DisplayName("Logout de usuário com sucesso")
    void logoutUsuer(){
        loginComSucesso();
        assertTrue(loginPage.verificarElementoUsuarioLogado());
        loginPage.clicarElementoUsuarioLogado();
        assertFalse(loginPage.verificarElementoUsuarioLogado());
    }
}
