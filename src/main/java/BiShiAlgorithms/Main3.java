package BiShiAlgorithms;

import java.util.Scanner;

public class Main3

{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        char[] chars = line.toCharArray();
        int len = chars.length;
        int temp = 0;
        int count = 0;
        for(int i = 0;i < len;i++){
            if(temp % 3 == 0) {
                count++;
                temp = 0;
            }
            temp += (chars[i]-'0');
        }
        System.out.println(count);
    }
}
