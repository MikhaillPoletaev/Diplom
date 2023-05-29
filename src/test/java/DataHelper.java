import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo firstCardInfo() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo secondCardInfo() {
        return new CardInfo("4444 4444 4444 4444");
    }

    public static CardInfo invalidCardInfo() {
        return new CardInfo("4444 4444 4444 444");
    }

    @Value
    public static class MonthYear {
        private String month;
        private String year;
    }

    public static MonthYear validData() {
        return new MonthYear("10", "25");
    }

    public static MonthYear invalidData() {
        return new MonthYear("10", "22");
    }

    public static MonthYear invalidMonthOne() {
        return new MonthYear("13", "25");
    }

    public static MonthYear invalidMonthTwo() {
        return new MonthYear("00", "25");
    }

    @Value
    public static class CardHolder {
        private String name;
    }

    public static CardHolder validName() {
        return new CardHolder("Aleksey Petrov");
    }

    public static CardHolder invalidName() {
        return new CardHolder("Алексей Петров");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode validCode() {
        return new VerificationCode("999");
    }

    public static VerificationCode invalidCode() {
        return new VerificationCode("99");
    }


}
