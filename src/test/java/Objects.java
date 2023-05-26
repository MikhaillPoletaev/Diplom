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
    private static WebElement errorMessage;

    @FindBy(xpath = "//div[contains (@class, 'notification_status_ok')]")
    private static WebElement successNotification;

    @FindBy(xpath = "//div[contains (@class, 'notification_status_error')]")
    private static WebElement errorNotification;

    public Objects() {
        driver.get("http://localhost:8080/");
        PageFactory.initElements(driver, this);
    }

    public Objects byCard(DataHelper.CardInfo info){
        buyButton.click();
        cardNumber.sendKeys(info.getCardNumber());
        return this;
    }

    public Objects byCredit(DataHelper.CardInfo creditInfo){
        creditButton.click();
        cardNumber.sendKeys(creditInfo.getCardNumber());
        return this;
    }

    public Objects setMonthYear(DataHelper.MonthYear data){
        month.sendKeys(data.getMonth());
        year.sendKeys(data.getYear());
        return this;
    }

    public Objects setCardHolder(DataHelper.CardHolder holder){
        cardHolder.sendKeys(holder.getName());
        return this;
    }

    public Objects setVerification(DataHelper.VerificationCode code){
        verificationCode.sendKeys(code.getCode());
        return this;
    }

    public static void findSuccessMessage(){
        successNotification.isDisplayed();
    }

    public static void findErrorMessage(){
        errorNotification.isDisplayed();
    }

    public static void findErrorNotification(){
        errorMessage.isDisplayed();
    }
}
