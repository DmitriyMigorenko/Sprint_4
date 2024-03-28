package ru.yandex.praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests {
    private final String testOrderButton;
    private final String testUserName;
    private final String testUserSurname;
    private final String testUserAddress;
    private final String testUserMobilePhone;
    private final String testOrderDate;
    private final String testComment;

    public OrderTests(String testOrderButton, String testUserName, String testUserSurname, String testUserAddress, String testUserMobilePhone, String testOrderDate, String testComment) {
        this.testOrderButton = testOrderButton;
        this.testUserName = testUserName;
        this.testUserSurname = testUserSurname;
        this.testUserAddress = testUserAddress;
        this.testUserMobilePhone = testUserMobilePhone;
        this.testOrderDate = testOrderDate;
        this.testComment = testComment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Вверхняя кнопка", "Пётр", "Первый", "Санкт-Петербург, Невский проспект, дом 7", "88009993322", "01.09.2024", "Оставить у парадной"},
                {"Другая кнопка", "Слава", "Смирнов", "Москва, ул. Пушкина, дом 42", "89871239900", "31.12.2049", "123"}

        };
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void orderTest() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.acceptCookie();
        mainPage.clickOnOrderButton(testOrderButton);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.setUserData(testUserName, testUserSurname, testUserAddress, testUserMobilePhone);
        orderPage.setRentData(testOrderDate, testComment);
        orderPage.clickYesButton();
        boolean actualFinalModalWindow = orderPage.checkFinalModalWindow();

        assertTrue(actualFinalModalWindow);
    }
}
