package leetcodeAlgorithm;

import org.junit.Test;

public class leetcode136SingleNumber_Easy {

    /*
    *
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4
    * */
    class Solution {
        public int singleNumber(int[] nums) {
            int singleNum = 0;
            for(int num:nums){
                singleNum ^= num;
            }
            return singleNum;
        }
    }

    @Test
    public void solutionTest(){
        int nums[] = {2,3,4,5,4,3,6,8,6,5,2};//数组int[] nums 或者int nums[]都可以
        int[] nums2 = {2,3,4,5,4,3,6,8,6,5,2};//
        int singleNum = new Solution().singleNumber(nums);
        System.out.println("singleNum = " + singleNum);
    }
}
