import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Test {
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
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @org.junit.jupiter.api.Test
    void shouldTest() {
        var cardNumber = DataHelper.firstCardInfo();
        var month = DataHelper.validData();
        var year = DataHelper.validData();
        var cardHolder = DataHelper.validName();
        var code = DataHelper.validCode();
        Objects objects = new Objects();
        objects.byCard(cardNumber);
        objects.setMonthYear(month);
        objects.setMonthYear(year);
        objects.setCardHolder(cardHolder);
        objects.setVerification(code);
        Objects.findSuccessMessage();

    }

}
