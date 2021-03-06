package BreadthFirstSearch_DepthFS;

import org.junit.Test;

import java.util.ArrayDeque;

public class Leetcode_200_NumberOfIslands_Medium {
    /*******************Leetcode_200_NumberOfIslands_Medium************************/
    /**
     * 难度：Medium
     * https://leetcode.com/problems/number-of-islands/description/
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
     *思路方法：深度优先搜索DFS与宽度优先搜索BFS。
     * 方法1：深度优先搜索：DFS。
     *        创建一个与grid对应的visited布尔数组，用于标记mark该格子是否已经访问过；
     *        然后利用嵌套循环遍历每一个格子，只有当前格子为1且visited未访问过，
     *        调用DFS搜索该岛屿的所有格子，每一次调用DFS，都会自动递归地将该岛屿
     *        上下左右四个格子都标记为true，同时岛屿计数加一，最后返回岛屿总数即可。
     * 方法2：广度优先搜索：BFS。
     *        利用一个队列数据结构结合while循环搜索并标记一个岛屿的所有的方格。
     *        首先岛屿的第一个方格进入队列，进入即标记为true；然后弹出该点的时候，
     *        将上下左右满足条件的四个方格进入队列，并标记为true。
     *        直到队列为空，至此一个岛屿搜索并标记完毕。
     *
     *  DFS与BFS的共同特点：
     *      都是利用嵌套循环遍历所有的元素，通过条件限制和是否访问过来找到新岛屿的第一个点，
     *      如果找到新岛屿的第一个点，也就相当于找到了一个新的岛屿，
     *      后面就交给DFS或BFS去把该岛屿的所有点标记完全；因此岛屿的计数就在找到第一个点后(或者在标记完该岛屿所有点后)。
     *  DFS与BFS的区别：DFS利用递归的方式实现搜索并标记；
     *                BFS利用队列数据结构并结合while循环实现搜索并标记。
     *  代码实现时注意点：DFS是进入递归方法之后标记为已经访问过，而BFS是将该元素放入队列之后标记。
     *    DFS方法是递归方法，因此对元素的过滤放在DFS方法的开头；而BFS是在放入队列之前对元素进行过滤，
     *    只有符合条件的元素才会加入队列中。
     */

    /**
     * 通用方法：无论DFS或BFS，都是需要遍历每个点，判断是否是一个新岛屿的开始。
     * 若是一个新岛屿的开始，则会利用DFS或BFS标记该岛屿的每个方格。
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        //嵌套循环，遍历每一个点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {//新岛屿的起点
                    //采用了模板方法的设计模式
//                    DFS(grid,visited,i,j);//通过递归调用将该岛屿的所有格标记为true
                    BFS(grid, visited, i, j);//通过递归调用将该岛屿的所有格标记为true
                    count++;
                }
            }
        }
        return count;
    }

    //方法1：DFS深度优先搜索
    public void DFS(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length ||
                grid[i][j] != '1' || visited[i][j]) return;//这个判断必须放在DFS方法中，因为后续需要递归调用
        visited[i][j] = true;//注意：DFS是在递归方法中标记该元素已经被访问过
        DFS(grid, visited, i - 1, j);//上
        DFS(grid, visited, i, j + 1);//右
        DFS(grid, visited, i + 1, j);//下
        DFS(grid, visited, i, j - 1);//左
    }

    //方法2：BFS宽度优先搜索
    public void BFS(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length ||
                grid[x][y] != '1' || visited[x][y]) return;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.addLast(x);
        deque.addLast(y);
        visited[x][y] = true;//一定要在进入队列的时候就做标记，否则会使同一个点多次进入队列
        //为什么？因为队列弹出一个点可能会进去4个点，若是弹出再标记的话，
        // 则4个点都是未访问的，会多次进入队列中
        while (!deque.isEmpty()) {
            x = deque.removeFirst();
            y = deque.removeFirst();
            for (int k = 0; k < 4; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];
                if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[x].length
                        || grid[newX][newY] != '1' || visited[newX][newY])
                    continue;
                deque.addLast(newX);
                deque.addLast(newY);
                visited[newX][newY] = true;
            }
        }
    }

    /**
     * 宽度优先搜索BFS合并一个方法：合并到一个方法实现。
     * mark数组只负责标记为'1'的点，对于为'0'的点没有必要标记，
     * 因为判断为'0'，则直接continue;或不加入队列中。
     */
    public int numIslands_BFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, cols = grid[0].length, result = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        boolean[][] mark = new boolean[rows][cols];//标记使用
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        //嵌套for循环遍历每一个点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mark[i][j] || grid[i][j] == '0') continue;//被访问过或为'0'则直接continue
                //添加新岛屿的第一个点到队列中
                deque.add(i);
                deque.add(j);
                mark[i][j] = true;
                //利用for循环搜索该岛屿的所有土地，即将为1的全部搜索到并做标记
                while (!deque.isEmpty()) {
                    int x = deque.removeFirst();//注意：这里的x与上面的i要区分开
                    int y = deque.removeFirst();//注意：这里的y与上面的j要区分开；一次将x、y当成i、j，导致bug出现。
                    //i、j是开启一个岛屿的第一个点，而x、y是从队列中获取的一个点。
                    for (int k = 0; k < 4; k++) {
                        int newx = x + dx[k];
                        int newy = y + dy[k];
                        if (newx < rows && newx >= 0 && newy >= 0 && newy < cols
                                && !mark[newx][newy] && grid[newx][newy] == '1') {
                            deque.addLast(newx);
                            deque.addLast(newy);
                            mark[newx][newy] = true;
                        }
                    }
                }
                result++;
            }
        }
        return result;
    }

    //测试
    @Test
    public void test() {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        int result = numIslands(grid);
        System.out.println("result = " + result);

        char[][] grid2 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int result2 = numIslands(grid2);
        System.out.println("result2 = " + result2);

        char[][] grid3 = {{'1', '0', '1', '1', '0', '1', '1'}};
        int result3 = numIslands(grid3);
        System.out.println("result3 = " + result3);

        char[][] grid4 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int result4 = numIslands_BFS(grid4);
        System.out.println("result4 = " + result4);
    }

}
