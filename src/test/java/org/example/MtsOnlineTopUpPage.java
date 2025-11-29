package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MtsOnlineTopUpPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By paySectionRoot = By.id("pay-section");
    private final By blockTitle = By.xpath("(//*[@id='pay-section']//h2)[1]");
    private final By paymentLogos = By.xpath("(//*[@id='pay-section']//ul)[2]//img");
    private final By detailsLink =
            By.xpath("//*[@id='pay-section']//a[contains(normalize-space(), 'Подробнее о сервисе')]");
    private final By formContainer = By.xpath("//*[@id='pay-section']//section/div");
    private final By cookieAgreeButton = By.id("cookie-agree");

    private final By tabServices = By.xpath(
            "//*[@id='pay-section']//*[normalize-space(text())='Услуги связи']"
    );

    private final By tabHomeInternet = By.xpath(
            "//*[@id='pay-section']//*[normalize-space(text())='Домашний интернет']"
    );

    private final By tabInstallment = By.xpath(
            "//*[@id='pay-section']//*[normalize-space(text())='Рассрочка']"
    );

    private final By tabDebt = By.xpath(
            "//*[@id='pay-section']//*[normalize-space(text())='Задолженность']"
    );

    private final By servicesPhoneInput  = By.xpath("//*[@id='connection-phone']");
    private final By servicesAmountInput = By.xpath("//*[@id='connection-sum']");
    private final By servicesEmailInput  = By.xpath("//*[@id='connection-email']");

    private final By homeInternetPhoneInput  = By.xpath("//*[@id='internet-phone']");
    private final By homeInternetAmountInput = By.xpath("//*[@id='internet-sum']");
    private final By homeInternetEmailInput  = By.xpath("//*[@id='internet-email']");

    private final By installmentNumberInput  = By.xpath("//*[@id='score-instalment']");
    private final By installmentAmountInput  = By.xpath("//*[@id='instalment-sum']");
    private final By installmentEmailInput   = By.xpath("//*[@id='instalment-email']");

    private final By debtNumberInput  = By.xpath("//*[@id='score-arrears']");
    private final By debtAmountInput  = By.xpath("//*[@id='arrears-sum']");
    private final By debtEmailInput   = By.xpath("//*[@id='arrears-email']");

    private final By continueButton = By.xpath(
            "//*[@id='pay-section']//button[contains(.,'Продолжить') or contains(.,'продолжить')]"
    );

    private final By confirmModal = By.xpath("//app-card-page");

    private final By confirmPhoneText = By.xpath(
            "//*[contains(text(),'Номер') and contains(text(),'375')]"
    );

    private final By confirmAmountText = By.xpath(
            "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[1]/div[1]/span"
    );


    private final By cardNumberField = By.xpath("//*[@id='cc-number']");
    private final By cardExpiryField = By.xpath("(//app-card-page//app-card-input//app-input//input)[1]");
    private final By cardCvvField    = By.xpath("//input[@formcontrolname='cvc']");
    private final By cardHolderField = By.xpath("//input[@formcontrolname='holder']");

    private final By paymentSystemIconsConfirm = By.xpath(
            "//div[contains(@class,'cards-brands__container')]//img"
    );

    public MtsOnlineTopUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get("https://www.mts.by/");
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieAgreeButton));
            btn.click();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    public void waitForBlockVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(paySectionRoot));
    }

    public String getBlockTitleText() {
        WebElement titleEl = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
        return titleEl.getText().trim();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    public void clickDetailsLinkAndWait() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(detailsLink));
        String href = link.getAttribute("href");
        if (href == null || href.isEmpty()) {
            throw new IllegalStateException("Ссылка 'Подробнее о сервисе' без href");
        }
        link.click();
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public void selectServicesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabServices)).click();
        waitForActiveForm();
    }

    public void selectHomeInternetTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabHomeInternet)).click();
        waitForActiveForm();
    }

    public void selectInstallmentTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabInstallment)).click();
        waitForActiveForm();
    }

    public void selectDebtTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabDebt)).click();
        waitForActiveForm();
    }

    private WebElement getActiveForm() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formContainer));
    }

    private void waitForActiveForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(formContainer));
    }

    public List<String> getPlaceholdersInActiveForm() {
        WebElement form = getActiveForm();
        List<WebElement> inputs = form.findElements(By.xpath(".//input"));

        List<String> res = new ArrayList<>();
        for (WebElement input : inputs) {
            String ph = input.getAttribute("placeholder");
            if (ph != null && !ph.trim().isEmpty()) {
                res.add(ph.trim());
            }
        }
        return res;
    }

    public void fillServicesForm(String phone, String amount) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(servicesPhoneInput));
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        try {
            WebElement amountInputEl = wait.until(ExpectedConditions.visibilityOfElementLocated(servicesAmountInput));
            amountInputEl.clear();
            amountInputEl.sendKeys(amount);
        } catch (NoSuchElementException ignored) {
        }
    }

    public void clickContinueInForm() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        btn.click();
    }

    public void waitForConfirmModal() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        longWait.until(d -> {
            if (!d.findElements(By.xpath("//app-card-page")).isEmpty()) {
                return true;
            }

            if (!d.findElements(By.xpath("//*[contains(text(),'Оплата:') and contains(text(),'Услуги связи')]")).isEmpty()) {
                return true;
            }

            if (!d.findElements(By.xpath("//*[contains(text(),'Оплата')]")).isEmpty()) {
                return true;
            }

            return false;
        });
    }
    public String getConfirmPhoneText() {
        try {
            return driver.findElement(confirmPhoneText).getText().trim();
        } catch (NoSuchElementException e) {
            WebElement el = driver.findElement(
                    By.xpath("//*[contains(text(),'+375') or contains(text(),'297') or contains(text(),'777777')]")
            );
            return el.getText().trim();
        }
    }


    private By paymentIframe = By.cssSelector("iframe[src*='checkout.bepaid.by/widget_v2']");

    public String getConfirmAmountText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));

        WebElement amountElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(), 'BYN')]")
                )
        );

        String text = amountElement.getText();
        System.out.println("Текст суммы в iframe: " + text);

        driver.switchTo().defaultContent();

        return text;
    }


    public String getConfirmButtonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(.,'BYN') or .//span[contains(.,'BYN')]]")
                )
        );

        String text = button.getText().trim();
        System.out.println("Текст на кнопке в iframe: " + text);

        driver.switchTo().defaultContent();

        return text;
    }


    public boolean areCardFieldsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));

        boolean num    = !driver.findElements(cardNumberField).isEmpty();
        boolean exp    = !driver.findElements(cardExpiryField).isEmpty();
        boolean cvv    = !driver.findElements(cardCvvField).isEmpty();
        boolean holder = !driver.findElements(cardHolderField).isEmpty();

        driver.switchTo().defaultContent();

        return num && exp && cvv && holder;
    }


    public boolean arePaymentIconsInConfirmPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));

        boolean present = !driver.findElements(paymentSystemIconsConfirm).isEmpty();

        driver.switchTo().defaultContent();

        return present;
    }

    public String getPlaceholdersPretty() {
        return getPlaceholdersInActiveForm().stream().collect(Collectors.joining(" | "));
    }

    public By getInstallmentNumberInput() {
        return installmentNumberInput;
    }

    public By getDebtNumberInput() {
        return debtNumberInput;
    }
}
