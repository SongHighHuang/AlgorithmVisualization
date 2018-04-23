package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private static int blockSide=32;
    private static int DELAY=5;

    private MineSweeperData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N,int M,int mineNumber){
        data=new MineSweeperData(N,M,mineNumber);
        int sceneWidth=M*blockSide;
        int sceneHeight=N*blockSide;

        EventQueue.invokeLater(()->{
            frame=new AlgoFrame("Mine Sweeper",sceneWidth,sceneHeight);
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(()->{
                run();
            }).start();
        });


    }

    private void  run(){
        setData(false,-1,-1);
    }

    private void setData(boolean isLeftClicked,int x,int y){
        if (data.inArea(x,y)){
            if (isLeftClicked){
                if (data.isMine(x,y)){
                    //Game over
                    data.open[x][y]=true;
                }
                else
                    data.open(x,y);

            }

            else
                data.flages[x][y]=!data.flages[x][y];
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent event){
            event.translatePoint(
                    -(int)(frame.getBounds().width-frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height-frame.getCanvasHeight()));

            Point pos=event.getPoint();

            int w=frame.getCanvasWidth()/data.M();
            int h=frame.getCanvasHeight()/data.N();

            int x=pos.y/h;
            int y=pos.x/w;

            if (SwingUtilities.isLeftMouseButton(event))
                setData(true,x,y);
            else
                setData(false,x,y);
        }
    }

    public static void main(String[] args){
        int N=20;
        int M=20;
        int mineNumer=50;
        new AlgoVisualizer(N,M,mineNumer);
    }
}
