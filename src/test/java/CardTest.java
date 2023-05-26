import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("10");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("23");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains (@class, 'notification_status_ok')]")).isDisplayed();
    }

    @Test
    @DisplayName("Операция отклонена при покупке в кредит")
    void shouldDeclineOperation() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4444");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("10");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("23");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains (@class, 'notification_status_error')]")).isDisplayed();
    }

    @Test
    @DisplayName("Введен неверный формат номера карты")
    void incorrectFormatBundleIsVisibleForCardNumber() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 444");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("10");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("23");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Неверный формат", actualText);
    }

    @Test
    @DisplayName("Истекший срок действия карты")
    void expiredCard() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("10");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("22");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Истёк срок действия карты", actualText);
    }

    @Test
    @DisplayName("Некорректный срок действия, 13 месяц")
    void incorrectMonth() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("13");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("24");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Неверно указан срок действия карты", actualText);
    }

    @Test
    @DisplayName("Некорректный срок действия, 0 месяц")
    void incorrectMonthSecond() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("00");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("24");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Неверно указан срок действия карты", actualText);
    }

    @Test
    @DisplayName("Не указан владелец карты")
    void cardholderNameFieldIsUnpopulated() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("00");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("24");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Поле обязательно для заполнения", actualText);
    }

    @Test
    @DisplayName("Владелец карты указан на русском")
    void cardholderNameIncorrectLanguage() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("00");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("24");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("Алейксей");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("999");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Неверный формат", actualText);
    }

    @Test
    @DisplayName("CVV/CVC заполненно некорректно")
    void incorrectFormatForCVV() {
        driver.findElement(By.xpath("//span[@class='button__content']/*[.='Купить в кредит']")).click();
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @maxlength='19']"))
                .sendKeys("4444 4444 4444 4441");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='08']"))
                .sendKeys("12");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='22']"))
                .sendKeys("24");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"))
                .sendKeys("APPA");
        driver.findElement(By.xpath("//span[@class='input__box']/input[@class='input__control' and @placeholder='999']"))
                .sendKeys("99");
        driver.findElement(By.xpath("//*[text()='Продолжить']")).click();
        driver.findElement(By.xpath("//span[@class='input__sub']")).getText();
        var actualText = driver.findElement(By.xpath("//span[@class='input__sub']")).getText().trim();
        assertEquals("Неверный формат", actualText);
    }


}
