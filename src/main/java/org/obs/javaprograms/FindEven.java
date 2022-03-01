package org.obs.javaprograms;

public class FindEven {
    int i;
    void evencount()
    {
        for(i=1;i<=20;i++)
        {
            if(i%2==0)
            {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        FindEven evenn=new FindEven();
        evenn.evencount();
    }

}
