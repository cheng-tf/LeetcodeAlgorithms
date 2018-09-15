package leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode_15_3Sum_Medium {
    /**************************Leetcode_15_3Sum_Medium*****************************/
    /**
     * 难度：Medium
     * https://leetcode.com/problems/3sum/description/
     *
     * Given an array nums of n integers, are there elements a, b, c in nums
     * such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     * The solution set must not contain duplicate triplets.
     * Example:
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     * A solution set is:
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 思路分析：两种思路：一是利用HashMap,另一种是利用双指针。
     *
     *
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null|| nums.length <= 2) return result;
        HashMap<Integer,int[]> hashMap = new HashMap<Integer,int[]>();
        for(int i = 0;i < nums.length;i++){
            hashMap.clear();
            for(int j = i+1;j < nums.length;j++){

            }
        }
        return result;

    }
}
