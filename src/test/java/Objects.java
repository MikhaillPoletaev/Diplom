import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Objects extends BaseSeleniumPage{

    @FindBy(xpath = "//span[@class='button__content']/*[.='Купить в кредит']")
    private WebElement creditButton;

    @FindBy(xpath = "//span[@class='button__text'][.='Купить']")
    private WebElement buyButton;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @maxlength='19']")
    private WebElement cardNumber;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @placeholder='08']")
    private WebElement month;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @placeholder='22']")
    private WebElement year;

    @FindBy(xpath = "//*[@id=\\\"root\\\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input")
    private WebElement cardHolder;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @placeholder='999']")
    private WebElement verificationCode;

    @FindBy(xpath = "//*[text()='Продолжить']")
    private WebElement continueButton;

    @FindBy(xpath = "//span[@class='input__sub']")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains (@class, 'notification_status_ok')]")
    private WebElement successNotification;

    @FindBy(xpath = "//div[contains (@class, 'notification_status_error')]")
    private WebElement errorNotification;

    public Objects() {
        driver.get("http://localhost:8080/");
        PageFactory.initElements(driver, this);
    }

    public Objects successBuy(DataHelper.CardInfo cardValue , DataHelper.MonthYear monthValue, DataHelper.MonthYear yearValue,
                              DataHelper.CardHolder cardHolderValue, DataHelper.VerificationCode verificationValue){
        buyButton.click();
        cardNumber.sendKeys(cardValue.getCardNumber());
        month.sendKeys(monthValue.getMonth());
        year.sendKeys(yearValue.getYear());
        cardHolder.sendKeys(cardHolderValue.getName());
        verificationCode.sendKeys(verificationValue.getCode());
        continueButton.click();
        return this;
    }






}
