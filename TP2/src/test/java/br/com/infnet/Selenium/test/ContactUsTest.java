package br.com.infnet.Selenium.test;

import br.com.infnet.Selenium.core.BaseTest;
import br.com.infnet.Selenium.page.ContactUsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactUsTest extends BaseTest {

    @Test
    @DisplayName("Envio de mensagem pelo formulário Contact Us com sucesso")
    void envioMensagemContactUsComSucesso() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.acessarPaginaContactUs();
        contactUsPage.preencherNome("Percy Jackson")
                .preencherEmail("percy.jackson@example.com")
                .preencherAssunto("Son Of Poseidon")
                .preencherMensagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
                .clicarBotaoSubmit()
                .validarAlerta();
        Assertions.assertTrue(contactUsPage.verificarMensagemSucesso(), "A mensagem de sucesso não foi exibida.");
    }
}
