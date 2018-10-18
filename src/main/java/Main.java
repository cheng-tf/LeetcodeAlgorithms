import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Long l = 1000L;
        System.out.println(l);
        System.out.println("1000".equals(l.toString()));

//        int result = migong();
        int[][] arr = {{
                0, 1, 3},
                {2, 3, -1},
                {1, 3, 1}};
        int result = helper(arr, 3, 3);
        System.out.println(result);
    }

    public static int migong() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] strs = line.split(" ");
        int rows = Integer.valueOf(strs[0]);
        int cols = Integer.valueOf(strs[1]);
        String[] vals = new String[rows];
        for (int i = 0; i < rows; i++) {
            vals[i] = scanner.nextLine();
        }

        int[][] arr = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] vs = vals[i].split(" ");
            for (int j = 0; j < cols; j++) {
                arr[i][j] = Integer.valueOf(vs[j]);
            }
        }
        return helper(arr, rows, cols);
    }

    public static int helper(int[][] arr, int rows, int cols) {
        //思路分析：从终点往出发点找
//        int[] dx = {-1, 0};
//        int[] dy = {0, -1};
        int[] dx = {-1,0,1, 0};
        int[] dy = {0,-1,0,1};
        int[][] nums = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nums[i][j] = Integer.MAX_VALUE;
            }
        }
        boolean[][] mark = new boolean[rows][cols];
        Deque<Integer> queue = new ArrayDeque<Integer>();
        queue.push(rows - 1);
        queue.push(cols - 1);
        nums[rows - 1][cols - 1] = arr[rows - 1][cols - 1];
        while (!queue.isEmpty()) {
            int x1 = queue.remove();
            int y1 = queue.remove();
            int num = nums[x1][y1];
//            if(num == -1) continue;
            for (int i = 0; i < dx.length; i++) {
                int newx = x1 + dx[i];
                int newy = y1 + dy[i];
                if (newx < 0 || newx > rows - 1 || newy < 0 || newy > cols - 1 || mark[newx][newy]) continue;
                if (arr[newx][newy] == -1) {
                    nums[newx][newy] = -1;
                } else {
                    if (num + arr[newx][newy] < nums[newx][newy]) {
                        nums[newx][newy] = num + arr[newx][newy];
                        mark[newx][newy] = true;
                        queue.add(newx);
                        queue.add(newy);
                    }
                }

            }
        }
        return nums[0][0];
    }

}

