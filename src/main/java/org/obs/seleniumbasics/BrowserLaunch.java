package org.obs.seleniumbasics;

import org.obs.seleniumarchitecture.ChromeDriver;
import org.obs.seleniumarchitecture.FirefoxDriver;
import org.obs.seleniumarchitecture.WebDriver;

public class BrowserLaunch {
    public static void main(String[] args) {
        WebDriver driver;
        //ChromeDriver driver=new ChromeDriver();
         driver=new ChromeDriver();
        String title= driver.getTitle();
        System.out.println(title);
        driver=new FirefoxDriver();

        String title2= driver.getTitle();
        System.out.println(title2);

       // WebDriver driver1=new FirefoxDriver();
        /*driver.getTitle();
        driver.getPageSource();
        driver.quit();
        driver.close();*/

    }
}
