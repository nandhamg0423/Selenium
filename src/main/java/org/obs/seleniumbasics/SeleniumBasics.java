package org.obs.seleniumbasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumBasics {
    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver","E:\\Selenium_Files\\chromedriver.exe");
        //WebDriver driver= new ChromeDriver();

        //System.setProperty("webdriver.gecko.driver","E:\\Selenium_Files\\geckodriver.exe");
        //WebDriver driver = new FirefoxDriver();

        System.setProperty("webdriver.edge.driver", "E:\\Selenium_Files\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
    }
}
