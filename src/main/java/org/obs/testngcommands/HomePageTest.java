package org.obs.testngcommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePageTest {

    WebDriver driver;

    public void testInitialise( String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else {
            try {
                throw new Exception("invalid browsername");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "url"})
    public void setUp(String browserName, String baseUrl) {
        testInitialise(browserName);
        driver.get(baseUrl);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./ScreenShots/" + result.getName() + ".png"));
        }
        driver.close();
    }

    @Test(groups = {"sanity"})
    public void verifyHomePageTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Page Title");
    }

    @Test(dataProvider = "loginData",groups = {"smoke"})
    public void verifyLogin(String uName, String pWord) {
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys(uName);
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys(pWord);
        WebElement checkin = driver.findElement(By.cssSelector("input[id='RememberMe']"));
        checkin.click();
        WebElement loginbutton = driver.findElement(By.cssSelector("input[value='Log in']"));
        loginbutton.click();
        WebElement account = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID = account.getText();
        String expectedemailID = uName;
        Assert.assertEquals(actualemailID, expectedemailID, "User login Failed");
    }

    @DataProvider(name = "loginData")
    public Object[][] userCredentials(){
        Object[][] data = new Object[2][2];
        data[0][0]= "nandhamg5078@gmail.com";
        data[0][1]= "abc12345";
        data[1][0]="jackbatson@jackbatson.com";
        data[1][1]="123456";
        return data;
    }
}
