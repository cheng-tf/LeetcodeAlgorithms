package BiShiAlgorithms;
import java.util.Scanner;


public class Toutiao_3 {


    /*
     将一个字符串还原成ip地址
     */
    public class Main {
    }

        //可还原的ip地址个数
        public static int total = 0;
        //测试数据：指定字符串
        public static String ip = "8888";
        //存放可能的ip地址
        public static String[] temp = new String[4];

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String ip1 = in.next();
            ip = ip1;
            dfs(0);
            System.out.println(total);
        }

        public static void dfs(int cur) {
            if (cur == 4) {
                int len = 0;
                for (int i = 0; i < 4; i++) {
                    len += temp[i].length();
                }
                if (len != ip.length()) {
                    return;
                }
                int i;
                for (i = 0; i < 4; i++) {
                    if (Integer.parseInt(temp[i]) > 255) {
                        break;
                    }
                }
                if (i == 4) {
                    total++;
              /*  for (int j = 0; j < 4; j++) {
                    System.out.print(temp[j] + ".");
                }
                System.out.println();*/
                }
                return;
            }
            String backup = ip;
            for (int i = 1; i <= 3; i++) {
                int len = 0;
                for (int j = 0; j < cur; j++) {
                    len += temp[j].length();
                }
                if ((len + i) <= ip.length()) {
                    temp[cur] = backup.substring(len, (len + i));
                    dfs(cur + 1);
                }
            }
        }
}
