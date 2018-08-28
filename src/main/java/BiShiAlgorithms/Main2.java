package BiShiAlgorithms;

import java.util.Scanner;
import java.util.TreeSet;

public class Main2 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if(line == null || line.length() == 0){
                System.out.println(0);
                return;
            }
            char[] chars = line.toCharArray();
            int len = chars.length;
            int temp = 0;
            int count = 0;
            for(int i = 0;i < len;i++){
                temp += (chars[i]-'0');
                if(temp % 3 == 0) {
                    count++;
                    temp = 0;
                }
            }
            System.out.println(count);
        }
}
