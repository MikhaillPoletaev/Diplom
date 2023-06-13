package ru.netology.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import ru.netology.DataHelper.DataHelper;
import ru.netology.PageObjects.Objects;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    @DisplayName("Успешно проведенная операция при покупке в кредит")
    void shouldProvideSuccessOperation() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.validData();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findSuccessMessage();
    }

    @Test
    @DisplayName("Операция отклонена при покупке в кредит")
    void shouldDeclineOperation() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.secondCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.validData();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorMessage();
    }

    @Test
    @DisplayName("Введен неверный формат номера карты")
    void incorrectFormatBundleIsVisibleForCardNumber() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.invalidCardInfo();
        Objects.byCard(cardNumber);
        var monthYear = DataHelper.validData();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }

    @Test
    @DisplayName("Истекший срок действия карты")
    void expiredCard() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.invalidData();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }

    @Test
    @DisplayName("Некорректный срок действия, 13 месяц")
    void incorrectMonth() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.invalidMonthOne();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }

    @Test
    @DisplayName("Некорректный срок действия, 0 месяц")
    void incorrectMonthSecond() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.invalidMonthTwo();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }

    @Test
    @DisplayName("Не указан владелец карты")
    void cardholderNameFieldIsUnpopulated() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.validData();
        Objects.setMonthYear(monthYear);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }

    @Test
    @DisplayName("Владелец карты указан на русском")
    void cardholderNameIncorrectLanguage() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.validData();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.invalidName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.validCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }

    @Test
    @DisplayName("CVV/CVC заполненно некорректно")
    void incorrectFormatForCVV() {
        Objects app = PageFactory.initElements(driver, Objects.class);
        var cardNumber = DataHelper.firstCardInfo();
        Objects.byCredit(cardNumber);
        var monthYear = DataHelper.validData();
        Objects.setMonthYear(monthYear);
        var cardHolder = DataHelper.validName();
        Objects.setCardHolder(cardHolder);
        var code = DataHelper.invalidCode();
        Objects.setVerification(code);
        Objects.clickOnContinue();
        Objects.findErrorNotification();
    }


}
