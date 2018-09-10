package SearchAlgorithm.SearchAlgorithms_DFS_BFS;

import org.junit.Test;

public class Leetcode_200_NumberOfIslands_Medium {
    /*******************Leetcode_200_NumberOfIslands_Medium************************/
    /**
     * 难度：Medium
     *https://leetcode.com/problems/number-of-islands/description/
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed
     * by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * Example 1:
     * Input:
     * 11110
     * 11010
     * 11000
     * 00000
     * Output: 1
     * Example 2:
     *
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     * Output: 3
     *
     *思路方法：深度优先搜索与宽度优先搜索。
     * 方法1：深度优先搜索：DFS。
     *        创建一个与grid对应的visited布尔数组，用于标记mark该格子是否已经访问过；
     *        然后利用嵌套循环遍历每一个格子，只有当前格子为1且visited未访问过，
     *        调用DFS搜索该岛屿的所有格子，每一次调用DFS，都会自动递归地将该岛屿
     *        上下左右四个格子都标记为true，同时岛屿计数加一，最后返回岛屿总数即可。
     * 方法2：宽度优先搜索：BFS。
     *
     *
     */

    /**
     * 方法1：深度优先搜索
     */
    public int numIslands(char[][] grid) {
        if(grid == null||grid.length == 0||grid[0].length == 0) return 0;
        int rows = grid.length,columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int count = 0;
        //嵌套循环，遍历每一个点
        for(int i = 0;i < rows;i++){
            for(int j = 0;j < columns;j++){
                if(grid[i][j] == '1' && !visited[i][j]){//新岛屿的起点
                    DFS(grid,visited,i,j);//通过递归调用将该岛屿的所有格标记为true
                    count++;
                }
            }
        }
        return count;
    }

    //深度优先搜索
    public void DFS(char[][] grid,boolean[][] visited,int i,int j ){
        if(i < 0 ||i >= grid.length|| j <0 || j >= grid[i].length||grid[i][j] != '1'|| visited[i][j]) return;
        visited[i][j] = true;//标记已经访问过
        DFS(grid,visited,i-1,j);//上
        DFS(grid,visited,i,j+1);//右
        DFS(grid,visited,i+1,j);//下
        DFS(grid,visited,i,j-1);//左
    }

    //宽度优先搜索


    //测试
    @Test
    public void test(){
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int result = numIslands(grid);
        System.out.println("result = " + result);

        char[][] grid2 = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        int result2 = numIslands(grid2);
        System.out.println("result2 = " + result2);
    }

}
