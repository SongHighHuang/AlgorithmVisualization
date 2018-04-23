package com.company;

public class MineSweeperData {

    public static final String blockImageURL="resources/block.png";
    public static final String flagImageURL="resources/flag.png";
    public static final String mineImageURL="resources/mine.png";
    public static String numberImageURL(int num){
        if (num<0||num>=8)
            throw new IllegalArgumentException("No such a number image!");
        return "resources/"+num+".png";
    }

    private int N,M;
    private int[][] numbers;
    private boolean[][] mines;
    public boolean[][] open;
    public boolean[][] flages;

    public MineSweeperData(int N,int M,int mineNumber){
        if (N<=0||M<=0)
            throw new IllegalArgumentException("Mine sweeper size is invalid");
        if (mineNumber<0||mineNumber>N*M)
            throw new IllegalArgumentException("Mine number is larger than the size");

        this.N=N;
        this.M=M;

        mines=new boolean[N][M];
        open=new boolean[N][M];
        flages=new boolean[N][M];
        numbers=new int[N][M];

        generateMines(mineNumber);
        calculateNumbers();
    }

    public int N(){
        return N;
    }

    public int M(){
        return M;
    }

    public boolean isMine(int x,int y){
        if (!inArea(x,y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        return mines[x][y];
    }

    public boolean inArea(int x,int y){
        return x>=0&&x<N&&y>=0&&y<M;
    }

    public int getNumber(int x,int y){
        if (!inArea(x,y))
            throw new IllegalArgumentException("Out of index in getNuber function");
        return numbers[x][y];
    }

    private void generateMines(int mineNumber){
        /*
        for (int i=0;i<mineNumber;i++) {
            while (true) {
                int x = (int) (Math.random() * N);
                int y = (int) (Math.random() * M);
                if (!mines[x][y]) {
                    mines[x][y] = true;
                    break;
                }
            }
            */
        for (int i=0;i<mineNumber;i++){
            int x=i/M;
            int y=i%M;
            mines[x][y]=true;
        }

        for (int i=N*M-1;i>=0;i--){
            int ix=i/N;
            int iy=i%M;

            int randNumber=(int)(Math.random()*(i+1));

            int randX=randNumber/M;
            int randY=randNumber%M;


            swap(ix,iy,randX,randY);
        }



    }

    public void open(int x,int y){
        if (!inArea(x,y))
            throw new IllegalArgumentException("Out of index in open function!");

        if (isMine(x,y))
            throw new IllegalArgumentException("Cannot open an mine block in open");

        open[x][y]=true;

        if (numbers[x][y]>0)
            return;

        for (int i=x-1;i<x+1;i++)
            for (int j=y-1;j<=y+1;j++){
            if (inArea(i,j)&&!open[i][j]&&!mines[i][j])
                open(i,j);
            }
    }

    private void swap(int x1,int y1,int x2,int y2){
        boolean t=mines[x1][y1];
        mines[x1][y1]=mines[x2][y2];
        mines[x2][y2]=t;
    }

    private void calculateNumbers(){
        for (int i=0;i<N;i++)
            for (int j=0;j<M;j++){
            if (mines[i][j])
                numbers[i][j]=-1;
            numbers[i][j]=0;

            for (int ii=i-1;ii<=i+1;ii++)
                for (int jj=j-1;jj<j+1;jj++)
                    if (inArea(ii,jj)&&mines[ii][jj])
                        numbers[i][j]++;
            }
    }

}
