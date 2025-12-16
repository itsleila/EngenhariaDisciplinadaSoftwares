package br.com.infnet.Selenium.test;

import br.com.infnet.Selenium.core.BaseTest;
import br.com.infnet.Selenium.page.RegisterPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegisterTest extends BaseTest {
    RegisterPage RegisterPage;

    @BeforeEach
    public void setUp() {
        RegisterPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("Registro de usuario com sucesso")
    void registroUsuarioComSucesso() {
        RegisterPage.acessarPaginaSingUp();
        RegisterPage.preencherNome("Taylor Swift");
        RegisterPage.preencherEmail("tswift13@example.com");
        RegisterPage.clicarBotaoSingUp();
        RegisterPage.selecionarTituloMrs();
        RegisterPage.preencherSenha("likeDaylight");
        RegisterPage.preencherPrimeiroNome("Taylor");
        RegisterPage.preencherUltimoNome("Swift");
        RegisterPage.preencherEndereco("Cornelia Street, 2308");
        RegisterPage.selecionarPais("United States");
        RegisterPage.preencherEstado("New York");
        RegisterPage.preencherCidade("New York");
        RegisterPage.preencherZipcode("11111111");
        RegisterPage.preencherNumeroCelular("1111111111");
        RegisterPage.clicarBotaoCriarConta();
        Assertions.assertTrue(RegisterPage.verificarContaCriadaComSucesso(), "A mensagem de conta criada com sucesso não foi exibida.");
    }

    @Test
    @DisplayName("Registro de usuario com email ja existente")
    void registroUsuarioComEmailExistente() {
        RegisterPage.acessarPaginaSingUp();
        RegisterPage.preencherNome("TS13");
        RegisterPage.preencherEmail("tswift@example.com");
        RegisterPage.clicarBotaoSingUp();
        Assertions.assertTrue(RegisterPage.verificarEmailExistente(), "A mensagem de email existente não foi exibida.");

    }
}
