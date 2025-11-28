package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MtsOnlineTopUpTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String baseUrl = "https://mts.by";

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterAll
    static void teardownAll() {
        if (driver != null) driver.quit();
    }

    @BeforeEach
    void openHome() {
        driver.get("https://mts.by");
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By cookieBtn = By.id("cookie-agree"); // или By.cssSelector(".cookie__ok")
            WebElement accept = shortWait.until(ExpectedConditions.elementToBeClickable(cookieBtn));
            accept.click();
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(cookieBtn));
        } catch (TimeoutException ignored) {
        }
        ;
    }

    @Test
    @Order(1)
    @DisplayName("Проверить название указанного блока")
    void testBlockTitle() {
        By blockTitle = By.xpath("(//*[@id='pay-section']//h2)[1]");
        WebElement titleEl = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
        assertNotNull(titleEl, "Заголовок блока не найден");
        String text = titleEl.getText().trim();
        assertTrue(text.toLowerCase().contains("онлайн пополнение"), "Текст заголовка содержит 'онлайн пополнение'");
        assertTrue(text.toLowerCase().contains("без комиссии"), "Текст заголовка содержит 'без комиссии'");
    }

    @Test
    @Order(2)
    @DisplayName("Проверить наличие логотипов платёжных систем")
    void testPaymentLogosPresence() {
        By logosContainer = By.xpath("(//*[@id='pay-section']//ul)[2]//img");
        List<WebElement> logos = driver.findElements(logosContainer);
        assertFalse(logos.isEmpty(), "Не обнаружены логотипы платежных систем в блоке");
        for (WebElement img : logos) {
            assertTrue(img.isDisplayed(), "Логотип должен быть видимым");
            String src = img.getAttribute("src");
            assertNotNull(src);
            assertFalse(src.trim().isEmpty());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Проверить работу ссылки 'Подробнее о сервисе'")
    void testDetailsLink() {
        By detailsLink = By.xpath("(//*[@id='pay-section']//a)[1]");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(detailsLink));
        String href = link.getAttribute("href");
        assertNotNull(href, "У ссылки нет href");

        link.click();

        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        String pageTitle = driver.getTitle().toLowerCase();
        assertTrue(pageTitle.contains("порядок") || pageTitle.contains("оплаты"), "Заголовок страницы деталей содержит ключевые слова");
    }

    @Test
    @Order(4)
    @DisplayName("Заполнить поля и проверить работу кнопки «Продолжить»")
    void testFillFormAndContinue() {
        By formContainer = By.xpath("//*[@id='pay-section']//section/div");
        WebElement form = wait.until(
                ExpectedConditions.visibilityOfElementLocated(formContainer)
        );
        assertNotNull(form, "Форма пополнения не найдена в блоке pay-section");

        try {
            WebElement select = form.findElement(By.xpath(".//select"));
            Select s = new Select(select);
            s.selectByVisibleText("Услуги связи");
        } catch (NoSuchElementException | ElementNotInteractableException e) {

            List<WebElement> candidates =
                    form.findElements(By.xpath(".//*[contains(normalize-space(), 'Услуги связи')]"));
            if (!candidates.isEmpty()) {
                candidates.get(0).click();
            }
        }


        WebElement phoneInput = null;


        List<WebElement> inputs = form.findElements(By.xpath(".//input"));
        for (WebElement inp : inputs) {
            String type = (inp.getAttribute("type") == null ? "" : inp.getAttribute("type")).toLowerCase();
            String name = (inp.getAttribute("name") == null ? "" : inp.getAttribute("name")).toLowerCase();
            String ph   = (inp.getAttribute("placeholder") == null ? "" : inp.getAttribute("placeholder")).toLowerCase();

            if (type.contains("tel") || name.contains("phone") || ph.contains("номер")) {
                phoneInput = inp;
                break;
            }
        }

        if (phoneInput == null) {
            phoneInput = form.findElement(By.xpath(".//input[@type='text' or not(@type)]"));
        }
        assertNotNull(phoneInput, "Поле ввода телефона не найдено");

        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        try {
            WebElement amount = form.findElement(By.xpath(".//input[@id='connection-sum']"));
            amount.clear();
            amount.sendKeys("5");
        } catch (NoSuchElementException ignored) {
        }

        WebElement continueBtn = null;
        List<WebElement> buttons = form.findElements(
                By.xpath(".//button | .//input[@type='submit']")
        );
        for (WebElement b : buttons) {
            String t = (b.getText() == null ? "" : b.getText()).toLowerCase();
            String v = (b.getAttribute("value") == null ? "" : b.getAttribute("value")).toLowerCase();
            if (t.contains("продолжить") || v.contains("продолжить")
                    || t.contains("continue") || v.contains("continue")) {
                continueBtn = b;
                break;
            }
        }
        assertNotNull(continueBtn, "Кнопка 'Продолжить' не найдена");

        String urlBefore = driver.getCurrentUrl();
        continueBtn.click();

        boolean ok = wait.until(d -> {
            if (!d.getCurrentUrl().equals(urlBefore)) {
                return true;
            }
            List<WebElement> confirm = d.findElements(By.xpath(
                    "//*[contains(text(),'Подтвержд') or " +
                            "contains(text(),'Подтвердите') or " +
                            "contains(text(),'Оплата')]"
            ));
            return !confirm.isEmpty();
        });

        assertTrue(ok, "После нажатия 'Продолжить' не произошло перехода к шагу подтверждения/оплаты");
    }
}
