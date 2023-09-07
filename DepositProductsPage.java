package pages.logged_in_pages.deposit_pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

import static constants.TestConstants.SECONDS_5;

@Getter
public class DepositProductsPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'css-17iwmdz')]")
    private WebElement listOfDepositProducts;

    @FindBy(xpath = "//h5[text()='Депозитные продукты']")
    private WebElement depositProductsHeader;

    @FindBy(xpath = "//span[text()='Оптимальный']")
    private WebElement optimalDeposit;

    @FindBy(xpath = "//span[text()='Оптимальный']/parent::*/parent::*//*[text()='Подробнее']")
    private WebElement optimalDepositButton;

    @FindBy(xpath = "//span[text()='Надежный']")
    private WebElement reliableDeposit;

    @FindBy(xpath = "//span[text()='Надежный']/parent::*/parent::*//*[text()='Подробнее']")
    private WebElement reliableDepositButton;

    @FindBy(xpath = "//span[contains(text(),'ный')]")
    private List<WebElement> depositProductName;

    @FindBy(xpath = "//span[contains(text(),'%')]")
    private List<WebElement> depositProductInterestRate;

    @FindBy(xpath = "//span[starts-with(text(),\"от\")]")
    private List<WebElement> periodOfPlacementInMonths;

    @FindBy(xpath = "//span[contains(text(),\"1000 RUB\")]")
    private List<WebElement> minimumAmountToOpen;

    @FindBy(xpath = "//span[contains(text(),\"отзывной\")]")
    private List<WebElement> depositType;

    @FindBy(xpath = "//span[contains(text(),\"валюта\")]")
    private List<WebElement> depositValue;

    @FindBy(xpath = "//span[@data-testid=\"more\"]")
    private List<WebElement> linkMore;

    public DepositProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Отображение элемента")
    public boolean isElementDisplayed(WebElement element) {
        return waitForWebElementVisibilityInSeconds(element, 5).isDisplayed();
    }
    @Step("Отображение элемента с применением индекса")
    public boolean isElementDisplayed(List<WebElement> element, int index) {
        return waitForWebElementVisibilityInSeconds(element.get(index), 5).isDisplayed(); 
    }

    @Step("Получение информации о продукте")
    public String getDepositProductInfo(List<WebElement> webElements,int index) {
        return webElements.get(index).getText().trim();

    }

    @Step ("Клик по кнопке 'Подробнее'")
    public DepositProductInfoPage clickMoreButton( List<WebElement> webElement, int index) {
        waitForWebElementBeingClickableInSeconds(webElement.get(index), SECONDS_5).click();
        return new DepositProductInfoPage(driver);
    }



}
