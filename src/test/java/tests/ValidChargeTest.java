package tests;

import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;


public class ValidChargeTest extends BaseTest {


    protected String homeUrl = "https://sanbox.undostres.com.mx/";
    protected String amount = "10";



    @Test(priority = 0)
    public void dataCharge()  {
        HomePage homePage = new HomePage(driver);
        driver.get(homeUrl);
        driver.manage().window().maximize();
        homePage.doCelPhoneCharge("8465433546", "Telcel", amount);
        Assert.assertTrue(true);
    }

    @Test(priority = 1, dependsOnMethods = "dataCharge")
    public void payment() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.selectPaymentMethod("Tarjeta");
        homePage.selectCrediCardOption("Usar nueva tarjeta");
        homePage.writeCrediCard("4111111111111111","11","25","111");
        homePage.writeMail("test@test.com");
        homePage.clickPayButton();
        Assert.assertTrue(homePage.popupLogin());
    }
    @Test(priority = 2, dependsOnMethods = "payment")
    public void popupRegister() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.doPopupLogin("automationUDT1@gmail.com", "automationUDT123");
        Assert.assertEquals("¡Recarga Exitosa!",homePage.getSuccessFullMsg());
        Assert.assertEquals("$"+amount,homePage.getAmountCharge());
    }


}
