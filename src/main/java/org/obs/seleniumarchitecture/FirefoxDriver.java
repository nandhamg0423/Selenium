package org.obs.seleniumarchitecture;

public class FirefoxDriver implements WebDriver{
    @Override
    public String getTitle() {
        return "firefox driver get title";

    }

    @Override
    public String getPageSource() {
        return "firefox driver get page sourse";
    }

    @Override
    public void quit() {
        System.out.println("firefox driver quit");
    }

    @Override
    public void close() {
        System.out.println("firefox driver close");
    }
}
