package DynamicProgramming_DP;

import org.junit.Test;

/**
 * @Description 注释
 * @Author zhaohong
 * @Time 2019-03-22 13:05
 */
public class JianzhiOffer_14_FindMaximumProductOfCutRope {
    /**
     * JianzhiOffer_14_FindMaximumProductOfCutRope: 剪绳子
     * 题目介绍：给你一根长度为n的绳子，请把绳子剪成m段(m,n都是整数，n>1并且m>1),
     * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度为2,3,3的三段，此时得到的最大乘积是18。
     * 两种算法：动态规划和贪婪算法。
     * 算法1：动态规划。
     *      令f[n]表示长度为n的绳子剪成若干段之后的最大乘积，则f[n]=max{f[i]*f[n-i]};
     * 这是一个从上至下的递归公式。由于递归会有很多的子问题，带来大量不必要的重复计算，更好办法是
     * 按照从下至上的顺序，得到f[n]。也就是从f[2]、f[3]...f[n]。创建dpProd数组保存子问题的结果。
     * 算法2：贪婪算法。
     *      贪婪策略：当n大于等于5时，尽可能多地剪长度为3的绳子；
     *      当剩下的绳子长度为4时，把绳子剪成两段长度为2的绳子。
     *      (目的:为了照顾绳子长度为4时至少剪一次。若大于4，直接不用剪成两段了)。
     *      数学证明：当n>=5时,3(n-3)>n;2(n-2)>n;且3(n-3)>=2(n-2)。
     */

    /**
     * 算法1：动态规划思路
     */
    public int findMaximumProductOfCutRope(int ropeLen) {
        if (ropeLen <= 1) {
            return 0;
        } else if (ropeLen == 2) {
            return 1;
        } else if (ropeLen == 3) {
            return 2;
        }
        int[] dpProd = new int[ropeLen + 1];
        dpProd[0] = 0;
        dpProd[1] = 1;
        dpProd[2] = 2;
        dpProd[3] = 3;
        int perMax, product;
        for (int i = 4; i <= ropeLen; i++) {
            perMax = 0;//每一轮都要初始化为0，否则前一轮对后一轮有影响
            for (int j = 1; j <= i >> 1; j++) {//必须是<=
                product = dpProd[j] * dpProd[i - j];
                if (product > perMax) {
                    perMax = product;
                }
            }
            dpProd[i] = perMax;
        }
        return dpProd[ropeLen];
    }

    /**
     * 算法2：贪婪算法
     */
    public int findMaximumProductOfCutRope2(int ropeLen) {
        if (ropeLen <= 1) {
            return 0;
        } else if (ropeLen == 2) {
            return 1;
        } else if (ropeLen == 3) {
            return 2;
        }
        //尽可能多地剪去长度为3的绳子段
        int timesOf3 = ropeLen / 3;
        //当绳子最后剩下长度为4的时候，不再剪去长度为3的绳子段
        if (ropeLen % 3 == 1) {
            timesOf3--;
        }
        int timesOf2 = (ropeLen - timesOf3 * 3) >> 1;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    /****************************测试***************************/
    @Test
    public void test() {
        for (int i = 0; i < 20; i = i + 2) {
            System.out.println("findMaximumProductOfCutRope(i) = " + findMaximumProductOfCutRope(i));
            System.out.println("findMaximumProductOfCutRope2(i) = " + findMaximumProductOfCutRope2(i));
            System.out.println(findMaximumProductOfCutRope(i) == findMaximumProductOfCutRope2(i));
        }
    }
}
