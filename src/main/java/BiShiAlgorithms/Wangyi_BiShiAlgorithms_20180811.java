package BiShiAlgorithms;
import org.junit.Test;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Wangyi_BiShiAlgorithms_20180811 {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String firstLine = in.nextLine();
            String secondLine = in.nextLine();
            String thirdLine = in.nextLine();
            int[] firstNums = helper(firstLine);
            int[] secondNums = helper(secondLine);
            int[] thirdNums = helper(thirdLine);
            if(firstNums[0]==0)
                System.out.println(0);
            int sum = 0;
            //计算所有为1的总分数
            for(int i = 0;i < firstNums[0];i++){
                if(thirdNums[i]==1){
                    sum += secondNums[i];
                }
            }
            if(firstNums[1]==1)
                System.out.println(sum);
            //用于遍历所有
            int sumBase = 0;
            for (int j = 0;j < firstNums[0]; j++ ){//遍历所有
                if(thirdNums[j] == 1) continue;
                //下面是 为0的
                int count = firstNums[1];
                int sum2 = 0;
//                int sum2 = secondNums[j];
                for(int k = 0;k<count&&j+k<firstNums[0];k++){
                    if(thirdNums[j+k] == 0){
                        sum2 += secondNums[j+k];
                    }
                }
                if(sum2 > sumBase) sumBase = sum2;
            }
            System.out.println(sum+sumBase);
        }

        public static int[] helper(String line){
            String[] lineStrs = line.split(" ");
            int[] nums = new int[lineStrs.length];
            for(int i = 0;i<lineStrs.length;i++){
                nums[i] = Integer.valueOf(lineStrs[i]);
            }
            return nums;
        }


        /*********************利用滑动窗口解决瞌睡问题*******************************/



    /**
     * 思路分析：利用滑动窗口解决
     * @param n 一堂课的分钟数
     * @param k 叫醒一次清醒分钟数
     * @param isSleep 每分钟是否睡着
     * @return  返回最大的总分数
     */
    public int maxSumValue(int n,int k,int[] perValues,int[] isSleep){
        int maxSumValue = 0;
        //计算所有清醒的总分数
        for(int i = 0;i < n ;i++)
            maxSumValue += perValues[i]*isSleep[i];
        //初始化第一个窗口
        for(int i = 0;i < k; i++)
            maxSumValue += perValues[i]*(1-isSleep[i]);
        for(int i = k;i < n;i++){
            int add = perValues[i]*(1-isSleep[i]) - perValues[i-k]*(1-isSleep[i-k]);
            if(add > 0)  maxSumValue += add;
        }
        return maxSumValue;
    }

    /********************Java基础题目***************************/
    public void IntegerLong(){
        Long a = 1024L;
        int b = 1024;
        Integer c = null;
        System.out.println("(a==b) = " + (a == b));//int b自动转型为Long对象，故是true
        System.out.println("b.equals(a) = " + a.equals(b));//
        System.out.println("(b==c) = " + (b==c));//报错，类型不一致
    }



    public void test2(){

        Integer a = 1;
        Integer b = 2;
        //写一个函数，交换a和b的值
        System.out.println("a="+a +";b="+b);//a=2;b=1
    }



    @Test
    public void test(){
        int n = 6,k =3;
        int[] perValues = {1,3,5,2,5,4};
        int[] isSleep =   {1,1,0,1,0,0};
        int maxSumValue = maxSumValue(n,k,perValues,isSleep);
        System.out.println("maxSumValue = " + maxSumValue);

        IntegerLong();
    }






}
