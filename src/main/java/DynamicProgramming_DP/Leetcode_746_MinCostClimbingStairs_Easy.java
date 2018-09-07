package DynamicProgramming_DP;

import org.junit.Test;

public class Leetcode_746_MinCostClimbingStairs_Easy {
    /*************************Leetcode_746_MinCostClimbingStairs_Easy************************/

    /**
     * 难度：Easy
     * 类型分类：动态规划DynamicProgramming
     * https://leetcode.com/problems/min-cost-climbing-stairs/description/
     *
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     *
     * Once you pay the cost, you can either climb one or two steps.
     * You need to find minimum cost to reach the top of the floor,
     * and you can either start from the step with index 0, or the step with index 1.
     *
     * Example 1:
     * Input: cost = [10, 15, 20]   Output: 15
     * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     * Example 2:
     * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]    Output: 6
     * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     * Note:
     *      cost will have a length in the range [2, 1000].
     *      Every cost[i] will be an integer in the range [0, 999].
     *
     * 思路分析：动态规划思路。
     *           题意理解：cost长度为len，则共len台阶，top相当于是len+1台阶。
     *           要想爬上top，需要计算之前的花费总和。
     *           编号从0开始，则top对应下标为len。
     *           爬上第n台阶的总花费，相当于爬上第n-1台阶的花费再加上n-1台阶本身的花费或者
     *           爬上第n-2台阶的花费再加上n-2台阶本身的花费；
     *           由于要获取最小的花费，取两种情况的最小值即可。
     *           那么爬上top的总花费就是爬上len-1台阶的花费再加上len-1台阶本身的花费与
     *           爬上len-2台阶的花费再加上len-2台阶本身的花费的较小者。
     *           定义：cost[n]表示第n台阶本身的花费；dp[n+1]表示爬上n+1花费的最小代价。
     *           动态规划状态转换为：
     *           dp[n+1] = min{dp[n]+cost[n],dp[n-1]+cost[n-1]};n>=1
     *           dp[0+1] = cost[0];
     *           为了节省内存空间，从低到高不断更新cost，即cost[n-1]=dp[n]；
     *           因此最后需要dp[n]，返回cost[n-1]和cost[n-2]的最小值即可。
     */

    public int minCostClimbingStairs(int[] cost) {
        if(cost == null||cost.length == 2) return 0;
        if(cost.length == 1) return cost[0];//边界情况
        int len = cost.length;
        for(int i = 2;i < len;i++)
            cost[i] += (cost[i-1]<cost[i-2]?cost[i-1]:cost[i-2]);//或者使用Math.min()方法
        return  cost[len-1]<cost[len-2]?cost[len-1]:cost[len-2];
    }

    //测试
    @Test
    public void test(){
        int[][] costs = {{10, 15, 20},{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}};
        for(int i = 0;i < costs.length;i++){
            int result = minCostClimbingStairs(costs[i]);
            System.out.println("result = " + result);
        }
    }


}
