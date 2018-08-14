package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_JumpFloor_Based_on_Fibonacci {

    /**************************************I. 跳台阶****************************************************/


    /**
     * 题目描述
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * 思路分析：跳n级台阶，分成两种情况，第一次跳1台阶后跳n-1台阶和第一次跳2台阶后跳n-2台阶，
     * 即正好满足斐波那契数列f(n)=f(n-1)+f(n-2)。
     */
    /**
     * 递归方法
     */
    public int JumpFloor(int n) {
        if(n<=0) return 0;
        if(n==1||n==2) return n;
        return JumpFloor(n-1)+JumpFloor(n-2);
    }

    /**
     * 非递归方法
     *
     */
    public int JumpFloor2(int n){
        if(n<=0) return 0;
        if(n == 1) return 1;
        int first  = 0,second = 1;
        while(n-- > 0){
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /**************************************II. 变态跳台阶*****************************************************/
    @Test
    public void test(){
        for(int i = 1;i < 10;i++){
            System.out.println(JumpFloorII(i));
            System.out.println(JumpFloorIIBasedOnFibonacci(i));
            System.out.println(JumpFloorIIBasedOnFibonacci2(i));
            System.out.println(JumpFloorIIBasedOnFibonacci3(i));
        }
    }

    /**
     * 题目描述
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    //方法1:直接利用公式
    //利用数学归纳方法证明
    public int JumpFloorII(int n) {
        return (int)Math.pow(2,n-1);
    }

    /**
     * 基于斐波那契数列计算
     * 台阶树：n
     * 若n=1，则返回1；{[1]}
     * 若n=2，则返回2；{[1,f(1)],[2]}
     * 若n=3，则返回3；{[1,f(2)],[2,f(1)],[3]}
     * 若n，则返回2^(N-1);{[1,f(n-1)],[2,f(n-2)],...,[n-1,f(1)],[n]}
     * 即f(n)=f(n-1)+f(n-2)+...+f(1)+1=2*f(n-1)
     * f(1)=2^(1-1);f(2)=2^(2-1);.....;数学归纳法，f(n) = 2^(n-1)
     * @param n
     * @return
     */
    public int JumpFloorIIBasedOnFibonacci(int n) {
        if(n <= 0) return 0;
        if(n==1||n==2) return n;
        int result = 0;
        while(--n>=0) result += JumpFloorIIBasedOnFibonacci(n);
        return result + 1;
    }

    /**
     * 递归方法：就是2倍的关系
     */
    public int JumpFloorIIBasedOnFibonacci2(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        return 2*JumpFloorIIBasedOnFibonacci(n-1);
    }

    /**
     * 非递归方法：就是2倍的关系
     */
    public int JumpFloorIIBasedOnFibonacci3(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        int result = 1;
        while(--n>0)  result *= 2;
        return result;
    }
}
