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
     *  思路分析：首先根据nums计算每一点可以达到的最远点，存放到新的数组index；
     *            需要定义一个int变量canJumpMaxIndex，存放可跳至最远的点；然后每次都往后跳一步，
     *            不断更新canJumpMaxIndex，直到canJumpMaxIndex大于等于maxIndex就返回true；
     *            若跳的下一步jump大于了可跳至最远点canJumpMaxIndex，则返回false，
     *            while循环条件是jump<maxIndex，防止数组下标越界。
     *            若
     *            示例：
     *            下标 = 0 1 2 3 4
     *            nums = 2 3 1 1 4
     *            index= 2 4 3 4 8，分析过程：下标为0，可跳至下标2的点，canJumpMaxIndex=2；
     *            canJumpMaxIndex小于maxIndex，继续跳下一步；
     *            下一步跳至索引为1的点，index[1]>maxJumpMaxIndex,更新maxJumpMaxIndex=4，
     *            即可跳至索引为4的点，满足canJumpMaxIndex>=maxIndex，返回true。
     */
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
//        if(nums.length == 1&&nums[0] >= 0) return true;//后面已经涵盖
        int[] index = new int[nums.length];//用于存放某点可跳的最大的点
        int maxIndex = nums.length-1;//最大索引，即最后一点
        for(int i = 0;i <= maxIndex;i++)
            index[i] = nums[i] + i;//计算每一点可跳至的最远点
        int jump = 0,canJumpMaxIndex = index[0];
        while(jump <= maxIndex){//防止数组越界
            if(jump > canJumpMaxIndex) return false;
            if(index[jump] > canJumpMaxIndex) canJumpMaxIndex = index[jump];//更新可跳跃的最大位置
            jump++;//继续跳下一步
            if(canJumpMaxIndex >= maxIndex) return true;//可跳范围大于最大边界，返回true
        }
        return true;//一般到不了这里，所以返回true或false都可以
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
