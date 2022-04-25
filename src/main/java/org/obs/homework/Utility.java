package org.obs.homework;

import java.util.Random;

public class Utility {
    //public static void main(String[] args) {
       // Utility util=new Utility();
        //util.random();
    //}
    public String random() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        String email="testmail"+ randomInt +"@gmail.com";
        return email;

    }

}