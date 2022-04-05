package org.obs.seleniumcommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.obs.homework.Utility;
import org.obs.homework.UtilityExcel;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class SeleniumCommands {
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

    @BeforeMethod
    public void setUp() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

    @Test
    public void verifyHomepageTitle() {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid PageTitle");
    }

    @Test
    public void verifyLogin() throws IOException {
        driver.get("http://demowebshop.tricentis.com");
        WebElement login = driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        UtilityExcel excel = new UtilityExcel();
        List<String> data = excel.readDataFromExcel("\\src\\main\\resources\\TestData.xlsx", "Login");
        WebElement loginEmail = driver.findElement(By.cssSelector("input#Email"));
        System.out.println(data);
        loginEmail.sendKeys(data.get(2));
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys(data.get(3));
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement account = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID = account.getText();
        String expectedemailID = "nandhamg5078@gmail.com";
        Assert.assertEquals(actualemailID, expectedemailID, "User login Failed");
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
        // Assert.assertEquals(actualSubmitButtonTest, expectedSubmitButtonTest, "Incorrect Text in Login button");
        String tagname = submit.getTagName();
        System.out.println("tagname is " + tagname);
        String cssProperty = submit.getCssValue("color");
        System.out.println(cssProperty);
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

    @Test
    public void gender(String gender) {
        List<WebElement> elementList = driver.findElements(By.xpath("//label[@class='forcheckbox']"));
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().equals("Male")) {
                elementList.get(i).click();
            }
        }
    }

    @Test
    public void verifyGender() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement regg = driver.findElement(By.xpath("//a[text()='Register']"));
        regg.click();
        gender("Male");
        Thread.sleep(3000);
        WebElement firstName = driver.findElement(By.xpath("//input[@class='text-box single-line'and@id='FirstName']"));
        firstName.sendKeys("james");
        WebElement lastname = driver.findElement(By.xpath("//input[@class='text-box single-line'and@id='LastName']"));
        lastname.sendKeys("k");
        WebElement regemail = driver.findElement(By.xpath("//input[contains(@id,'Email')]"));
        regemail.click();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        regemail.sendKeys("username" + randomInt + "@gmail.com");
        WebElement regpassword = driver.findElement(By.xpath("//input[@class='text-box single-line password'and@id='Password']"));
        regpassword.sendKeys("iamthepassword");
        WebElement confirmpassword = driver.findElement(By.xpath("//input[@class='text-box single-line password'and@id='ConfirmPassword']"));
        confirmpassword.sendKeys("iamthepassword");
        Thread.sleep(3000);
        WebElement regbutton = driver.findElement(By.xpath("//input[contains(@id,'register-button')]"));
        regbutton.click();
        Thread.sleep(6000);
    }

    @Test
    public void verifyElementPresent() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        Boolean buttonresult = submit.isDisplayed();
        System.out.println(buttonresult);
        Assert.assertTrue(buttonresult, "submit button not displayed");
    }

    @Test
    public void verifyElementenabled() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        Boolean buttonenable = submit.isEnabled();
        System.out.println(buttonenable);
        Assert.assertTrue(buttonenable, "Submit button not enabled");
    }

    @Test
    public void checkboxSelectionStatus() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement remind = driver.findElement(By.cssSelector("input#RememberMe"));
        Boolean beforeSelection = remind.isSelected();
        System.out.println(beforeSelection);
        Assert.assertFalse(beforeSelection, "Checkbox selection not expected");
        remind.click();
        Thread.sleep(3000);
        Boolean checkboxselect = remind.isSelected();
        System.out.println(checkboxselect);
        Assert.assertTrue(checkboxselect, "Checkbox not selected");
    }

    @Test
    public void verifyPromptAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        WebElement prompt = driver.findElement(By.cssSelector("button#promtButton"));
        prompt.click();
        Alert alert = driver.switchTo().alert();
        String alertString = alert.getText();
        System.out.println(alertString);
        alert.sendKeys("name");
        System.out.println(alert);
        Thread.sleep(6000);
        alert.accept();
        //alert.dismiss();
    }

    @Test
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alert1 = driver.findElement(By.id("alertButton"));
        alert1.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        //alert.dismiss();
    }

    @Test
    public void verifyConfirmationAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alert1 = driver.findElement(By.id("confirmButton"));
        alert1.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        //alert.dismiss();
    }

    @Test
    public void verifyDropDown() {
        driver.get("https://demo.guru99.com/test/newtours/");
        List<WebElement> registerlink = driver.findElements(By.xpath("//td[@class='mouseOut']/child::a"));
        for (int i = 0; i < registerlink.size(); i++) {
            if (registerlink.get(i).getText().equals("REGISTER")) {
                registerlink.get(i).click();
                break;
            }
        }
        WebElement country = driver.findElement(By.xpath("//select[contains(@name,'country')]"));
        Select select = new Select(country);
        select.selectByIndex(0);
        //select.selectByValue("INDIA");
        //select.selectByVisibleText("INDIA");
        List<WebElement> dropdownoption = select.getOptions();
        System.out.println(dropdownoption.size());
        for (int i = 0; i < dropdownoption.size(); i++) {
            System.out.println(dropdownoption.get(i));
        }
    }

    @Test
    public void demoDeleteAlert() {
        driver.get("https://demo.guru99.com/test/delete_customer.php");
        WebElement text = driver.findElement(By.name("cusid"));
        text.sendKeys("Nanditha");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        System.out.println(alert.getText());
        alert.accept();
    }

    @Test
    public void verifyMultipleWindows() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement clickhere = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickhere.click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            String newWindow = iterator.next();
            if (!newWindow.equals(parentWindow)) {
                driver.switchTo().window(newWindow);
                WebElement emails = driver.findElement(By.xpath("//input[@type='text'and @name='emailid']"));
                Utility utility = new Utility();
                String random2 = utility.random();
                emails.sendKeys(random2);
                WebElement buttonsubmit = driver.findElement(By.xpath("//input[contains(@name,'btnLogin')]"));
                buttonsubmit.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void verifyFramesInSelenium() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame(1);
        //driver.switchTo().frame("frame1");
        WebElement frameElement = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameElement);
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String sampleHeading = heading.getText();
        System.out.println(sampleHeading);
    }

    @Test
    public void verifyFirstScrollBar() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement color = driver.findElement(By.xpath("//select[@id='single-input-field']"));
        Select colorSelect = new Select(color);
        colorSelect.selectByIndex(0);
        List<WebElement> colorDropdown = colorSelect.getOptions();
        for (int i = 0; i < colorDropdown.size(); i++) {
            System.out.println(colorDropdown.get(i));
            colorDropdown.get(1).click();
            WebElement colorPresent = driver.findElement(By.xpath("//div[@id='message-one']"));
            String actualcolorPresent = colorPresent.getText();
            String expextedcolorPresent = "Selected Color : Red";
            Assert.assertEquals(actualcolorPresent, expextedcolorPresent, "Error message");
        }
    }

    @Test
    public void verifyscrollMultiple(String firstcolor, String secondcolor) {
        WebElement scrollbar = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(scrollbar);
        select.selectByIndex(0);
        select.selectByIndex(1);
        List<WebElement> scrollbarcolor = select.getOptions();
        for (int i = 0; i < scrollbarcolor.size(); i++) {
            System.out.println(scrollbarcolor.get(i).getText().equals(firstcolor));
            System.out.println(scrollbarcolor.get(i).getText().equals(secondcolor));
        }
    }

    @Test
    public void verifyfirstSelect(String firstcolor, String secondcolor) {
        WebElement scrollbar = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(scrollbar);
        select.selectByIndex(0);
        select.selectByIndex(1);
        select.getFirstSelectedOption();
        List<WebElement> scrollbarcolor = select.getOptions();
        for (int i = 0; i < scrollbarcolor.size(); i++) {
            System.out.println(scrollbarcolor.get(i).getText().equals(firstcolor));
            System.out.println(scrollbarcolor.get(i).getText().equals(secondcolor));
        }
    }

    @Test
    public void verifygetallColor(String firstcolor, String secondcolor) {
        WebElement scrollbar = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(scrollbar);
        select.selectByIndex(0);
        select.selectByIndex(1);
        select.getAllSelectedOptions();
        List<WebElement> scrollbarcolor = select.getOptions();
        for (int i = 0; i < scrollbarcolor.size(); i++) {
            System.out.println(scrollbarcolor.get(i).getText().equals(firstcolor));
            System.out.println(scrollbarcolor.get(i).getText().equals(secondcolor));
        }
    }

    @Test
    public void verifydeselectColor(int firstcolor, int secondcolor) {
        WebElement scrollbar = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(scrollbar);
        select.selectByIndex(0);
        select.selectByIndex(1);
        select.deselectByIndex(0);
        select.deselectByIndex(1);
        List<WebElement> scrollbarcolor = select.getOptions();
        for (int i = 0; i < scrollbarcolor.size(); i++) {
            System.out.println(scrollbarcolor.get(i).getText().equals(firstcolor));
            System.out.println(scrollbarcolor.get(i).getText().equals(secondcolor));
        }
    }

    @Test
    public void verifymainMultiple() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        verifyscrollMultiple("Red", "Green");
        WebElement getfirstselected = driver.findElement(By.xpath("//button[@id='button-first']"));
        getfirstselected.click();
        Thread.sleep(3000);
        WebElement getallSelected = driver.findElement(By.xpath("//button[@id='button-all']"));
        getallSelected.click();
        verifydeselectColor(0, 1);
        Thread.sleep(3000);

    }

    @Test
    public void verfifyRightClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement mouserightclick = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(mouserightclick).build().perform();
    }

    @Test
    public void verifydoubleclick() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement mousedoubleclick = driver.findElement(By.xpath("//button[@ondblclick='myFunction()']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(mousedoubleclick).build().perform();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        Thread.sleep(3000);
    }

    @Test
    public void verifymainofmenu(String mainmenuargument) throws InterruptedException {
        List<WebElement> mainnavigationbar = driver.findElements(By.xpath("//ul[@id='nav']//a"));
        for (int i = 0; i < mainnavigationbar.size(); i++) {
            if (mainnavigationbar.get(i).getText().equals(mainmenuargument)) {
                Actions mainmenuaction = new Actions(driver);
                mainmenuaction.moveToElement(mainnavigationbar.get(i)).build().perform();
            }
        }
    }

    @Test
    public void verifysubMenu(String submenuarguments) {
        List<WebElement> subnavigationbar = driver.findElements(By.xpath("//ul//li//a[contains(text(),'Sub')]"));
        for (int i = 0; i < subnavigationbar.size(); i++) {
            if (subnavigationbar.get(i).equals(submenuarguments)) {
                Actions submenuaction = new Actions(driver);
                submenuaction.moveToElement(subnavigationbar.get(i)).build().perform();
            }
        }
    }

    @Test
    public void mainofmenuBar() throws InterruptedException {
        driver.get("https://demoqa.com/menu/");
        verifymainofmenu("Main Item 2");
        Thread.sleep(3000);
        verifysubMenu("SUB SUB LIST »");
        Thread.sleep(3000);
    }

    @Test
    public void verifydemotourMenuItem(String select) {
        driver.get("https://demo.guru99.com/test/newtours/index.php");
        List<WebElement> demoTourList = driver.findElements(By.xpath("//tr[@class='mouseOut']//td//a"));
        for (int i = 0; i < demoTourList.size(); i++) {
            if (demoTourList.get(i).getText().equals(select)) {
                System.out.println(demoTourList.get(i).getText());
                Actions actions = new Actions(driver);
                actions.moveToElement(demoTourList.get(i)).build().perform();
            }
        }
    }

    @Test
    public void verifyMainDemotourMousHover() {
        driver.get("https://demo.guru99.com/test/newtours/");
        verifydemotourMenuItem("Home");
    }

    @Test
    public void verifyDragandDrop() {
        driver.get("https://demoqa.com/droppable");
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions dragdrop = new Actions(driver);
        dragdrop.dragAndDrop(drag, drop).build().perform();

    }

    @Test
    public void draganddropBy() {
        driver.get("https://demoqa.com/dragabble");
        WebElement dragElement = driver.findElement(By.xpath("//div[@id='dragBox']"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(dragElement, 200, 200).build().perform();
    }

    @Test
    public void keyboardActions() {
        driver.get("https://demoqa.com/text-box");
        WebElement fullNames = driver.findElement(By.xpath("//input[@id='userName']"));
        fullNames.sendKeys("John");
        WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
        email.sendKeys("abc@gmail.com");
        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currentAddress.sendKeys("TP Road,TVM");
        Actions actions = new Actions(driver);
        /**select the current address**/
        actions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();
        /**copy the current address**/
        actions.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).build().perform();
        /**pres tab keys to switch permanent address**/
        actions.sendKeys(Keys.TAB).build().perform();
        /**pasting address**/
        actions.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).build().perform();
    }

    @Test
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement choosefile = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
        choosefile.click();
        choosefile.sendKeys("E:\\Selenium_Files\\Sample.txt");
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='terms']"));
        checkbox.click();
        WebElement submitfile = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        submitfile.click();
    }

    @Test
    public void fileuploadusingRobertclass() throws AWTException {
        driver.get("https://www.monsterindia.com/seeker/registration");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        WebElement chooseFile = driver.findElement(By.xpath("//span[text()='Choose CV']"));
        chooseFile.click();
        StringSelection S = new StringSelection("E:\\Selenium_Files\\Sample.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(S, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void verifyJavaScriptExecutor() {
        driver.get("http://demowebshop.tricentis.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"newsletter-email\").value='nan@gmail.com'");
        js.executeScript("document.getElementById(\"newsletter-subscribe-button\").click()");

    }

    @Test
    public void multiDimensionalArray() {
        ArrayList<ArrayList<String>> strlist = new ArrayList<ArrayList<String>>();
        ArrayList<String> birds = new ArrayList<String>();
        birds.add("parrot");
        birds.add("crow");
        birds.add("peacock");
        ArrayList<String> animal = new ArrayList<String>();
        animal.add("cow");
        animal.add("elephant");
        animal.add("cat");
        ArrayList<String> color = new ArrayList<String>();
        color.add("blue");
        color.add("green");
        color.add("red");
        ArrayList<String> names = new ArrayList<String>();
        names.add("Peter");
        names.add("sam");
        names.add("Sankar");
        System.out.println(birds);
        System.out.println(animal);
        System.out.println(color);
        System.out.println(names);
        Iterator<String> iterator = animal.iterator();
        for (int i = 0; i < strlist.size(); i++) {
            for (int j = 0; j < strlist.get(i).size(); j++) {
                System.out.print(strlist.get(i).get(j) + " ");
            }
        }
        Iterator<String> it = animal.iterator();
        String animals = iterator.next();
        String d = "Manu";
        if (names.equals(d)) {
            System.out.println("Present");
        } else {
            System.out.println(d + " " + "not present in the list");
        }
    }
}
