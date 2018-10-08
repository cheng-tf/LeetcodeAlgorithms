package BinarySearch;

import org.junit.Test;

public class JianzhiOffer_53_SearchNumberInSortedArray_2_GetMissingNumber {

    /**
     * 题目描述：找出0 -- n-1中缺失的数字SearchNumberInSortedArray
     * 长度为n-1的递增排序数组中所有数字都是唯一的，且都在0 -- n-1范围内。
     * 有且只有一个数字不在该数组中，找出这个数字。
     *
     * 分析：方法1：求和判断，时间复杂度为O(n);
     * 方法2：二分查找，第一个索引和元素值不相等的索引即可。
     */

    /**
     * 方法1：利用二分法查找缺失的数字
     * 0 -- n-1共n个数字，有一个长度为n-1的排序数组，每个数字都是惟一的，
     * 找出那个没有出现的数字。
     */
    public int getMissingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == mid) low = mid + 1;
            else {//只可能是nums[mid]<mid；不可能nums[mid]>mid
//                if(mid == low ) return low;
//                if(nums[mid-1]== mid-1) return mid;
                if (mid == low || nums[mid - 1] == mid - 1) return mid;
                high = mid - 1;
            }
        }
        return -1;
    }

    //测试
    @Test
    public void test() {
        int[] nums1 = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10};
        int[] nums = new int[100];
        for (int k = 1; k < 100; k += 3) {
            int num = 0;
            for (int i = 0; i < 100; i++) {
                if (i == k) nums[i] = ++num;
                else nums[i] = num;
                num++;
            }
            int missingNumber = getMissingNumber(nums);
            System.out.println("missingNumber = " + missingNumber);
        }
    }
}
