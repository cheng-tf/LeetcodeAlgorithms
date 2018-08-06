package BiShiAlgorithms;

import org.junit.Test;

import java.util.Scanner;

public class PinDuoDuo0805 {
    /**
     * the first problem
     * 旋转的字符串
     */
    public static void main_first(String[] args) {//运行时去掉_first
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        int K = chars.length/4;
        char[] spaces = new char[K-1];
        for(int i = 0;i<K-1;i++){
            spaces[i] = ' ';
        }
        String spaceStr = new String(spaces);
        for(int i = 0;i < K+1;i++){//控制行数

                if(i == 0){
                    System.out.println(str.substring(0,K+1));
                }else if(i >0 && i < K){
                    System.out.println(chars[4*K-i]+spaceStr+chars[K+i]);
                }else{
                    System.out.println(new StringBuilder(str.substring(2*K,3*K+1)).reverse().toString());
                }
        }
    }



        /**
     * the second problem
     * 有趣的变换
         * 比较优化的方法
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numStr = scanner.nextLine();
        char[] nums = numStr.toCharArray();
        int count = 0;
        for(int i = 0;i < nums.length-1;i++){
            int leftCount = halfCount(nums,0,i);
            if(leftCount == 0) continue;//提高效率；左段不存在；右段就不用统计了
            int rightCount = halfCount(nums,i + 1,nums.length-1);
            count += leftCount*rightCount;
        }
        System.out.println(count);
    }

    public static int halfCount(char[] nums,int from ,int end){
        if(from == end) return 1;//一位数肯定就一种
        if(nums[from] == '0'&& nums[end] == '0') return 0;//前后都有0，肯定不合法
        if(nums[from] == '0'|| nums[end] == '0') return 1;//以0开头只能为0.xxx;以0结尾只能为整数
        return end-from+1;//如345,：345,3.45,34.5
    }






    /**
     * the second problem
     * 比较笨重的方法
     * 有趣的变换
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numStr = scanner.nextLine();
        char[] nums = numStr.toCharArray();
        int count = 0;
        int len = nums.length;
        for(int i = 0;i < len -1;i++){//从[1,len-1],[2,len-2],...,[len-1,1]
            int preCount = 0,sufCount = 0;
            for(int point = -1;point < i; point ++) {
                if (isLegal(nums, 0, i, point)) preCount ++;
            }
            for(int point2 = i; point2 < len-1; point2 ++){
                if (isLegal(nums, i+1, len-1, point2)) sufCount++;
            }
            count += preCount*sufCount;
        }
        System.out.println(count);
    }

    /**
     * 判断nums[from]--nums[end]组成的数字是否合法,point表示小数点位置
     * @return
     */
    public static boolean isLegal(char[] nums,int from ,int end,int point){
        //一位数情况：肯定返回ture;(此时必有point<from，但是point<from不一定只是一位数)
        if(from == end) return true;//只有1位数，肯定返回true
        //以下是多位数情况
        if(point < from) {//多位整数
           return nums[from] != '0'? true :false;
        }
        //下面是小数情况
        if(nums[end] == '0') return false;//如果以0结尾，必定返回false
        if(nums[from]=='0'){//如果以0开头，必须0.xxx才可以
            if(point != from ) return false;//当前必须是小数点，否则返回false
        }
        return true;
    }

}
