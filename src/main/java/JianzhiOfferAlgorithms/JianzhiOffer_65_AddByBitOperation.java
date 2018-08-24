package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_65_AddByBitOperation {

    /**********************剑指Offer65：不用加减乘除做加法*********************/
    /**
     * 题目描述：
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     *
     * 思路分析：借助十进制加法规则，某一位上a+b+carray
         * 不考虑进位情况 ：0+0=0；1+0=1；1+1=0；0+1=1；满足异或规律；
         * 进位情况：只有1+1有进位，其他无进位，满足&规律。
         * 因此，利用异或获取不进位的结果，然后再与、左移一位，在和之前结果相加即可，反复执行，
         * 直到进位为0即可。
     */

    public int Add(int num1,int num2) {
        int sum = num1;
        while(num2 != 0){
            sum = num1^num2;//不考虑进位的和
            num2 = (num1&num2) <<1;//进位carry,注意加括号，位运算优先级大于逻辑运算
            num1 = sum;
        }
        return sum;
    }


    //测试
    @Test
    public void test(){
        int num1 = 111;
        int num2 = 899;
        int sum = Add(num1,num2);
        System.out.println("sum = " + sum);
    }


}
