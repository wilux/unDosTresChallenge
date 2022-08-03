package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10));
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver","webdriver/chromedriver.exe");
            //create chrome instance
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        //Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("Edge")){
            //set path to Edge driver.exe
            System.setProperty("webdriver.edge.driver","webdriver/msedgedriver.exe");
            //create Edge instance
            driver = new EdgeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }


    }

}
