package tests;

import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;


public class ValidDataChargeTest extends BaseTest {


     /* "Como usuario de teléfono móvil quiero poder cargar mi línea
     desde la página de UnDosTres, y poder pagar con tarjeta de crédito para
     tener saldo más rápidamente. " */


    //Home Url
    protected String homeUrl = "https://sanbox.undostres.com.mx/";
    //Amount to Charge
    protected String amount = "10";
    HomePage homePage;

    /*Step 1*/
    /*First need to Complete data charge */
    @Test(priority = 0)
    public void dataCharge() {
        homePage = new HomePage(driver);
        //Go Home
        driver.get(homeUrl);
        //Maximize
        driver.manage().window().maximize();
        //Write Phone Cel Number, Operator and Amount to charge.
        homePage.doCelPhoneCharge("8465433546", "Telcel", amount);
        //Assert true if start Step 2 (open Pay Modal)
        Assert.assertTrue(homePage.modalPayMethod());
    }

    /*Step 2*/
    /*Then, if Step 1 is passed, I need complete credit card data*/
    @Test(priority = 1, dependsOnMethods = "dataCharge")
    public void payment() throws InterruptedException {
        //Select payment method
        homePage.selectPaymentMethod("Tarjeta");
        //Select new cred card option
        homePage.selectCreditCardOption("Usar nueva tarjeta");
        //Write credit card data, conformed by numbers, month, expired and cvv.
        homePage.writeCreditCard("4111111111111111", "11", "25", "111");
        //Write a mail
        homePage.writeMail("test@test.com");
        //Click on pay button
        homePage.clickPayButton();
        //Assert true if start Step 3 (open Login popup)
        Assert.assertTrue(homePage.popupLogin());
    }

    /*Step 3*/
    /*Finally, if Step 2 is passed, I need login and validate the charge*/
    @Test(priority = 2, dependsOnMethods = "payment")
    public void popupRegister() throws InterruptedException {
        //Write Login info and Submit with reCaptcha include
        homePage.doPopupLogin("automationUDT1@gmail.com", "automationUDT123");
        //Assert if I get "¡Recarga Exitosa!" and the amount charge is same to the solicited.
        Assert.assertEquals("¡Recarga Exitosa!", homePage.getSuccessFullMsg());
        Assert.assertEquals("$" + amount, homePage.getAmountCharge());
    }


}
