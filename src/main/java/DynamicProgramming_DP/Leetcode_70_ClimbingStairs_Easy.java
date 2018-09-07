package DynamicProgramming_DP;

import org.junit.Test;

public class Leetcode_70_ClimbingStairs_Easy {

    /***********************Leetcode_70_ClimbingStairs_Easy*****************************/

    /**
     * 难度：Easy
     * https://leetcode.com/problems/climbing-stairs/description/
     * 题目介绍：
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Note: Given n will be a positive integer.
     * Example 1:  Input: 2   Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:  Input: 3   Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     * 思路分析：动态规划思想。
     *           f(n) = f(n-1) + f(n-2);
     *           f(1) = 1;
     *           f(2) = 2;
     *          凡是属于动态规划思路可以解决的问题，最好用dp表示出来，
     *          面试官一看就知道采用的是DynamicProgramming思想。
      */
    public int climbStairs(int n) {
        if(n < 0) return 0;
        if(n <= 2) return n;
        int[] dp = new int[n+1];
        for(int i = 0;i < 3;i++)//初始化,n必须大于2
            dp[i] = i;
        for(int i = 3;i <= n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    //测试
    @Test
    public void test(){
        for(int i = 0;i < 10;i++){
             int result = climbStairs(i);
            System.out.println("result = " + result);
        }
    }


}
