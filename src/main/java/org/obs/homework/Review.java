package org.obs.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Review {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            // System.setProperty("webdriver.chrome.driver", "E:\\Selenium_Files\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            //System.setProperty("webdriver.gecko.driver", "E:\\Selenium_Files\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            //System.setProperty("webdriver.edge.driver", "E:\\Selenium_Files\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            try {
                throw new Exception("Invalid browser");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    //NAVIGATION COMMANDS
    //driver.navigate().to("http://demowebshop.tricentis.com/login");//used instead of driver.get
    //driver.navigate().back(); //Takes youback by one page on the browser's history.
    //driver.navigate().forward(); //Takes you forward by one page on the browser's history.
    //driver.navigate().refresh(); // to refresh the page


    // how to get title
    // String actTitle = driver.getTitle();
    // System.out.println(actTitle);

    // String currentUrl = driver.getCurrentUrl();
    // System.out.println(currentUrl);

    //sourse code
    // String pageSourse = driver.getPageSource();
    // System.out.println(pageSourse);

    @BeforeMethod
    public void setUp() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sreenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sreenshot, new File("./Screenshots/" + result.getName() + ".png"));
        }
        //driver.close();
    }

    @Test
    public void verifyColor() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement scrollbar = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select=new Select(scrollbar);
        select.selectByIndex(0);
        List<WebElement> colorList=select.getOptions();
        for (int i=0;i<colorList.size();i++){

        }
    }
    //div[@class='header-menu']//a
}
