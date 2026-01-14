package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(MainPageLocators.URL);
    }

    public void acceptCookiesIfExists() {
        try {
            driver.findElement(MainPageLocators.COOKIE_BUTTON).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    public void clickOrderTop() {
        driver.findElement(MainPageLocators.ORDER_BUTTON_TOP).click();
    }

    public void clickOrderBottom() {
        WebElement block = driver.findElement(MainPageLocators.IMPORTANT_QUESTIONS_HEADER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", block);
        driver.findElement(MainPageLocators.ORDER_BUTTON_BOTTOM).click();
    }

    public String clickQuestionAndGetAnswerText(int index) {
        WebElement q = driver.findElement(MainPageLocators.question(index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", q);
        q.click();

        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(MainPageLocators.answer(index)))
                .getText();
    }
}
