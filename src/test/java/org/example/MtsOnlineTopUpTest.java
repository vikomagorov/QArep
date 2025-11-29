package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.MtsOnlineTopUpPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class MtsOnlineTopUpTest {

    private static WebDriver driver;
    private static MtsOnlineTopUpPage page;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        page = new MtsOnlineTopUpPage(driver);
    }

    @BeforeEach
    void openPage() {
        page.open();
        page.acceptCookiesIfPresent();
        page.waitForBlockVisible();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Проверить название указанного блока")
    void testBlockTitle() {
        String text = page.getBlockTitleText();
        String lower = text.toLowerCase();

        assertTrue(
                lower.contains("онлайн пополнение"),
                "Ожидаем, что заголовок содержит 'Онлайн пополнение': " + text
        );
        assertTrue(
                lower.contains("без комиссии"),
                "Ожидаем, что заголовок содержит 'без комиссии': " + text
        );
    }

    @Test
    @Order(2)
    @DisplayName("Проверить наличие логотипов платёжных систем")
    void testPaymentLogosPresence() {
        List<?> logos = page.getPaymentLogos();
        assertFalse(logos.isEmpty(), "Не обнаружены логотипы платёжных систем в блоке");
    }

    @Test
    @Order(3)
    @DisplayName("Проверить работу ссылки 'Подробнее о сервисе'")
    void testDetailsLink() {
        String urlBefore = driver.getCurrentUrl();

        page.clickDetailsLinkAndWait();

        String urlAfter = driver.getCurrentUrl();
        assertNotEquals(
                urlBefore,
                urlAfter,
                "После клика по 'Подробнее о сервисе' ожидаем переход на другую страницу"
        );

        String title = driver.getTitle().toLowerCase();
        assertTrue(
                title.contains("порядок") || title.contains("оплат"),
                "Заголовок страницы с деталями должен содержать слова про порядок/оплату. title=" + title
        );
    }


    @Test
    @Order(4)
    @DisplayName("«Услуги связи»: заполнить форму и дойти до шага оплаты")
    void testFillFormAndContinue() {
        page.selectServicesTab();
        page.fillServicesForm("297777777", "5");
        page.clickContinueInForm();
        page.waitForConfirmModal();
    }



    @Test
    @Order(5)
    @DisplayName("Проверить надписи в незаполненных полях всех вариантов оплаты")
    void testPlaceholdersForAllPaymentTypes() {

        page.selectServicesTab();
        List<String> servicesPlaceholders = page.getPlaceholdersInActiveForm();
        System.out.println("Услуги связи плейсхолдеры: " + servicesPlaceholders);

        assertFalse(
                servicesPlaceholders.isEmpty(),
                "Для 'Услуги связи' должны быть плейсхолдеры"
        );
        assertTrue(
                servicesPlaceholders.stream().anyMatch(s -> s.toLowerCase().contains("номер")),
                "В 'Услуги связи' должен быть плейсхолдер для номера телефона"
        );

        page.selectHomeInternetTab();
        List<String> internetPlaceholders = page.getPlaceholdersInActiveForm();
        System.out.println("Домашний интернет плейсхолдеры: " + internetPlaceholders);

        assertFalse(
                internetPlaceholders.isEmpty(),
                "Для 'Домашний интернет' должны быть плейсхолдеры в полях"
        );

        page.selectInstallmentTab();
        List<String> installmentPlaceholders = page.getPlaceholdersInActiveForm();
        System.out.println("Рассрочка плейсхолдеры: " + installmentPlaceholders);

        assertFalse(
                installmentPlaceholders.isEmpty(),
                "Для 'Рассрочка' должны быть плейсхолдеры в полях"
        );

        page.selectDebtTab();
        List<String> debtPlaceholders = page.getPlaceholdersInActiveForm();
        System.out.println("Задолженность плейсхолдеры: " + debtPlaceholders);

        assertFalse(
                debtPlaceholders.isEmpty(),
                "Для 'Задолженность' должны быть плейсхолдеры в полях"
        );
    }

    @Test
    @Order(6)
    @DisplayName("«Услуги связи»: проверка суммы, номера и полей карты в модальном окне")
    void testServicesFlowConfirmModal() {
        String testPhone = "297777777";
        String testAmount = "5";

        page.selectServicesTab();
        page.fillServicesForm(testPhone, testAmount);
        page.clickContinueInForm();

        page.waitForConfirmModal();

        String phoneText = page.getConfirmPhoneText();
        System.out.println("Текст с номером в модалке: " + testPhone);
        assertTrue(
                phoneText.contains("+375"),
                "В модальном окне должен отображаться код страны +375: " + testPhone
        );

        String amountText = page.getConfirmAmountText();
        System.out.println("Текст суммы в модалке: " + amountText);
        assertTrue(
                amountText.contains(testAmount),
                "В модальном окне должна отображаться корректная сумма: " + amountText
        );

        String buttonText = page.getConfirmButtonText();
        System.out.println("Текст на кнопке подтверждения: " + buttonText);
        assertTrue(
                buttonText.contains(testAmount),
                "На кнопке подтверждения должна быть указана сумма: " + buttonText
        );

        assertTrue(
                page.areCardFieldsPresent(),
                "В модальном окне должны быть поля для реквизитов карты: номер, срок, CVV, держатель"
        );

        assertTrue(
                page.arePaymentIconsInConfirmPresent(),
                "В модальном окне должны отображаться иконки платёжных систем (Visa/Mastercard/и т.п.)"
        );
    }
}
