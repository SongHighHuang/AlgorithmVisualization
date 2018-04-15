package com.company;


import java.util.Arrays;

public class HeapSortData {

    public enum Type{
        Default,
        NearlyOrdered,
        Identical
    }

    public int[] numbers;
    public int heapIndex; //numbers[heapIndex..N]已经排序好

    public int l,r;
    public int curPivot;
    public int curL,curR;
    public boolean[] fixedPivots;


    public HeapSortData(int N, int randomBound, Type type){
        numbers=new int[N];
        fixedPivots=new boolean[N];

        int lBound=1;
        int rBound=randomBound;
        if (type==Type.Identical){
            int avgNumber=(lBound+rBound)/2;
            lBound=avgNumber;
            rBound=avgNumber;
        }

        for (int i=0;i<N;i++) {
            numbers[i] = (int) (Math.random() * (rBound-lBound+1)) +lBound;
            fixedPivots[i]=false;
        }

        if (type==Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime=(int)(0.02*N);
            for (int i=0;i<swapTime;i++){
                int a=(int)(Math.random()*N);
                int b=(int)(Math.random()*N);
                swap(a,b);
            }
        }
    }

    public HeapSortData(int N, int randomBound){
        this(N,randomBound,Type.Default);
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if (index<0||index>=numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        return numbers[index];
    }

    public void swap(int i,int j){
        if (i<0||i>=numbers.length||j<0||j>numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        int t=numbers[i];
        numbers[i]=numbers[j];
        numbers[j]=t;
    }
}
