package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private final WebDriver driver;

    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By periodDropdown = By.className("Dropdown-control");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By black = By.id("black");
    private final By grey  = By.id("grey");

    private final By orderBtn = By.xpath("//div[contains(@class,'Order_Buttons')]//button[normalize-space()='Заказать']");
    private final By confirmModal = By.xpath("//div[contains(@class,'Order_Modal')]");
    private final By yesBtn = By.xpath("//div[contains(@class,'Order_Modal')]//button[normalize-space()='Да']");



    private final By successHeader = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillSecondStep(String deliveryDate, String rentPeriodText, boolean wantBlack, boolean wantGrey, String courierComment) {
        driver.findElement(date).sendKeys(deliveryDate);
        driver.findElement(date).sendKeys(Keys.ENTER);

        driver.findElement(periodDropdown).click();
        driver.findElement(By.xpath(".//div[contains(@class,'Dropdown-option') and text()='" + rentPeriodText + "']")).click();

        if (wantBlack) driver.findElement(black).click();
        if (wantGrey) driver.findElement(grey).click();

        driver.findElement(comment).sendKeys(courierComment);
    }

    public String submitAndGetSuccessText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(orderBtn)).click(); // клик по "Заказать"
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmModal)); // дождались модалку
        wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click(); // клик "Да"

        return wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader)).getText();
    }
}

