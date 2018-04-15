package com.company;


public class MonteCarloExperiment {




    public static int run(int squareSide,int N){
        int k=0;
       for (int i=0;i<N;i++){
           double m=Math.random()*(squareSide);
           double l=Math.random()*(squareSide);

           if ((m*m+l*l)<=squareSide*squareSide)
               k++;
       }

       return k;

    }

    public static void main(String[] args){
        int squareSide=100;
        int N=20000;
        int k=run(squareSide,N);

        System.out.println("pi="+(double)k/(double)N*4.0);
    }
}
