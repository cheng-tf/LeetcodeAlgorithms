package GreedyAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class Leetcode_45_JumpGameII_Hard {
    /******************Leetcode_45_JumpGameII_Hard********************/

    /**
     * 难度：Hard
     * https://leetcode.com/problems/jump-game-ii/description/
     * Given an array of non-negative integers, you are initially positioned
     * at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * Example:
     * Input: [2,3,1,1,4]   Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Note: You can assume that you can always reach the last index.
     *
     * 思路分析：在上一个JumpGame的基础上改进;前提已经假定每一次都可以到达终点了。
     *           目标：寻找最少跳跃次数。
     *           根据贪心规律：只需要每次跳跃最远，最终跳跃次数就是最少的。
     *           因此，只需要保证每跳一次，保证跳跃最远距离即可。
     *           具体实现：
     *           首先还是计算每一点可跳至的最远点index，遍历jump至当前可跳至的最远点，每跳一步，判断下一次跳跃最远点，
     *           从而不断更新canJumpMaxIndex；在此基础上，找出最后一个更新了canJumpMaxIndex的点，该点就是此次选择跳跃的点。
     *           依次类推，最终统计跳跃次数。
     *
     *           可以利用两次while循环实现，更容易理解；
     *           也可以利用一次for循环或while循环实现.
     *           如果要求不能开辟数组，可以利用nums[i]+i代替index[i].
     *
     *           改进：打印出最短跳跃次数对应的跳跃路径。
     */
    public int jump(int[] nums) {
        if(nums == null|| nums.length <= 1) return 0;//边界情况
        int[] index = new int[nums.length];//存放每一点可跳至最远点
        int maxIndex = nums.length-1;
        for(int i = 0;i <= maxIndex;i++)
            index[i] = i + nums[i];
        int jump = 0,canJumpCurrentMaxIndex = index[0],canJumpNextMaxIndex = index[0];
        int jumpCount = 0;//跳跃次数
        //利用嵌套循环实现：更容易理解。可以将内部while循环整合到大循环之中，jump2
        while(jump <= maxIndex){
            //当前可跳范围大于maxIndex，直接返回
            if(canJumpCurrentMaxIndex >= maxIndex) return jumpCount+1;
            while(jump <= canJumpCurrentMaxIndex){//从jump遍历到canJumpMaxIndex
                if(index[jump] > canJumpNextMaxIndex)
                    canJumpNextMaxIndex = index[jump];//保存下一步可跳最远的点
                jump++;
            }
            //while循环出来之后，jump=canJumpCurrentMaxIndex+1，即下一跳点的遍历起点
            jumpCount++;//找到了当前跳的点
            canJumpCurrentMaxIndex = canJumpNextMaxIndex;
        }
        return jumpCount+1;
    }

    /**
     *     简化实现
     */
    public int jump2(int[] nums) {
        if(nums == null|| nums.length <= 1) return 0;//边界情况
        int[] index = new int[nums.length];//存放每一点可跳至最远点
        int maxIndex = nums.length-1;
        for(int i = 0;i <= maxIndex;i++)
            index[i] = i + nums[i];
        int jump = 0,canJumpCurrentMaxIndex = nums[0],canJumpNextMaxIndex = nums[0];
        int jumpCount = 0;//跳跃次数
        //基于for循环实现，这种方式
        for(int i = 1;i <= maxIndex; i++){
            if(canJumpCurrentMaxIndex >= maxIndex) return jumpCount+1;//增加这个判断可以简化最后的循环次数
            if(i > canJumpCurrentMaxIndex){//找到了上次需要跳点
                jumpCount++;
                canJumpCurrentMaxIndex = canJumpNextMaxIndex;
            }
            if(index[i] > canJumpNextMaxIndex){//更新下次可跳最远点
                canJumpNextMaxIndex = index[i];
            }
        }
        return jumpCount+1;
    }



    /***
     * 添加新功能：打印最少跳跃点的路径
     */
    public int jumpAndRoute(int[] nums) {
        if(nums == null|| nums.length <= 1) return 0;//边界情况
        int[] index = new int[nums.length];//存放每一点可跳至最远点
        int maxIndex = nums.length-1;
        for(int i = 0;i <= maxIndex;i++)
            index[i] = i + nums[i];
        int jump = 0,canJumpCurrentMaxIndex = index[0],canJumpNextMaxIndex = index[0];
        int jumpCount = 0;//跳跃次数
        ArrayList<Integer> route = new ArrayList<Integer>();
        int jumpPoint = 0;
        route.add(jumpPoint);
        while(jump <= maxIndex){
            if(canJumpCurrentMaxIndex >= maxIndex) {
                route.add(maxIndex);//添加终点
                System.out.println("跳跃路径 = " + route.toString());
                return jumpCount+1;//下一次可跳范围大于maxIndex，直接返回
            }
            while(jump <= maxIndex && jump <= canJumpCurrentMaxIndex){//从jump遍历到canJumpMaxIndex
                if(index[jump] > canJumpNextMaxIndex){
                    jumpPoint = jump;
                    canJumpNextMaxIndex = index[jump];//保存下一步可跳最远的当前点
                }
                jump++;
            }
            route.add(jumpPoint);
            jumpCount++;
            canJumpCurrentMaxIndex = canJumpNextMaxIndex;
        }
        System.out.println("跳跃路径 = " + route.toString());
        return jumpCount+1;
    }

    //测试
    @Test
    public void test(){
        int[] nums = {2,3,1,1,4};
        int result = jump(nums);
        System.out.println("result = " + result);

        int[] nums2 = {1,2};
        int result2 = jump(nums2);
        System.out.println("result2 = " + result2);

        int[] nums21 = {2,3,1,1,4};
        int result21 = jump2(nums21);
        System.out.println("result21 = " + result21);

        int[] nums22 = {1,2};
        int result22 = jump2(nums22);
        System.out.println("result22 = " + result22);

        int[] nums11 = {2,3,1,1,4};
        int result11 = jumpAndRoute(nums11);
        System.out.println("result = " + result11);

        int[] nums12 = {1,2};
        int result12 = jumpAndRoute(nums12);
        System.out.println("result12 = " + result12);
    }

}
