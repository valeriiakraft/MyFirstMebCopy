package epic_5_deposit.us_5_4_viewing_available_deposits;

import JDBC.DebitServiceDatabaseHandler;
import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.logged_in_pages.UserMainPage;
import pages.logged_in_pages.deposit_pages.DepositProductInfoPage;
import pages.logged_in_pages.deposit_pages.DepositProductsPage;
import pages.logging_in_pages.AuthorizationForm;
import pages.most_common_page_elements.Sidebar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constants.TestConstants.*;
import static constants.TestConstants.NOT_DISPLAYED;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Epic-5 Депозиты (WEB)")
@Story("US 5.4 Просмотр доступных для оформления депозитов")
public class ViewingAvailableDepositsTests extends BaseTest {
    private static AuthorizationForm authorizationForm;
    private static Sidebar sidebar;
    private static UserMainPage userMainPage;
    private static DepositProductsPage depositProductsPage;
    private static DepositProductInfoPage depositProductInfoPage;
    private static DebitServiceDatabaseHandler debitServiceDatabaseHandler;

    @BeforeAll
    public static void setBeforeAll() {
        //  System.setProperty("headless", "true");
        WebDriver driver = driverActions.getDriver();
        authorizationForm = new AuthorizationForm(driver);
        userMainPage = new UserMainPage(driver);
        sidebar = new Sidebar(driver);
        depositProductsPage = new DepositProductsPage(driver);
        depositProductInfoPage = new DepositProductInfoPage(driver);
    }

    @BeforeEach
    public void setBeforeEach() {
        authorizationForm.openSwagger();
        authorizationForm.startAFinny();
        debitServiceDatabaseHandler = DebitServiceDatabaseHandler.getInstance();
    }

    @Test
    @DisplayName("C6314955 Возможность просмотреть доступные депозитные продукты ")// позже
    public void viewDepositsProductsTest() {
        authorizationForm.clickByPassportButton();
        authorizationForm.enterPassportField(PASSPORT_NUMBER_USER_WITH_CREDITS);
        authorizationForm.enterPasswordField(PASSWORD_USER_WITH_CREDITS);
        authorizationForm.clickSignInButtonActive();
        sidebar.clickDepositsButton();
        sidebar.click(sidebar.getDepositProductButton());
        List<String> expectedListOfDepositProducts =new ArrayList<>();
        expectedListOfDepositProducts.add("Оптимальный");
        expectedListOfDepositProducts.add("Надежный");
        List<String> actualListOfDepositProducts = debitServiceDatabaseHandler.getListOfDepositProducts();
        // Сменить версию
        //Выгрузить из БД список продуктов
        //Сравнить с физическим списком

       Collections.sort(expectedListOfDepositProducts);
       Collections.sort(actualListOfDepositProducts);
        assertTrue(depositProductsPage.isElementDisplayed(depositProductsPage.getListOfDepositProducts()),
                "Список депозитных продуктов" + NOT_DISPLAYED);
        assertEquals(expectedListOfDepositProducts, actualListOfDepositProducts,
                "Список депозитных продуктов" + DOES_NOT_MATCH_WITH_EXPECTED);



    }


    @Test
    @DisplayName("C6429549 Краткая информация по депозитному продукту ") //готов
    public void briefInformationAboutDepositProductTest() {
        authorizationForm.clickByPassportButton();
        authorizationForm.enterPassportField(PASSPORT_NUMBER_USER_WITH_CREDITS);
        authorizationForm.enterPasswordField(PASSWORD_USER_WITH_CREDITS);
        authorizationForm.clickSignInButtonActive();
        sidebar.clickDepositsButton();
        sidebar.click(sidebar.getDepositProductButton());

        assertTrue(depositProductsPage.isElementDisplayed(depositProductsPage.getListOfDepositProducts()),
                "Список депозитных продуктов" + NOT_DISPLAYED);

        assertAll("Краткая информация по продукту типа 'Отзывной'",
                () -> assertEquals("Оптимальный", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositProductName(), 0),
                        "Название" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("до 4,5%", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositProductInterestRate(), 0),
                        "Процентная ставка" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("от 3 до 12", depositProductsPage.getDepositProductInfo(depositProductsPage.getPeriodOfPlacementInMonths(), 0),
                        "Срок размещения в месяцах" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("1000 RUB", depositProductsPage.getDepositProductInfo(depositProductsPage.getMinimumAmountToOpen(), 0),
                        "Минимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("тип депозита - отзывной", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositType(), 0),
                        "Тип депозита" +DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("валюта - RUB", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositValue(), 0),
                        "Валюта депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(depositProductsPage.isElementDisplayed(depositProductsPage.getLinkMore(),0),
                        "Ссылка 'Подробнее'" + NOT_DISPLAYED)
        );

        assertAll("Краткая информация по продукту типа 'Безотзывной'",
                () -> assertEquals("Надежный", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositProductName(), 1),
                        "Название" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("до 6,5%", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositProductInterestRate(), 1),
                        "Процентная ставка" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("от 3 до 12", depositProductsPage.getDepositProductInfo(depositProductsPage.getPeriodOfPlacementInMonths(), 1),
                        "Срок размещения в месяцах" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("1000 RUB", depositProductsPage.getDepositProductInfo(depositProductsPage.getMinimumAmountToOpen(), 1),
                        "Минимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("тип депозита - безотзывной", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositType(), 1),
                        "Тип депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("валюта - RUB", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositValue(), 1),
                        "Минимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("валюта - RUB", depositProductsPage.getDepositProductInfo(depositProductsPage.getDepositValue(), 1),
                        "Валюта депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(depositProductsPage.isElementDisplayed(depositProductsPage.getLinkMore(),1),
                        "Ссылка 'Подробнее'" + NOT_DISPLAYED)
        );
    }


    @Test
    @DisplayName("C6314956 Подробные условия по депозитному продукту") //готов
    public void detailedConditionsForDepositProductTest() {
        authorizationForm.clickByPassportButton();
        authorizationForm.enterPassportField(PASSPORT_NUMBER_USER_WITH_CREDITS);
        authorizationForm.enterPasswordField(PASSWORD_USER_WITH_CREDITS);
        authorizationForm.clickSignInButtonActive();
        sidebar.clickDepositsButton();
        sidebar.click(sidebar.getDepositProductButton());
        depositProductsPage.clickMoreButton(depositProductsPage.getLinkMore(), 0);
        assertAll("Подробные условия по депозитному продукту'Отзывной'",
                () -> assertEquals("Оптимальный", depositProductInfoPage.getText(depositProductInfoPage.getNameDeposit()), "Название депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("1 000,00 RUB", depositProductInfoPage.getText(depositProductInfoPage.getMinimumSum()), "Минимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("2 000 000 000,00 RUB", depositProductInfoPage.getText(depositProductInfoPage.getMaximumSum()), "Максимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("2%",depositProductInfoPage.getText(depositProductInfoPage.getMinimumRate()), "Минимальная процентная ставка" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("4,5%",depositProductInfoPage.getText(depositProductInfoPage.getMaximumRate()), "Максимальная процентная ставка" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("3 месяца",depositProductInfoPage.getText(depositProductInfoPage.getMinimumTime()), "Минимальный срок" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("12 месяцев",depositProductInfoPage.getText(depositProductInfoPage.getMaximumTime()), "Максимальный срок" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Отзывной",depositProductInfoPage.getText(depositProductInfoPage.getDepositType()), "Тип депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("RUB",depositProductInfoPage.getText(depositProductInfoPage.getDepositValue()), "Валюта" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Предусмотрено",depositProductInfoPage.getText(depositProductInfoPage.getPossibilityOfEarlyWithdrawal()), "Возможность досрочного снятия" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("0.00001%",depositProductInfoPage.getText(depositProductInfoPage.getEarlyWithdrawalInterestRate()), "Процентная ставка при досрочном отзыве" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Предусмотрено",depositProductInfoPage.getText(depositProductInfoPage.getCapitalization()), "Капитализация" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Предусмотрено",depositProductInfoPage.getText(depositProductInfoPage.getReplenishment()), "Возможность пополнения" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(depositProductInfoPage.isElementDisplayed(depositProductInfoPage.getOrderButton()),"Кнопка 'Заказать'" + NOT_DISPLAYED)

               );


    }

    @Test
    @DisplayName("C6314958 Возможность вернуться на просмотр доступных депозитов")// готов
    public void returnToViewAvailableDepositsTest() {
        authorizationForm.clickByPassportButton();
        authorizationForm.enterPassportField(PASSPORT_NUMBER_USER_WITH_CREDITS);
        authorizationForm.enterPasswordField(PASSWORD_USER_WITH_CREDITS);
        authorizationForm.clickSignInButtonActive();
        sidebar.clickDepositsButton();
        sidebar.click(sidebar.getDepositProductButton());
        depositProductsPage.clickMoreButton(depositProductsPage.getLinkMore(), 1);

        assertAll("Подробные условия по депозитному продукту'Безотзывной'",
                () -> assertEquals("Надежный", depositProductInfoPage.getText(depositProductInfoPage.getNameDeposit()), "Название депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("1 000,00 RUB", depositProductInfoPage.getText(depositProductInfoPage.getMinimumSum()), "Минимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("2 000 000 000,00 RUB", depositProductInfoPage.getText(depositProductInfoPage.getMaximumSum()), "Максимальная сумма" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("3,5%", depositProductInfoPage.getText(depositProductInfoPage.getMinimumRate()), "Минимальная процентная ставка" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("6,5%", depositProductInfoPage.getText(depositProductInfoPage.getMaximumRate()), "Максимальная процентная ставка" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("3 месяца", depositProductInfoPage.getText(depositProductInfoPage.getMinimumTime()), "Минимальный срок" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("12 месяцев", depositProductInfoPage.getText(depositProductInfoPage.getMaximumTime()), "Максимальный срок" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Безотзывной", depositProductInfoPage.getText(depositProductInfoPage.getDepositType()), "Тип депозита" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("RUB", depositProductInfoPage.getText(depositProductInfoPage.getDepositValue()), "Валюта" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Не предусмотрено", depositProductInfoPage.getText(depositProductInfoPage.getPossibilityOfEarlyWithdrawal()), "Возможность досрочного снятия" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Предусмотрено", depositProductInfoPage.getText(depositProductInfoPage.getCapitalization()), "Капитализация" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Предусмотрено", depositProductInfoPage.getText(depositProductInfoPage.getReplenishment()), "Возможность пополнения" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(depositProductInfoPage.isElementDisplayed(depositProductInfoPage.getOrderButton()), "Кнопка 'Заказать'" + NOT_DISPLAYED)
        );
        depositProductInfoPage.back();
                      assertTrue(depositProductsPage.isElementDisplayed(depositProductsPage.getDepositProductsHeader()), "Страница 'Депозитные продукты'" + NOT_DISPLAYED);
    }

   @AfterEach
    public void setAfterEach() {
        userMainPage.clickExitButton();
    }

    @AfterAll
    public static void setAfterAll() {
        driverActions.quitDriver();
    }
}

