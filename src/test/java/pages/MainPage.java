package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    // URL
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы
    private static final By COOKIE_BUTTON = By.id("rcc-confirm-button");
    private static final By ORDER_BUTTON_TOP = By.xpath("(//button[text()='Заказать'])[1]");
    private static final By ORDER_BUTTON_BOTTOM = By.xpath("(//button[text()='Заказать'])[last()]");
    private static final By IMPORTANT_QUESTIONS_HEADER = By.xpath(".//*[text()='Вопросы о важном']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By question(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By answer(int index) {
        return By.id("accordion__panel-" + index);
    }

    public void open() {
        driver.get(URL);
    }

    public void acceptCookiesIfExists() {
        try {
            driver.findElement(COOKIE_BUTTON).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    public void clickOrderTop() {
        driver.findElement(ORDER_BUTTON_TOP).click();
    }

    public void clickOrderBottom() {
        WebElement block = driver.findElement(IMPORTANT_QUESTIONS_HEADER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", block);
        driver.findElement(ORDER_BUTTON_BOTTOM).click();
    }

    public String clickQuestionAndGetAnswerText(int index) {
        WebElement q = driver.findElement(question(index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", q);
        q.click();

        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answer(index)))
                .getText();
    }
}
