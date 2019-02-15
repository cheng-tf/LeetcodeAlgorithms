package BitOperationsAlgorithms;

import org.junit.Test;

/**
 * @Description 注释
 * @Author zhaohong
 * @Time 2019-02-15 14:19
 */
public class Leetcode461HammingDistance_Easy {
    /**
     * 题目描述：
     * https://leetcode.com/problems/hamming-distance/description/
     * 题目难度：Easy
     * The Hamming distance between two integers is the number of positions
     * at which the corresponding bits are different.
     * Given two integers x and y, calculate the Hamming distance.
     *
     * Note: 0 ≤ x, y < 231.
     * Example:
     * Input: x = 1, y = 4
     * Output: 2
     * Explanation:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     * The above arrows point to positions where the corresponding bits are different.
     *
     * 算法分析：
     * 算法1：将x，y逐步右移1位获取最低位，比较是否相等，若不相等则计数加1，控制循环移位次数。
     *       这种算法的复杂度较高，对于任何情况下都需要移位31次，不是最优的。
     * 算法2：首先将x，y异或一次，然后统计异或结果中1的个数即为汉明距离。这里统计一个数中比特1的个数，采用的是减1相与的算法。
     */
    // 算法1
    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int i = 0; i < 31; i++) {
            if((x&1) != (y&1)) count++;
            x >>= 1;
            y >>= 1;
        }
        return count;
    }

    // 算法2
    public int hammingDistance2(int x, int y) {
        int count = 0;
        int z = x^y;
        while(z != 0){
            count++;
            z &= z-1;
        }
        return count;
    }

    @Test
    public  void test(){
        int x = 1,y = 4;
        int result = hammingDistance(x,y);
        System.out.println("result = " + result);

        int result2 = hammingDistance2(x,y);
        System.out.println("result2 = " + result2);
    }
}
