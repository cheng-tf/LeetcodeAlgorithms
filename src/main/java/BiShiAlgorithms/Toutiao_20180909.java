package BiShiAlgorithms;
import java.util.Scanner;
public class Toutiao_20180909 {
    public class Main{

    }


    //80%  有错误之处
    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] chars = line.toCharArray();
        int count = 0,maxLen = 0;
        int[] hashTable = new int[256];
        int start = 0;
        for(int i = 0;i < chars.length;i++){
            int c = chars[i];
            if(hashTable[c]==0){
                hashTable[c]++;
                count++;
            }else{
                if(maxLen < count)
                    maxLen = count;
                //这里错误
                for(int ii = start;ii < i;ii++)
                    hashTable[chars[ii]] = 0;
                start = i;
                count = 1;
                hashTable[c]++;
            }
        }
        if(maxLen < count)
            maxLen = count;
        System.out.println(maxLen);
    }

    //100%
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] chars = line.toCharArray();
        int count = 0,maxLen = 0;
        int start = 0;
        boolean isOk = true;
        for(int i = 0;i < chars.length;i++){
            char c = chars[i];
            //判断是否重复字符出现
            for(int j = i-1;j >= start;j--){
                if(c == chars[j]){
                    isOk = false;
                    start = j+1;
                    break;
                }
            }
            if(isOk){
                count++;
            }else{
                if(maxLen < count)
                    maxLen = count;
                count=i-start+1;//更新count
                isOk = true;//设置为true
            }
        }
        if(maxLen <count) maxLen = count;//最后一次更新maxLen
        System.out.println(maxLen);
    }


        public static int maxString(String str) {
            if (str == null || str.length() == 0)
                return 0;
            char[] chars = str.toCharArray();
            int[] map = new int[256];
            for (int i = 0; i < 256; i++) {
                map[i] = -1;
            }
            int len = 0;
            int pre = -1;
            int cur = 0;
            for (int i = 0; i < chars.length; i++) {
                pre = Math.max(pre, map[chars[i]]);
                cur = i - pre;
                len = Math.max(len, cur);
                map[chars[i]] = i;
            }
            return len;
        }

        public static void main4(String[] args) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            System.out.println(maxString(str));
        }

}
