package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    //локатор для имени
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    //локатор для фамилии
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор для адреса доставки
    private final By orderAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор для фокуса на поле станция метро
    private final By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор для выбора станции метро
    private final By selectedMetroStation = By.xpath(".//li[@data-index='166']");
    //локатор для телефона заказчика
    private final By mobilePhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор для кнопки Далее
    private final By nextButton = By.cssSelector(".Button_Middle__1CSJM");
    //локатор для даты заказа
    private final By orderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор для срока аренды
    private final By termOfRent = By.className("Dropdown-placeholder");
    //локатор для выбора продолжительности аренды из выпадающего списка
    private final By fiveDays = By.xpath(".//div[@class='Dropdown-option' and text()='пятеро суток']");
    //локатор для выбора черного цвета самоката
    private final By blackColor = By.id("black");
    //локатор для комментария
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор для кнопки Заказать
    private final By orderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор для кнопки "Да"
    private final By yesButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //локатор для финального модального окна заказа
    private final By finalModalWindow = By.xpath(".//div[@class ='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    public void setSurname(String userSurname) {
        driver.findElement(surname).sendKeys(userSurname);
    }

    public void setOrderAddress(String userAddress) {
        driver.findElement(orderAddress).sendKeys(userAddress);
    }

    public void setMetroStation() {
        driver.findElement(metroStation).click();
        driver.findElement(selectedMetroStation).click();
    }

    public void setMobilePhone(String userMobilePhone) {
        driver.findElement(mobilePhone).sendKeys(userMobilePhone);
    }

    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }

    //Ввести персональные данные заказчика
    public void setUserData(String userName, String userSurname, String userAddress, String userMobilePhone) {
        setName(userName);
        setSurname(userSurname);
        setOrderAddress(userAddress);
        setMetroStation();
        setMobilePhone(userMobilePhone);
        clickOnNextButton();
    }

    public void setOrderDate(String newOrderDate) {
        driver.findElement(orderDate).sendKeys(newOrderDate);
        driver.findElement(orderDate).sendKeys(Keys.ENTER);
    }

    public void chooseOrderTerm() {
        driver.findElement(termOfRent).click();
        driver.findElement(fiveDays).click();
    }

    public void chooseBlackColor() {
        driver.findElement(blackColor).click();
    }

    public void setComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
    }

    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Ввести данные по аренде
    public void setRentData(String orderDate, String comment) {
        setOrderDate(orderDate);
        chooseOrderTerm();
        chooseBlackColor();
        setComment(comment);
        clickOnOrderButton();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public boolean checkFinalModalWindow() {
        return driver.findElement(finalModalWindow).isDisplayed();
    }
}