package com.company;

import java.util.Arrays;

public class SelectionSortData {

    public enum Type{
        Default,
        NearlyOrdered
    }
    private int[] numbers;

    public int orderedIndex=-1; //[0,orderedIndex]
    public int currentMinIndex=-1;//当前最小的索引
    public int currentCompareIndex=-1; //当前正在比较的元素索引


    public SelectionSortData(int N,int randomBound,Type dataType){

        numbers=new int[N];

        for (int i=0;i<N;i++)
            numbers[i]=(int)(Math.random()*randomBound)+1;

        if (dataType==Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime=(int)(N*0.02);
            for (int i=0;i<swapTime;i++){
                int a=(int)(Math.random()*N);
                int b=(int)(Math.random()*N);
                swap(a,b);
            }
        }
    }

    public SelectionSortData(int N,int randomBound){
        this(N,randomBound,Type.Default);
    }

    public int N(){return numbers.length;}

    public int get(int index){
        if (index<0||index>=numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i,int j){
        int t=numbers[i];
        numbers[i]=numbers[j];
        numbers[j]=t;
    }
}
