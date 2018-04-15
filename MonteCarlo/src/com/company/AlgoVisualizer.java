
package com.company;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {


    private int[] moneys;  // 数据
    private AlgoFrame frame;    // 视图
    private int DELAY=40;

    public AlgoVisualizer(int sceneWidth, int sceneHeight){

        // 初始化数据
        moneys=new int[100];
        for (int i=0;i<moneys.length;i++)
            moneys[i]=100;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            //frame.addKeyListener(new AlgoKeyListener());
            //frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        while (true){

            Arrays.sort(moneys);
            frame.render(moneys);
            AlgoVisHelper.pause(DELAY);
           for (int k=0;k<50;k++) {
               for (int i = 0; i < moneys.length; i++) {
                   //if (moneys[i] > 0) {
                       int j = (int) (Math.random() * moneys.length);
                       moneys[i] -= 1;
                       moneys[j] += 1;
                   //}
               }
           }
        }
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 800;


        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
