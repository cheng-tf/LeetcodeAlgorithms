package BinarySearch;

import org.junit.Test;

public class LeetCode_35_SearchInsertPosition_Easy {
    /******************GetInsertionIndex*********************/
    /**
     * 题目介绍：二分查找的变形题目
     * 难度：Easy
     * <p>
     * 给定一个排序数组，无重复数字，指定一个数字target，
     * 返回应该插入数组的位置。
     * 如：nums={4,6,8,10,14,43};target = 11;返回4
     * target=3返回0；target=100；返回6；
     * <p>
     * 思路分析：
     * 基于二分查找的思路，在target > nums[mid]与target < nums[mid]两种情况下，
     * 作特殊判断处理。是否达到边界与左右两边的值大小关系。
     * 对于 target > nums[mid]这种情况：
     * 若mid==high到达边界，返回mid+1；
     * 若target< nums[mid+1]，返回mid+1；
     * 同理，对于target < nums[mid]这种情况：
     * 若mid==low到达边界，返回mid;
     * 若target>nums[mid-1]，返回mid。
     */

    public int searchInsert(int[] arr, int target) {//getInsertionIndex
        int low = 0;
        int high = arr.length - 1;
        while (true) {
            int mid = (low + high) >> 1;
            if (target > arr[mid]) {
                //两种情况下对应跳出：1、midIndex达到high，target大于数组中任何一个值,返回midIndex+1；
                // 2、arr[midIndex]< target < arr[midIndex + 1]，返回midIndex+1；
                if (mid == high || target < arr[mid + 1])
                    return mid + 1;
                low = mid + 1;
            } else if (target < arr[mid]) {
                //两种情况下对应跳出：1、midIndex达到low，target小于数组中任何一个值,返回midIndex；
                // 2、arr[midIndex-1]< target < arr[midIndex]，返回midIndex；
                if (mid == low || target > arr[mid - 1])
                    return mid;
                high = mid - 1;
            } else {
                //target == arr[midIndex]；直接返回midIndex
                return mid;
            }
        }
    }

    @Test
    public void test() {
        int[] arr = {1, 3, 4, 66, 73, 100};
        for (int i = 2; i < 122; i += 10) {
            System.out.println("searchInsert(arr,i) = " + i + "----" + searchInsert(arr, i));
        }

        int[] nums = {4, 6, 8, 10, 14, 43};
        int result = searchInsert(nums, 11);
        System.out.println("result = " + result);
        int result2 = searchInsert(nums, 100);
        System.out.println("result2 = " + result2);
    }

}
