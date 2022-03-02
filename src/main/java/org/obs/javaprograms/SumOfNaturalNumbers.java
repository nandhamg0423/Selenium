package org.obs.javaprograms;

public class SumOfNaturalNumbers {

    int i, n = 100, s = 0;

    public static void main(String[] args) {
        SumOfNaturalNumbers son = new SumOfNaturalNumbers();
        son.sum();


    }


    void sum() {

        for (i = 1; i <= n; i++) {

            s = s + i;
        }
        System.out.println("Sum" + s);
    }
}
