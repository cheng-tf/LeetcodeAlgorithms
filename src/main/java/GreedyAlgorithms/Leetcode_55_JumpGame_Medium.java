package GreedyAlgorithms;

import org.junit.Test;

public class Leetcode_55_JumpGame_Medium {
    /**********************Leetcode_55_JumpGame_Medium*******************************/

    /**
     * 难度:Medium
     * https://leetcode.com/problems/jump-game/description/
     * Given an array of non-negative integers,
     * you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     *
     * Example 1:
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     *              jump length is 0, which makes it impossible to reach the last index.
     *
     *  思路分析：
     *
     */
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        if(nums.length == 1&&nums[0] >= 0) return true;
        int maxJump = nums.length-1;
        int[] index = new int[maxJump];
        for(int i = 0;i < maxJump;i++)
            index[i] = nums[i] + i;
        int jump = 0,canJumpMaxIndex = index[0];
        while(jump <= maxJump){//防止数组越界
            if(jump > canJumpMaxIndex) return false;
            if(index[jump] > canJumpMaxIndex)//更新可跳跃的最大位置
                canJumpMaxIndex = index[jump];
            jump++;//继续跳下一步
            if(canJumpMaxIndex >= maxJump) return true;//可跳范围大于最大边界，返回true
        }
        return false;
    }

    //测试
    @Test
    public void test(){
        int[] nums = {2,3,1,1,4};
        boolean result = canJump(nums);
        System.out.println("result = " + result);

        int[] nums2 = {3,2,1,0,4};
        boolean result2 = canJump(nums2);
        System.out.println("result2 = " + result2);

        int[] nums3 = {2,0,0};
        boolean result3 = canJump(nums3);
        System.out.println("result3 = " + result3);

        int[] nums4 = {0};
        boolean result4 = canJump(nums4);
        System.out.println("result4 = " + result4);
    }

}
