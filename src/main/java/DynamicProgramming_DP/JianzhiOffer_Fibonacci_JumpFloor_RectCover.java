package DynamicProgramming_DP;

import org.junit.Test;

public class JianzhiOffer_Fibonacci_JumpFloor_RectCover {
    /*****************************最基本思想：斐波那契数列******************************/
    /**
     * 题目描述：
     * 大家都知道斐波那契数列，现在要求输入一个整数n，
     * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。(n<=39)
     *
     * 思路分析：F(n)=F(n-1)+F(n-2);
     *           两种方法：递归和非递归。递归方法存在重复计算，效率较低；
     *                     非递归方法效率较高。
     */

    /**
     * 递归方法：利用递归算法计算斐波那契序列
     * 递归算法存在重复计算：如计算F(9)=F(8)+F(7);
     * 而F(8) 内部又要计算F(7),因此效率很低。一般使用循环方法
     */
    public int Fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    /**
     * 非递归方法
     * 循环算法： 不断更新前两个值
     */
    public int Fibonacci2(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int first = 0, second = 1;
        while (n-- >= 2) {
//            int third = first + second;
//            first = second;
//            second = third;
            //不需要使用第三变量
            second = first + second;
            first = second - first;
        }
        return second;
    }

    //测试
    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Fibonacci(i) = " + Fibonacci(i));
            System.out.println("Fibonacci2(i) = " + Fibonacci2(i));
        }
    }
    /**
     * 算法3：利用矩阵实现
     * 略
     */
    /******************************应用1：青蛙跳台阶***********************************/

    /**************************************I. 跳台阶****************************************************/
    /**
     * 题目描述
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * 思路分析：跳n级台阶，分成两种情况，第一次跳1台阶后跳n-1台阶和第一次跳2台阶后跳n-2台阶，
     * 即正好满足斐波那契数列f(n)=f(n-1)+f(n-2)。
     */
    /**
     * 递归方法
     */
    public int JumpFloor(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return n;
        return JumpFloor(n - 1) + JumpFloor(n - 2);
    }

    /**
     * 非递归方法
     */
    public int JumpFloor2(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int first = 0, second = 1;
        while (n-- > 0) {
//            int third = first + second;
//            first = second;
//            second = third;
            //不需要使用第三变量
            second = first + second;
            first = second - first;
        }
        return second;
    }

    /**************************************II. 变态跳台阶*****************************************************/
    /**
     * 题目描述
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    //方法1:直接利用公式
    //利用数学归纳方法证明
    public int JumpFloorII(int n) {
        return (int) Math.pow(2, n - 1);
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
     */

    /**
     * f(n)=f(n-1)+f(n-2)+...+f(1)+1=2*f(n-1)
     * @param n
     * @return
     */
    public int JumpFloorIIBasedOnFibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return n;
        int result = 0;
        while (--n >= 0)
            result += JumpFloorIIBasedOnFibonacci(n);
        return result + 1;
    }

    /**
     * 递归方法：就是2倍的关系
     * f(n)=f(n-1)+f(n-2)+...+f(1)+1=2*f(n-1)
     */
    public int JumpFloorIIBasedOnFibonacci2(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return 2 * JumpFloorIIBasedOnFibonacci2(n - 1);
    }

    /**
     * 非递归方法：就是2倍的关系
     * f(n)=f(n-1)+f(n-2)+...+f(1)+1=2*f(n-1)
     */
    public int JumpFloorIIBasedOnFibonacci3(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int result = 1;
//        while(--n>0)  result *= 2;
//        while(--n>0)  result <<= 1;//利用左移替代乘2
        //先计算2^n，再乘以result
        result *= power(2, n - 1);
        return result;
    }

    //利用递归实现base的n次方
    public int power(int base, int n) {
        if (n == 0) return 1;
        if (n == 1) return base;
        int result = power(base, n >> 1);//核心
        result *= result;//关键
        if ((n & 1) == 1) result *= base;
        return result;
    }

    //测试
    @Test
    public void test22() {
        for (int i = 0; i < 10; i++)
            System.out.println("power(3," + i + ") = " + power(3, i));
        for (int i = 1; i < 10; i++) {
            System.out.println(JumpFloorII(i));
            System.out.println(JumpFloorIIBasedOnFibonacci(i));
            System.out.println(JumpFloorIIBasedOnFibonacci2(i));
            System.out.println(JumpFloorIIBasedOnFibonacci3(i));
        }
    }


    /*************************应用场景2：剑指Offer:矩形覆盖*************************************/
    /**
     * 题目描述
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * <p>
     * 思路分析：也属于斐波那契数列问题。
     * f(n)=f(n-1)+f(n-2);
     * f(1)=1;f(2)=2;
     * 分析：2*n的大矩形：可以在覆盖了2*(n-1)的大矩形的基础上，竖着放置一个矩形；
     * 或者在2*（n-2）的基础上，横着放两个矩形，因此f(n)=f(n-1)+f(n-2);
     */
    //方法1：递归方法
    public int RectCover(int target) {
        if (target <= 0) return 0;
        if (target == 1 || target == 2) return target;
        return RectCover(target - 1) + RectCover(target - 2);
    }

    /**
     * 方法2：非递归方法
     */
    public int RectCover2(int target) {
        if (target <= 0) return 0;
        if (target == 1 || target == 2) return target;
        int first = 1, second = 2;
        while (target-- >= 3) {
//            int third = first + second;
//            first = second;
//            second = third;
            //不需要使用第三变量
            second = first + second;
            first = second - first;
        }
        return second;
    }

    //测试
    @Test
    public void test33() {
        int result = RectCover(4);
        System.out.println("result = " + result);
        int result2 = RectCover2(4);
        System.out.println("result2 = " + result2);
    }
}
