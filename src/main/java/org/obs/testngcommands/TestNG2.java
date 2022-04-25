package org.obs.testngcommands;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG2 {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is from before method");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("This is from after method");
    }
    @Test(priority = 3)
    public void testcase3(){
        System.out.println("testcase3 executed");
    }
    @Test(priority = 4)
    public void testCase4() {
        System.out.println("Testcase4 executed");
    }
}
