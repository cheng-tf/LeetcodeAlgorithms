package SearchAlgorithm;

import org.junit.Test;

public class GetInsertionIndex {
    /******************GetInsertionIndex*********************/
    /**
     * 题目介绍：二分查找的变形题目
     * 给定一个排序数组，无重复数字，指定一个数字target，
     * 返回应该插入数组的位置。
     * 如：nums={4,6,8,10,14,43};target = 11;返回4
     * target=3返回0；target=100；返回6；
     *
     * 思路分析：
     *     基于二分查找的思路，在target > nums[mid]与target < nums[mid]两种情况下，
     *     作特殊判断处理。是否达到边界与左右两边的值大小关系。
     *    对于 target > nums[mid]这种情况：
     *                  若mid==end到达边界，返回mid+1；
     *                  若target<nums[mid+1]，返回mid+1；
     *   同理，对于target < nums[mid]这种情况：
     *                  若mid==start到达边界，返回mid;
     *                  若target>nums[mid-1]，返回mid。
     */

    public int getInsertionIndex(int[] nums,int target){
        int start = 0,end = nums.length-1;
        while(start <= end) {//一定是<=；否则对于很大的值，会返回-1
            int mid = (start + end) >> 1;
            if(target == nums[mid]){
                return mid;
            } else if (target > nums[mid]){
                if(mid == end|| target < nums[mid+1]){
                    return mid+1;
                }
                start = mid + 1;
            }else{
                if(mid == start||target > nums[mid-1]){
                    return mid;
                }
                end = mid-1;
            }
        }
        return -1;
    }

    //测试
    @Test
    public void test(){
        int[] nums = {4,6,8,10,14,43};
        int result = getInsertionIndex(nums,11);
        System.out.println("result = " + result);
        int result2 = getInsertionIndex(nums,100);
        System.out.println("result2 = " + result2);
    }
}
