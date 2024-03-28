package ru.yandex.praktikum.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.Config;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    //локатор для кнопки куки
    private final By cookieButton = By.id("rcc-confirm-button");
    //локатор кнопки Заказать вверху страницы
    private final By headerOrderButton = By.className("Button_Button__ra12g");
    //локатор кнопки Заказать в центре страницы
    private final By middleOrderButton = By.cssSelector(".Button_Middle__1CSJM");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(Config.URL);
    }

    public void acceptCookie() {
        driver.findElement(cookieButton).click();
    }

    //Кликнуть по вопросу из раздела "Вопросы о важном"
    public void clickOnButtonOfFaqList(String idOfElement) {
        WebElement element = driver.findElement(By.id("accordion__heading-" + idOfElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //Получить ответ на соответствующий вопрос из раздела "Вопросы о важном"
    public String getTextOfFaqList(String idOfElement) {
        driver.findElement(By.id("accordion__panel-" + idOfElement)).isDisplayed();
        return driver.findElement(By.id("accordion__panel-" + idOfElement)).getText();
    }

    //Кликнуть по кнопке Заказать в зависимости от её расположения на сайте
    public void clickOnOrderButton(String orderButton) {
        if (orderButton.equals(Config.HEADER_BUTTON)) {
            driver.findElement(headerOrderButton).click();
        } else {
            WebElement element = driver.findElement(middleOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }
}