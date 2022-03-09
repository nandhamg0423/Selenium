package org.obs.seleniumarchitecture;

public interface WebDriver {

    public String getTitle();
    public String getPageSource();
    public void quit();
    public void close();

}
