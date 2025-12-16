package br.com.infnet.Selenium.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement esperarElementoVisivel(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean elementoEstaVisivel(By locator) {
        try {
            esperarElementoVisivel(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected String obterTextoElemento(By locator) {
        WebElement element = esperarElementoVisivel(locator);
        return element.getText().trim();
    }

    protected void clicarElemento(By locator) {
        esperarElementoVisivel(locator).click();
    }

    protected void clicarElementoAposHover(By elementoHover, By elementoClique) {
        Actions actions = new Actions(driver);
        WebElement hover = wait.until(ExpectedConditions.visibilityOfElementLocated(elementoHover));

        WebElement clicavel = wait.until(ExpectedConditions.visibilityOfElementLocated(elementoClique));

        actions.moveToElement(hover)
            .pause(Duration.ofMillis(400))
            .moveToElement(clicavel)
            .pause(Duration.ofMillis(100))
            .click()
            .perform();
    }


    public Alert capturarAlerta() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}

