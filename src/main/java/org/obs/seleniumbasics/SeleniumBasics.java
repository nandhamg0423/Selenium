package org.obs.seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;

public class SeleniumBasics {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\Selenium_Files\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //System.setProperty("webdriver.gecko.driver","E:\\Selenium_Files\\geckodriver.exe");
        //WebDriver driver = new FirefoxDriver();

        //System.setProperty("webdriver.edge.driver", "E:\\Selenium_Files\\msedgedriver.exe");
        //WebDriver driver = new EdgeDriver();

        //to delete cookies
        driver.manage().deleteAllCookies();

        // to maximize window
        driver.manage().window().maximize();

        //to load url
        //driver.get("http://demowebshop.tricentis.com/");

        //NAVIGATION COMMANDS
        driver.navigate().to("http://demowebshop.tricentis.com/login");//used instead of driver.get
        //driver.navigate().back(); //Takes youback by one page on the browser's history.
        //driver.navigate().forward(); //Takes you forward by one page on the browser's history.
        //driver.navigate().refresh(); // to refresh the page


        //how to get title
        String actTitle = driver.getTitle();
        System.out.println(actTitle);

        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        //sourse code
        String pageSourse = driver.getPageSource();
        System.out.println(pageSourse);

        // WebElement email=driver.findElement(By.id("Email"));
        //  WebElement email=driver.findElement(By.name("Email"));
        // WebElement email=driver.findElement(By.className("email"));
        // WebElement email=driver.findElement(By.xpath("//*[@id=\"Email\"]"));
        WebElement email = driver.findElement(By.cssSelector("#Email"));
        email.sendKeys("nandhamg5078@gmail.com");

        //to close browser
        //driver.close();
        //driver.quit(); //for closing firefox, this commands close all browser windows
    }
}
