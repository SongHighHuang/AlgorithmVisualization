package com.company;

import java.awt.*;

public class AlgoVisualizer {

    private Circle[] circles;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth,int sceneHeight,int N){
        circles=new Circle[10];
        int R=50;
        for (int i=0;i<N;i++){
            int x=(int)(Math.random()*(sceneWidth-2*R))+R;
            int y=(int)(Math.random()*(sceneHeight-2*R))+R;
            int vx=(int)(Math.random()*11)-5;
            int vy=(int)(Math.random()*11)-5;
            circles[i]=new Circle(x,y,R,vx,vy);
        }

        EventQueue.invokeLater(()->{
            frame=new AlgoFrame("Welcome",sceneWidth,sceneHeight);
            new Thread(()->{
               run();
            }).start();

        });
    }

    private void run(){
        while (true){
            //绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            //更新数据
            for (Circle circle:circles)
                circle.move();
        }
    }
}
