package BiShiAlgorithms;

import java.util.Scanner;

public class Disifanshi_0909 {
    /**
     * 实现两个数字的乘积
     * 第一行：数字字符串
     * 第二行：数字字符串
     * 输出：
     * 乘积结果
     * 如  输入：1024
     *           2
     *     输出：2048
     */
    public class Main{

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        int a = 0,b = 0,c = 0,d = 0;
        char[] nums1 = line1.toCharArray();
        char[] nums2 = line2.toCharArray();
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = Math.min(len1,len2);
        int index = 0;
        int[] result = new int[len1+len2];
        for(int i = len2-1;i >=0;i--){
            a = nums2[i]-'0';
            index = len1+len2-1 - (len2 -1) + i;
            for(int j = len1-1;j>=0;j--){
                b = nums1[j]-'0';
                d = b*a + c;
                result[index--] += d%10;
                c = d/10;
            }
            result[index] += c;
        }
        StringBuilder sb = new StringBuilder();
        int kk = 0;
        for(;kk<result.length;kk++){
            if(result[kk] != 0) break;
        }
        for(int i = kk;i < result.length;i++){
            sb.append(result[i]);
        }
        if(sb.length()==0) sb.append(0);//结果为0的情况
        System.out.println(sb.toString());
    }
}
