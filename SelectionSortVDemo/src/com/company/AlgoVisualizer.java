package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private static int DELAY=20;

    // TODO: 创建自己的数据
    private SelectionSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight,int N,SelectionSortData.Type type){

        // 初始化数据
        // TODO: 初始化数据
        data=new SelectionSortData(N,sceneHeight,type);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth,int sceneHeight,int N){
        this(sceneWidth,sceneHeight,N,SelectionSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){

       setData(0,-1,-1);

        for (int i=0;i<data.N();i++){
            int minIndex=i;
            setData(i,-1,minIndex);
            for (int j=i+1;j<data.N();j++) {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex)){
                    minIndex = j;
                    setData(i,j,minIndex);
                }
            }

            data.swap(i,minIndex);
           setData(i+1,-1,-1);
        }

       setData(data.N(),-1,-1);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    private void setData(int orderedIndex,int currentCompareIndex,int currentMinIndex){
        data.orderedIndex=orderedIndex;
        data.currentCompareIndex=currentCompareIndex;
        data.currentMinIndex=currentMinIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N=100;
        SelectionSortData.Type dateType=SelectionSortData.Type.NearlyOrdered;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N,dateType);
    }
}
