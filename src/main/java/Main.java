import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

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

        String consumerDate = "2001-10-10 11:11:10";

        Date currDate = new Date();
        Date date;
        try {
//            Date date = DateUtils.parseDate("2001-10-10", "yyyy-MM-dd");
            date = DateUtils.parseDate(consumerDate.substring(0, 10), new String[]{"yyyy-MM-dd"});
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(new Date());

            String dateStr2 = "2001-10-10 11:11:10";
            Date date2 = sdf.parse(dateStr2);

            Date date3 = DateUtils.parseDate(dateStr2,"yyyy-MM-dd HH:mm:ss");

            System.out.println("date = " + sdf.format(date));
            System.out.println("date = " + sdf.format(DateUtils.addSeconds(DateUtils.addDays(date,1),-1)));

        }catch(Exception e){

        }

        Iterator<Calendar> ite = DateUtils.iterator(new Date(),2);
        for(Calendar ca = ite.next();ite.hasNext();){
            ite.next();
            System.out.println("ca = " + ca.getTimeInMillis());
        }

        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("aaa","bbbb");
        System.out.println("hashMap = " + hashMap.get("aaa"));
        System.out.println("hashMap = " + hashMap.get("bbb"));
        System.out.println("hashMap = " + hashMap.get("ccc"));

        hashMap.put("dddd",hashMap.get("bbb"));
        System.out.println("hashMap = " + hashMap.get("dddd"));



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

