package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;
import pages.OrderPage;
import pages.RentPage;

public class OrderFlowTest extends BaseUiTest {

    private void createOrderFrom(MainPage mainPage, boolean topButton) {
        mainPage.open();
        mainPage.acceptCookiesIfExists();

        if (topButton) {
            mainPage.clickOrderTop();
        } else {
            mainPage.clickOrderBottom();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstStep(
                "Иван", "Иванов", "Москва, Тверская 1", "Черкизовская", "+79990000001"
        );
        orderPage.clickNext();

        RentPage rentPage = new RentPage(driver);
        rentPage.fillSecondStep(
                "20.01.2026", "сутки", true, false, "Позвонить за 10 минут"
        );

        String success = rentPage.submitAndGetSuccessText();
        Assert.assertTrue("Нет текста об успешном создании заказа. Текст: " + success,
                success.contains("Заказ"));
    }

    @Test
    public void orderFromTopButtonShouldBeCreated() {
        MainPage mainPage = new MainPage(driver);
        createOrderFrom(mainPage, true);
    }

    @Test
    public void orderFromBottomButtonShouldBeCreated() {
        MainPage mainPage = new MainPage(driver);
        createOrderFrom(mainPage, false);
    }
}
