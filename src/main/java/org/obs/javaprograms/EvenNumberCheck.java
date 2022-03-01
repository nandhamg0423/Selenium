package org.obs.javaprograms;

public class EvenNumberCheck {


    public static void main(String[] args) {

        EvenNumberCheck even = new EvenNumberCheck();
        even.check(8);

    }

    public void check(int n) {
        if (n % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
    }
}

