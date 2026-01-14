package pages;

import org.openqa.selenium.By;

public class MainPageLocators {

    // Адрес главной страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // Кнопка принятия куки (если появляется)
    public static final By COOKIE_BUTTON = By.id("rcc-confirm-button");

    // Кнопка «Заказать» вверху страницы
    public static final By ORDER_BUTTON_TOP = By.xpath("(//button[text()='Заказать'])[1]");

    // Кнопка «Заказать» внизу страницы
    public static final By ORDER_BUTTON_BOTTOM = By.xpath("(//button[text()='Заказать'])[last()]");

    // Заголовок блока «Вопросы о важном» (чтобы проскроллить к нему)
    public static final By IMPORTANT_QUESTIONS_HEADER = By.xpath(".//*[text()='Вопросы о важном']");

    // Вопрос в аккордеоне по индексу (0..7)
    public static By question(int index) {
        return By.id("accordion__heading-" + index);
    }

    // Ответ в аккордеоне по индексу (0..7)
    public static By answer(int index) {
        return By.id("accordion__panel-" + index);
    }
}
