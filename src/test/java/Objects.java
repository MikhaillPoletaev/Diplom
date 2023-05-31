import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Objects {

    @FindBy(xpath = "//span[@class='button__content']/*[.='Купить в кредит']")
    private static WebElement creditButton;

    @FindBy(xpath = "//span[@class='button__content']/*[.='Купить']")
    private static WebElement buyButton;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @maxlength='19']")
    private static WebElement cardNumber;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @placeholder='08']")
    private static WebElement month;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @placeholder='22']")
    private static WebElement year;

    @FindBy(xpath = "/html/body/div/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input")
    private static WebElement cardHolder;

    @FindBy(xpath = "//span[@class='input__box']/input[@class='input__control' and @placeholder='999']")
    private static WebElement verificationCode;

    @FindBy(xpath = "//*[text()='Продолжить']")
    private static WebElement continueButton;

    @FindBy(xpath = "//span[@class='input__sub']")
    private static WebElement errorMessage;

    @FindBy(xpath = "//div[contains (@class, 'notification_status_ok')]")
    private static WebElement successNotification;

    @FindBy(xpath = "//div[contains (@class, 'notification_status_error')]")
    private static WebElement errorNotification;

    public static void clickOnBuy() {
        buyButton.click();
    }

    public static void byCard(DataHelper.CardInfo info) {
        buyButton.click();
        cardNumber.sendKeys(info.getCardNumber());
    }

    public static void byCredit(DataHelper.CardInfo creditInfo) {
        creditButton.click();
        cardNumber.sendKeys(creditInfo.getCardNumber());
    }

    public static void setMonthYear(DataHelper.MonthYear data) {
        month.sendKeys(data.getMonth());
        year.sendKeys(data.getYear());
    }

    public static void setCardHolder(DataHelper.CardHolder holder) {
        cardHolder.sendKeys(holder.getName());
    }

    public static void setVerification(DataHelper.VerificationCode code) {
        verificationCode.sendKeys(code.getCode());
        //continueButton.click();
    }

    public static void clickOnContinue() {
        continueButton.click();
    }

    public static void findSuccessMessage() {
        successNotification.isDisplayed();
    }

    public static void findErrorMessage() {
        errorNotification.isDisplayed();
    }

    public static void findErrorNotification() {
        errorMessage.isDisplayed();
    }
}