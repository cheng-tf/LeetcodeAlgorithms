package BiShiAlgorithms;

import java.util.*;

public class TouTiao_20181008 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();
        int k = Integer.parseInt(line1);
        ArrayList<String> list = new ArrayList<>();
        int num = 0;
        while (in.hasNextLine() && num < 4) {//注意while处理多个case
            String a = in.nextLine();
            list.add(a);
            num++;
        }
        int row = list.size();
        int col = list.get(0).split(",").length;
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            String[] nums = list.get(i).split(",");
            for (int j = 0; j < col; j++) {
                arr[i][j] = Integer.parseInt(nums[j]);
            }
        }
        int[][] result = process(arr, k);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(result[i][j]);
                if (j != col - 1)
                    System.out.print(",");
            }
            if (i != row - 1)
                System.out.println();
        }

    }

    public static int[][] process(int[][] arr, int k) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] num = new int[row][col];
        PriorityQueue<Item> queue = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return num[o1.x][o1.y] - num[o2.x][o2.y];
            }
        });

        boolean[][] mark = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 1) {
                    num[i][j] = 0;
                    queue.add(new Item(i, j));
                    mark[i][j] = true;
                } else {
                    num[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Item item = queue.remove();
            int x = item.x;
            int y = item.y;
            int v = num[x][y] + 1;
            if (arr[x][y] == -1) continue;//不在扩散
            for (int i = 0; i < 4; i++) {
                int newx = x + dx[i];
                int newy = y + dy[i];
                if (newx < 0 || newx > row - 1 ||
                        newy < 0 || newy > col - 1 || mark[newx][newy]) {
                    continue;
                }
                num[newx][newy] = v;
                mark[newx][newy] = true;
                queue.add(new Item(newx, newy));
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (num[i][j] > k) {
                    num[i][j] = 1;
                } else {
                    num[i][j] = 0;
                }
            }

        }
        return num;
    }

    public static class Item {
        int x;
        int y;

        public Item(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    //    @Test
    public void test() {
        int[][] arr = {{0, -1, 1, 0}, {0, 0, 0, -1}, {0, -1, 0, -1}, {1, -1, 0, 0}};
        int[][] num = process(arr, 3);
        System.out.println("Arrays.toString(num[0]) = " + Arrays.toString(num[0]));
        System.out.println("Arrays.toString(num[0]) = " + Arrays.toString(num[1]));
        System.out.println("Arrays.toString(num[0]) = " + Arrays.toString(num[2]));
        System.out.println("Arrays.toString(num[0]) = " + Arrays.toString(num[3]));
    }

}
