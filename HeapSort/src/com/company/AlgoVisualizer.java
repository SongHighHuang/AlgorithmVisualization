package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY=20;

    // TODO: 创建自己的数据
    private HeapSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight,int N,HeapSortData.Type type){

        // 初始化数据
        // TODO: 初始化数据
        data=new HeapSortData(N,sceneHeight,type);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge Sort Visualization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth,int sceneHeight,int N) {
        this(sceneWidth, sceneHeight, N, HeapSortData.Type.Default);
    }

    public void  run(){
        setData(data.N());

        //建堆
        for (int i=(data.N()-1-1)/2;i>=0;i--){
            shiftDown(data.N(),i);
        }

        //堆排序

        for (int i=data.N()-1;i>0;i--){
            data.swap(0,i);
            shiftDown(i,0);
            setData(i);
        }

        setData(0);


    }

    private void shiftDown(int n,int k){
        while (2*k+1<n){
            int j=2*k+1;
            if (j+1<n&&data.get(j+1)>data.get(j))
                j+=1;
            if (data.get(k)>=data.get(j))
                break;
            data.swap(k,j);
            setData(data.heapIndex);

            k=j;
        }
    }

    private void setData(int heapIndex){
        data.heapIndex=heapIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);

    }
    /*

    public void run(){

        setData(-1,-1,-1,-1,-1,-1);
        quickSort3Ways(0,data.N()-1);
        setData(-1,-1,-1,-1,-1,-1);

    }

    private void quickSort3Ways(int l, int r){
        if (l>r)
            return;
        if (l==r){
            setData(l,r,l,-1,-1,-1);
            return;
        }

        int p=(int)(Math.random()*(r-l+1))+l;
        setData(l,r,-1,p,-1,-1);
        data.swap(l,p);
        int v=data.get(l);
        setData(l,r,-1,l,-1,-1);

        //Three way Quick Sort partition

        int lt=l;   //<v
        int gt=r+1; //>v
        int i=l+1;  //=v
        setData(l,r,-1,l,lt,gt);
        while (i<gt){
            if (data.get(i)<v){
                data.swap(i,lt+1);
                i++;
                lt++;
            }else if (data.get(i)>v){
                data.swap(i,gt-1);
                gt--;
            }else
                i++;
            setData(l,r,-1,l,i,gt);
        }

        data.swap(l,lt);
        setData(l,r,lt,-1,-1,-1);

        quickSort3Ways(l,lt-1);
        quickSort3Ways(gt,r);
    }
    /*

    public void run(){

        setData(-1,-1,-1,-1,-1,-1);
        quickSort2Ways(0,data.N()-1);
        setData(-1,-1,-1,-1,-1,-1);
    }

    private void quickSort2Ways(int l, int r){
        if (l>r)
            return;
        if (l==r){
            setData(l,r,l,-1,-1,-1);
            return;
        }

        setData(l,r,-1,-1,-1,-1);

        int p=partition(l,r);
        quickSort2Ways(l,p-1);
        quickSort2Ways(p+1,r);
    }

    private int partition(int l, int r){

        int p=(int)(Math.random()*(r-l+1))+l;
        setData(l,r,-1,p,-1,-1);
        data.swap(l,p);
        setData(l,r,-1,l,-1,-1);
        int v=data.get(l);


        int i=l+1,j=r;
        setData(l,r,-1,l,i,j);
        while (true){
            while (i<=r&&data.get(i)<v){
                i++;
                setData(l,r,-1,l,i,j);
            }

            while (j>=l+1&&data.get(j)>v){
                j--;
                setData(l,r,-1,l,i,j);
            }
            if (i>j)
                break;
            data.swap(i,j);
            i++;
            j--;
            setData(l,r,-1,l,i,j);
        }
        data.swap(l,j);
        setData(l,r,j,-1,-1,-1);
        return j;
    }

    // 动画逻辑


    //单路快速排序
    public void run(){

        setData(-1,-1,-1,-1,-1);

        quickSort(0,data.N()-1);

        setData(-1,-1,-1,-1,-1);
    }

    private void quickSort(int l,int r){

        if (l>r)
            return;
        if (l==r){
            setData(l,r,l,-1,-1);
            return;
        }

        setData(l,r,-1,-1,-1);
        int p=partition(l,r);
        quickSort(l,p-1);
        quickSort(p+1,r);
    }

    private int partition(int l,int r){
        int p=(int)(Math.random()*(r-l+1))+l;
        setData(l,r,-1,p,-1);
        data.swap(l,p);
        int v=data.get(l);
        setData(l,r,-1,l,-1);

        int j=l;

        for (int i=l+1;i<=r;i++){
            setData(l,r,-1,l,i);
            if (data.get(i)<v){
                j++;
                data.swap(j,i);
                setData(l,r,-1,l,i);
            }
        }
        data.swap(l,j);
        setData(l,r,j,-1,-1);

        return j;
    }
    /*


    private void setData(int l,int r,int fixedPivot,int curPivot,int curL,int curR){
        data.l=l;
        data.r=r;
        if (fixedPivot!=-1) {
            data.fixedPivots[fixedPivot] = true;
            int i=fixedPivot;
            while (i<data.N()&&data.get(i)==data.get(fixedPivot)){
                data.fixedPivots[i]=true;
                i++;
            }
        }

        data.curPivot=curPivot;
        data.curL=curL;
        data.curR=curR;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    */
    /*

    private void mergeSort(int l, int r){

        if( l >= r )
            return;

        setData(l, r, -1);

        int mid = (l+r)/2;
        mergeSort(l, mid);
        mergeSort(mid+1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r){

        int[] aux = Arrays.copyOfRange(data.numbers, l, r+1);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid+1;
        for( int k = l ; k <= r; k ++ ){

            if( i > mid ){  // 如果左半部分元素已经全部处理完毕
                data.numbers[k] = aux[j-l]; j ++;
            }
            else if( j > r ){   // 如果右半部分元素已经全部处理完毕
                data.numbers[k] = aux[i-l]; i ++;
            }
            else if( aux[i-l] < aux[j-l] ){  // 左半部分所指元素 < 右半部分所指元素
                data.numbers[k] = aux[i-l]; i ++;
            }
            else{  // 左半部分所指元素 >= 右半部分所指元素
                data.numbers[k] = aux[j-l]; j ++;
            }

            setData(l, r, k);
        }
    }*/

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }



    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N=100;
        HeapSortData.Type dateType= HeapSortData.Type.Default;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N,dateType);
    }
}