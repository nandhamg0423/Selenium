package org.obs.testngcommands;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG1 {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is from before method");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("This is from after method");
    }
    @Test(priority = 1)
    public void testcase1(){
        System.out.println("testcase1 executed");
    }
    @Test(priority = 2)
    public void testCase2(){
        System.out.println("Testcase2 executed");
    }
}
