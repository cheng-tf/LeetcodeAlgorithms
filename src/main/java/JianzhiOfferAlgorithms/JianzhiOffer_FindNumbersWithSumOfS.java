package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class JianzhiOffer_FindNumbersWithSumOfS {
    /**
     * 剑指Offer  和为S的两个数字
     * https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&
     * tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 题目描述
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     * 输出描述:
     * 对应每个测试案例，输出两个数，小的先输出。
     * 思路分析：start和end两个索引，初始分别指向数组开头和末尾；
     * 若两者之和等于S，则计算两者乘积，若乘积小于上次的乘积，则保存到result中；一定要start++或end--；
     * 若两者之和小于S，则start++；
     * 若两者之和大于S，则end--。
     */

    /**
     * 通过保留最优解的下标索引
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] nums, int S) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length < 2) return result;
        int left = 0, right = nums.length - 1;
        int minLeft = -1, minRight = -1;
        int currentProduct = Integer.MAX_VALUE;
        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum < S) {
                left++;
            } else if (currentSum > S) {
                right--;
            } else {
                int product = nums[left] * nums[right];
                if (product < currentProduct) {
                    currentProduct = product;//容易忽略
                    minLeft = left;
                    minRight = right;
                }
                right--;//或者start++，容易忽略
            }
        }
        if (minLeft != -1) {
            result.add(nums[minLeft]);
            result.add(nums[minRight]);
        }
        return result;
    }

    /**
     * 直接更新ArrayList
     */
    public ArrayList<Integer> FindNumbersWithSum2(int[] nums, int S) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length < 2) return result;
        int left = 0, right = nums.length - 1;
        int currentProduct = Integer.MAX_VALUE;
        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum < S) {
                left++;
            } else if (currentSum > S) {
                right--;
            } else {
                int product = nums[left] * nums[right];
                if (product < currentProduct) {
                    currentProduct = product;//容易忽略
                    result.add(0, nums[left]);//更新
                    result.add(1, nums[right]);
                }
                right--;//或者start++，容易忽略
            }
        }
        return result;
    }

    /****************************测试**************************/
    @Test
    public void test() {
        int[] nums = {2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 12, 18};
        ArrayList<Integer> result = FindNumbersWithSum(nums, 12);
        System.out.println(result.toString());
    }

    /************------------剑指Offer:和为S的正数S序列----------------***************************/

    /**
     * https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&tPage=1&
     * rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 剑指Offer:和为S的正数S序列
     * <p>
     * 思路分析：left 和right表示连续正数的左右边界；
     * 重点1：截止条件，由于至少两个数且连续，因此只需要right小于(sum+1)>>1即可；
     * 重点2：当前和currSum与sum的比较处理，三种情况:
     *        当currSum>sum,left前进，currSum减去left；
     *        当currSum<sum,right前进，currSum加上right；
     *        当currSum==sum,添加结果，然后再left前进，currSum减去left；
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if (sum <= 2) return results;
        int left = 1, right = 2;
        int currSum = left + right;
        while (right <= (sum + 1 >> 1)) {
            if (currSum < sum) {
                right++;
                currSum += right;
            } else if (currSum > sum) {
                currSum -= left;
                left++;
            } else {
                ArrayList<Integer> result = new ArrayList<Integer>();
                for (int i = left; i <= right; i++) result.add(i);
                results.add(result);
                currSum -= left;
                left++;
            }
        }
        return results;
    }

}
