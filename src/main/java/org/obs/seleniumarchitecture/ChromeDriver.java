package org.obs.seleniumarchitecture;

public class ChromeDriver implements WebDriver{

    @Override
    public String getTitle() {

        return "chrome driver get title";
    }

    @Override
    public String getPageSource() {

        return "chrome driver get page sourse";
    }

    @Override
    public void quit() {
        System.out.println("chrome driver quit");
    }

    @Override
    public void close() {
        System.out.println("chrome driver close");

    }
}
