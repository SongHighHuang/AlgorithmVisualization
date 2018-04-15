package com.company;



import java.util.Arrays;

public class MergeSortData{

    public enum Type{
        Default,
        NearlyOrdered
    }
    public int[] numbers;

    public int l,r;
    public int mergeIndex;

    public MergeSortData(int N,int randomBound,Type dataType){

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

    public MergeSortData(int N,int randomBound){
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

