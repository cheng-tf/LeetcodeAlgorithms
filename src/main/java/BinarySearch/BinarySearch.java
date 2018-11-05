package BinarySearch;

import org.junit.Test;

public class BinarySearch {

    /**************************二分查找<最基本的>*******************************/
    /**
     * 二分查找: 最普通的二分查找，若存在多个相同的值(有序则索引必连续)，返回的索引无法确定
     * 1. 循环实现；
     * 2. 递归实现；
     * 方法的参数：数组nums,low,high,target
     */

    /**
     * 循环实现
     */
    public int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {//这里包括等号，与快排不一样，快排不包括等号，二分查找必须包括等号，否则出错
            int mid = (low + high) >> 1;
            if (target < nums[mid]) high = mid - 1;
            else if (target > nums[mid]) low = mid + 1;
            else return mid;//返回查找的索引
        }
        return -1;//不存在则返回-1
    }

    /**
     * 递归实现
     */
    public int binarySearchRecursive(int[] nums, int low, int high, int target) {
        if (low > high) return -1;//递归终止条件
        int mid = (low + high) >> 1;
        if (target < nums[mid]) {
            return binarySearchRecursive(nums, low, mid - 1, target);
        } else if (target > nums[mid]) {
            return binarySearchRecursive(nums, mid + 1, high, target);
        } else {
            return mid;
        }
    }

    /**********************************测试**********************************/
    @Test
    public void test() {
        int[] nums = {0, 0, 0, 1, 2, 2, 2, 2, 2, 3, 8, 32, 54, 89, 100, 100, 100, 999};
        for (int target : nums) {
            //递归方法
            int result1 = binarySearchRecursive(nums, 0, nums.length, target);
            System.out.println("binarySearchRecursive = " + result1);
            //while循环
            int result2 = binarySearch(nums, 0, nums.length, target);
            System.out.println("binarySearch = " + result2);
        }
    }
}

