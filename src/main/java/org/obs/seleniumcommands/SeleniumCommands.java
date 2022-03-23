package org.obs.seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


//import java.awt.*;

public class SeleniumCommands {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "E:\\Selenium_Files\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "E:\\Selenium_Files\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", "E:\\Selenium_Files\\msedgedriver.exe");
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

    @BeforeMethod
    public void setUp() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void verifyHomepageTitle() {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid PageTitle");
    }

    @Test
    public void verifyLogin() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input.email"));
        email.sendKeys("nandhamg5078@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input[type='Password']"));
        password.sendKeys("sagarmala943");
        WebElement remind = driver.findElement(By.cssSelector("input#RememberMe"));
        remind.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement acc = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUsername = acc.getText();
        String expectedUsername = "nandhamg5078@gmail.com";
        Assert.assertEquals(actualUsername, expectedUsername, "User login Failed");
    }

    @Test
    public void verifyClear() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input.email"));
        email.sendKeys("nandhamg5078@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input[type='Password']"));
        password.sendKeys("sagarmala943");
        email.clear();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement emailmessage = driver.findElement(By.xpath("//div[@class='validation-summary-errors']/child::span"));
        String actualemailmessage = emailmessage.getText();
        String expectedemailmessage = "Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actualemailmessage, expectedemailmessage, "Not correct");
    }

    @Test
    public void verifyWebElementCommands() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        Dimension dimension = submit.getSize();
        int height = dimension.height;
        int width = dimension.width;
        System.out.println("height is " + height);
        System.out.println("width is " + width);
        Point point = submit.getLocation();
        int x = point.x;
        int y = point.y;
        System.out.println("x cordinate = " + x);
        System.out.println("y cordinate = " + y);
        String actualSubmitButtonTest = submit.getAttribute("value");
        System.out.println(actualSubmitButtonTest);
        String expectedSubmitButtonTest = "Log in";
        Assert.assertEquals(actualSubmitButtonTest, expectedSubmitButtonTest, "Incorrect Text in Login button");
    }
    @Test
    public void searchButton() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement searchbutton = driver.findElement(By.xpath("//input[@class='button-1 search-box-button']"));
        Dimension dimension1 = searchbutton.getSize();
        int height1 = dimension1.height;
        int width1 = dimension1.width;
        System.out.println("height is " + height1);
        System.out.println("width is " + width1);
        Point point = searchbutton.getLocation();
        int x1 = point.x;
        int y1 = point.y;
        System.out.println("x cordinate = " + x1);
        System.out.println("y cordinate = " + y1);
        String actualsearchbutton = searchbutton.getAttribute("value");
        System.out.println(actualsearchbutton);
        String expectedsearchbutton = "Search";
        Assert.assertEquals(actualsearchbutton, expectedsearchbutton, "Incorrect Text in Search button");
    }




}
