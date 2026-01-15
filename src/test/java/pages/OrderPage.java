package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName  = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address   = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro     = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phone     = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextBtn   = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstStep(String fName, String lName, String addr, String metroStation, String phoneNumber) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(address).sendKeys(addr);

        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(metroStation);
        driver.findElement(metro).sendKeys(Keys.ARROW_DOWN, Keys.ENTER); // выбор из подсказки
        driver.findElement(phone).sendKeys(phoneNumber);
    }

    public void clickNext() {
        driver.findElement(nextBtn).click();
    }
}
