package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {

    WebDriver driver;


    private By inputCelPhone = By.xpath("//*[@name='mobile']");
    private By selectOperator = By.xpath("//*[@name='operator']");
    private By buttonNext = By.xpath("(//button[text()='Siguiente'])[1]");
    private By inputCardNumber = By.cssSelector("#cardnumberunique");
    private By inputCardMonth = By.xpath("//input[attribute::data-qa='mes-input']");
    private By inputCardYear = By.xpath("//input[attribute::data-qa='expyear-input']");
    private By inputCardMCvv = By.xpath("//input[attribute::data-qa='cvv-input']");
    private By inputMail = By.cssSelector(".email");
    private By buttonPaymentMethod = By.xpath("//button[@id='paylimit']/span[@class='PaymentMethod']");
    private By inputUsername = By.id("usrname");
    private By inputPassword = By.id("psw");
    private By frameReCaptcha = By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]");
    private By checkReCaptcha = By.id("recaptcha-anchor");
    private By buttonLogin = By.xpath("(//form[@method='post']//button)[1]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    private void writePhoneNumber(String number) {
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inputCelPhone));
        firstResult.sendKeys(number);
    }

    private void selectOperator(String operator) {
        driver.findElement(selectOperator).click();

        driver.findElement(By.xpath("//b[text()='" + operator + "']")).click();

    }

    private void selectAmount(String amount) {
        driver.findElement(By.cssSelector("[data-show='$" + amount + " (Recarga Saldo)'] b")).click();
    }


    private void nextButton() {
        driver.findElement(buttonNext).click();
    }

    public boolean modalPayMethod() {

        String paymentUrl = "payment.php";
        Boolean firstResult = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains(paymentUrl));
        return firstResult;

    }


    public void selectPaymentMethod(String method) {
        WebElement payMethod = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='" + method + "']")));
        payMethod.click();
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void selectCreditCardOption(String option) throws InterruptedException {


        driver.findElement(By.xpath("//span[text()='" + option + "']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + option + "']")).click();

    }

    public void writeCreditCard(String numbers, String mm, String yy, String cvv) throws InterruptedException {
        WebElement cardNumbers = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(inputCardNumber));
        cardNumbers.sendKeys(numbers);
        Thread.sleep(5000);
        driver.findElement(inputCardMonth).sendKeys(mm);
        Thread.sleep(200);
        driver.findElement(inputCardYear).sendKeys(yy);
        Thread.sleep(200);
        driver.findElement(inputCardMCvv).sendKeys(cvv);
    }

    public boolean popupLogin() {
        By popup = By.xpath("//div[text()='Para pagar por favor Regístrate o Ingresa a tu cuenta']");
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(popup));
        return firstResult.isDisplayed();
    }


    public void clickPayButton() {
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(buttonPaymentMethod));
        firstResult.click();
    }

    public void writeMail(String mail) {
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(inputMail));
        firstResult.sendKeys(mail);


    }


    public void doPopupLogin(String userEmail, String password) throws InterruptedException {
        WebElement inputMail = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inputUsername));
        inputMail.sendKeys(userEmail);
        driver.findElement(inputPassword).sendKeys(password);
        driver.switchTo().frame(driver.findElement(frameReCaptcha));
        Thread.sleep(2000);
        driver.findElement(checkReCaptcha).click();
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        driver.findElement(buttonLogin).click();

    }

    public boolean successPay() {
        String urlSuccessful = "confirmation/success";
        Boolean firstResult = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains(urlSuccessful));
        return firstResult;
    }

    public String getSuccessFullMsg() {

        WebElement msg = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".visual-message")));
        return msg.getText();
    }

    public String getAmountCharge() {
        return driver.findElement(By.cssSelector(".confirmation-amount > div")).getText();
    }


    public void doCelPhoneCharge(String phoneNumber, String operator, String amount) {
        writePhoneNumber(phoneNumber);
        selectOperator(operator);
        selectAmount(amount);
        nextButton();
    }


}
