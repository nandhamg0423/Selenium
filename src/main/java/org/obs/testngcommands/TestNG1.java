package org.obs.testngcommands;

import org.testng.annotations.*;

public class TestNG1 {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is from before method");
    }
    @BeforeClass
    public void beforClass(){
        System.out.println("This is from before class");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("This is from before Test");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("This is for before suite");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("This is from after method");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("This is from after class");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("This is from after test");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("This is for after suite");
    }
    @Test(priority = 1)
    public void testcase1(){
        System.out.println("testcase1 executed");
    }
    @Test(priority = 2,enabled = true)
    public void testCase2() {
        System.out.println("Testcase2 executed");
    }
}

