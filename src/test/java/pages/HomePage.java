package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {

    WebDriver driver;


    private By inputCelPhone =  By.xpath("//*[@name='mobile']");

    private By selectOperator =  By.xpath("//*[@name='operator']");


    private By buttonNext =  By.xpath("(//button[text()='Siguiente'])[1]");


    private By inputCardNumber =  By.id("cardnumberunique");
    private By inputCardMonth =  By.xpath("//input[attribute::data-qa='mes-input']");
    private By inputCardYear =  By.xpath("//input[attribute::data-qa='expyear-input']");
    private By inputCardMCvv =  By.xpath("//input[attribute::data-qa='cvv-input']");
    private By inputMail=  By.cssSelector(".email");
    private By buttonPaymentMethod =  By.xpath("//button[@id='paylimit']/span[@class='PaymentMethod']");

    private By inputUsername =  By.id("usrname");
    private By inputPassword =  By.id("psw");
    private By frameReCaptcha = By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]");
    private By checkReCaptcha = By.id("recaptcha-anchor");
    private By buttonLogin = By.xpath("//*[@id='loginBtn']");



    public HomePage(WebDriver driver) {
        this.driver=driver;
    }



    private void writePhoneNumber(String number){

        driver.findElement(inputCelPhone).sendKeys(number);
    }

    private void selectOperator(String operator){
        driver.findElement(selectOperator).click();
        driver.findElement(By.xpath("//b[text()='"+operator+"']")).click();
    }

    private void selectAmount(String amount){
        driver.findElement(selectOperator).click();
        driver.findElement(By.cssSelector("[data-show='$"+amount+" (Recarga Saldo)'] b")).click();
    }


    private void nextButton(){
        driver.findElement(buttonNext).click();
    }

    public boolean modalPayMethod(){

        String paymentUrl = "payment.php";
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains(paymentUrl));
        return driver.getCurrentUrl().contains(paymentUrl);

    }
    public void selectPaymentMethod(String method){
        driver.findElement(By.xpath("//p[text()='"+method+"']")).click();
    }

    public void selectCrediCardOption(String option) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+option+"']")));
        driver.findElement(By.xpath("//span[text()='"+option+"']")).click();

//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[text()='Usar nueva tarjeta']")).click();


    }

    public void writeCrediCard(String numbers, String mm, String yy, String cvv){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inputCardNumber));
        driver.findElement(inputCardNumber).sendKeys(numbers);
        driver.findElement(inputCardMonth).sendKeys(mm);
        driver.findElement(inputCardYear).sendKeys(yy);
        driver.findElement(inputCardMCvv).sendKeys(cvv);
    }

    public boolean popupLogin(){
        By popup = By.xpath("//div[text()='Para pagar por favor Regístrate o Ingresa a tu cuenta']");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(popup));
        return driver.findElement(popup).isDisplayed();
    }

    public boolean successPay(){
        String urlSuccessful = "confirmation/success";
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains(urlSuccessful));
        return driver.getCurrentUrl().contains(urlSuccessful);
    }

    public void clickPayButton(){
        driver.findElement(buttonPaymentMethod).click();
    }

    public void writeMail(String mail){
        driver.findElement(inputMail).sendKeys(mail);

    }


    public void doPopupLogin(String userEmail, String password) throws InterruptedException {
        driver.findElement(inputUsername).sendKeys(userEmail);
        driver.findElement(inputPassword).sendKeys(password);
        driver.switchTo().frame(driver.findElement(frameReCaptcha));
        driver.findElement(checkReCaptcha).click();
        driver.switchTo().defaultContent();
        driver.findElement(buttonLogin).click();

    }

    public String getSuccessFullMsg(){
        ////*[@id="wzrk-confirm"]
        return driver.findElement(By.cssSelector(".visual-message")).getText();
    }

    public String getAmountCharge(){
        return driver.findElement(By.cssSelector(".confirmation-amount > div")).getText();
    }


    public void doCelPhoneCharge(String phoneNumber, String operator, String amount ){
        writePhoneNumber(phoneNumber);
        selectOperator(operator);
        selectAmount(amount);
        nextButton();
    }


}
