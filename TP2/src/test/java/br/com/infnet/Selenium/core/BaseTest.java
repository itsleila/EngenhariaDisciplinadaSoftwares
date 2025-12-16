package br.com.infnet.Selenium.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected WebDriver driver;
    @BeforeAll
    static void inicializarDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void abrirNavegaor() {
        ChromeOptions options = configurarChrome();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @AfterEach
    void fecharNavegaor() {
        if(driver != null) {
            driver.quit();
        }
    }

    private ChromeOptions configurarChrome() {
        ChromeOptions options = new ChromeOptions();
        return options;
    }
}
