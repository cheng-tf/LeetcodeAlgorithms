package ArrayAlgorithms;

import org.junit.Test;

public class JianzhiOffer_13_RobotMovingRange {

    /*********************剑指Offer13:机器人移动范围****************************************/

    /**
     * 题目介绍：
     *     地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），
     * 因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     *
     * 思路介绍：回溯法。
     *         设起点坐标(0,0),定义一个函数，获取当前坐标(x,y)的数位和是否满足要求.
     *        为了避免重复，利用一个布尔二维数组标识该点有没有判断过。
     *        若当前点满足要求，则继续向上下左右判断。
     */
    public int movingCount(int threshold, int rows, int cols){
        if(threshold < 0||rows<0||cols <0) return 0;
        int x = 0,y=0;
        boolean[][] hasVisited = new boolean[rows][cols];
        int count =  movingCountHelper(threshold,rows,cols,x,y,hasVisited);
        return count;
    }

    /**
     * 递归方法
     */
    public int movingCountHelper(int threshold,int rows,int cols,int x,int y,boolean[][] hasVisited){
        int count = 0;
        if(x < rows&&y<cols&&checkCurrentPoint(x,y,threshold,rows,cols)&&!hasVisited[x][y]){
            hasVisited[x][y] = true;//标记该点已经判断过
            count = 1 + movingCountHelper(threshold,rows,cols,x-1,y,hasVisited)
                    + movingCountHelper(threshold,rows,cols,x+1,y,hasVisited)
                    + movingCountHelper(threshold,rows,cols,x,y-1,hasVisited)
                    + movingCountHelper(threshold,rows,cols,x,y+1,hasVisited);
        }
        return count;
    }
    
    public boolean checkCurrentPoint(int x,int y,int threshold,int rows,int cols){
        if(x<0||x >= rows||y>= cols||y<0) return false;
        int sum = getSum(x) + getSum(y);
        return sum<=threshold;
    }
    
    public int getSum(int x){
        int sum = 0;
        while(x != 0){
            sum += x%10;
            x /= 10;
        }
        return sum;
    }
    
    //测试
    @Test
    public void test(){
        int count = movingCount(10,99,3);
        System.out.println("count = " + count);
        int count2 = movingCount(5,10,10);
        System.out.println("count2 = " + count2);
    }


}
