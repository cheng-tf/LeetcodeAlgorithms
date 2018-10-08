package BinarySearch;

import org.junit.Test;

import java.util.Arrays;

public class JianzhiOffer_53_SearchNumberInSortedArray_34_SearchRange_Easy {
    /***************************************************/
    /**
     * T1:寻找范围,LeetCode_34_FindFirstAndLastPositionOfElementInSortedArray
     * T2:找出target在数组nums中出现的个数;
     */

    /*******************T1:LeetCode_34_FindFirstAndLastPositionOfElementInSortedArray***************************/
    /**
     * 题目：LeetCode_34_FindFirstAndLastPositionOfElementInSortedArray
     * 难度：Easy
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
     *
     * 题目介绍
     * Given an array of integers nums sorted in ascending order,
     * find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     * Example 2:
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     *
     *
     * 思路分析：
     * 方法1：左右遍历法。
     * 在二分查找的基础上向左右遍历。
     * 方法2：基于二分查找方法，分别找到第一个位置和最后一个位置；
     */

    /**
     * SearchRange：综合方法
     */
    public int[] binarySearchRange(int[] nums,int low,int high,int target){
        int low1 = low,high1 = high;
        int[] result = {-1,-1};
        //寻找第一个索引：FirstPosition
        while(low <= high){
            int midIndex = (low + high)/2;
            if(target<nums[midIndex]){
                high = midIndex -1;
            }else if (target > nums[midIndex]){
                low = midIndex + 1;
            }else{
                while(--midIndex>=low && target==nums[midIndex]);
                result[0] = midIndex + 1;
                break;
            }
        }
        low = low1;
        high = high1;
        //寻找最后一个索引：LastPosition
        while(low <= high){
            int midIndex = (low + high)/2;
            if(target < nums[midIndex]){
                high = midIndex - 1;
            }else if(target > nums[midIndex]){
                low = midIndex + 1;
            }else{
                while(++midIndex <= high&&target==nums[midIndex]);
                result[1] = midIndex-1;
                return result;
            }
        }
        return result;
    }


    /**
     * 获取范围1
     */
    public int[] binarySearchRange1(int[] nums,int low,int high,int target){
        int[] result = new int[2];
        result[0] = binarySearchLeft1(nums,low,high,target);
        result[1] = binarySearchRight1(nums,low,high,target);
        return result;
    }
    /**f
     * 获取范围2
     */
    public int[] binarySearchRange2(int[] nums,int low,int high,int target){
        int[] result = new int[2];
        result[0] = binarySearchLeft2(nums,low,high,target);
        result[1] = binarySearchRight2(nums,low,high,target);
        return result;
    }


    /**
     * 若数组中存在多个相同的值，返回最左边那个值的索引
     * 在二分查找的基础上，不断向左查找
     */
    public int binarySearchLeft1(int[] nums,int low,int high,int target){
        while(low <= high){//这里包括等号，与快排不一样，快排不包括等号，二分查找必须包括等号，否则出错
            int middleIndex = (low+high)/2;
            if(target < nums[middleIndex]){
                high = middleIndex - 1;
            }else if(target > nums[middleIndex]){
                low = middleIndex + 1;
            }else{
                while(--middleIndex >= low &&nums[middleIndex] == target);
                return middleIndex+1;//返回查找的索引
            }
        }
        return -1;//不存在则返回-1
    }

    /**
     * 判断是否到达左端点
     * 直接利用二分查找，查询最左边的索引
     */
    public int binarySearchLeft2(int[] nums,int low,int high,int target){
        while(low <= high){//这里包括等号，与快排不一样，快排不包括等号，二分查找必须包括等号，否则出错
            int midIndex = (low+high)/2;
            if(target < nums[midIndex]){
                high = midIndex - 1;
            }else if(target > nums[midIndex]){
                low = midIndex + 1;
            }else{
                if(midIndex == low||target>nums[midIndex-1])
                    return midIndex;
                high = midIndex - 1;
            }
        }
        return -1;//不存在则返回-1
    }

    /**
     *若数组中存在多个相同的值，返回最右边那个值的索引
     */
    public int binarySearchRight1(int[] nums,int low,int high,int target){
        while(low <= high){//这里包括等号，与快排不一样，快排不包括等号，二分查找必须包括等号，否则出错
            int middleIndex = (low+high)/2;
            if(target < nums[middleIndex]){
                high = middleIndex - 1;
            }else if(target > nums[middleIndex]){
                low = middleIndex + 1;
            }else{
                while(++middleIndex <= high && nums[middleIndex] == target);
                return middleIndex-1;//返回查找的索引
            }
        }
        return -1;//不存在则返回-1
    }

    /**
     * 判断是否到达右端点
     * 直接利用二分查找，查询最右边的索引
     */
    public int binarySearchRight2(int[] nums,int low,int high,int target){
        while(low <= high){//这里包括等号，与快排不一样，快排不包括等号，二分查找必须包括等号，否则出错
            int middleIndex = (low+high)/2;
            if(target < nums[middleIndex]){
                high = middleIndex - 1;
            }else if(target > nums[middleIndex]){
                low = middleIndex + 1;
            }else{
                if(middleIndex == high||target<nums[middleIndex+1])
                    return middleIndex;
                low = middleIndex + 1;
            }
        }
        return -1;//不存在则返回-1
    }

    @Test
    public void test1(){
        int[] nums = {0,0,0,1,2,2,2,2,2,3,8,32,54,89,100,100,100,999};
        for(int target : nums){
            //测试左边界
            int indexLeft = binarySearchLeft1(nums,0,nums.length-1,target);
            //测试右边界
            int indexRight = binarySearchRight1(nums,0,nums.length-1,target);
            System.out.println("indexLeft = " + indexLeft + ";indexRight = " + indexRight);
        }
        //获取范围
        for(int target:nums){
            System.out.println("binarySearchRange = " + Arrays.toString(binarySearchRange(nums,0,nums.length-1,target)));
            System.out.println("binarySearchRange2 = " + Arrays.toString(binarySearchRange2(nums,0,nums.length-1,target)));
            System.out.println("binarySearchRange3 = " + Arrays.toString(binarySearchRange(nums,0,nums.length-1,target)));
        }
    }



    /*******************************T2：找出target在数组nums中出现的个数。********************************************/
    /**
     * T2：找出target在数组nums中出现的个数。
     * 题目类型：BinarySearch
     * <p>
     * 方法1：二叉查找基础上，两边遍历。
     * 若中间位置的值与target相等，向两边顺序扫描，直到向左到low或非target，向右到high或非target。
     * 该方法缺点：这种算法的在全是target的情况下时间复杂度为O(N)，与顺序扫描一样。
     * <p>
     * 方法2：二分查找基础上
     * 在数组nums全是target的情况下，方法1的时间复杂度为O(N)，而二分查找的复杂度为O(logN);
     * 如果只是寻找target连续的第一个位置和最后一个位置，作差加1就是次数，
     * 这样时间复杂度就是O(logN)
     * 二分查找的改进：
     * nums[mid]>target和nums[mid]<target都不变，
     * nums[mid]==target需要好好考虑下条件：
     * 需要返回mid的两种情况：mid==low或mid==high；或者nums[mid+1]!=target或nums[mid-1]!=target
     * 其他情况需要递归：high=mid-1或low=mid+1
     */

    /**
     * 方法1:两边遍历。
     */
    public int GetNumberOfK(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                //在二分查找的基础上，改变了相等的情况
                int mid1 = mid;
                while (mid1 >= low && nums[mid1] == target) mid1--;
                while (mid <= high && nums[mid] == target) mid++;
                return mid - mid1 - 1;
            }
        }
        return 0;//不存在情况下，返回0
    }
    /*******************************方法二****************************/
    /**
     * 方法2：分别找到第一个和最后一个位置。
     */
    public int getNumberOfK(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int firstIndex = getFirstIndexOfK(nums, target);//第一个出现的索引
        int lastIndex = getLastIndexOfK(nums, target);//第二个出现的索引
        return (firstIndex != -1 && lastIndex != -1) ? (lastIndex - firstIndex + 1) : 0;
    }

    /**
     * 方法2的辅助方法：获取第一个索引位置
     */
    public int getFirstIndexOfK(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;//1. 位移效率高；2. 求mid一定在while循环内，前几次在while循环外，导致无限循环
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                //相当于增加了苛刻的条件：条件1或条件2
                //找到第一个两种情况：1.mid==low，到达边界；2. 下一个元素不是target；其他情况需要继续循环
                if (mid == low || nums[mid - 1] != target) return mid;
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 方法2的辅助方法：获取最后一个索引位置
     */
    public int getLastIndexOfK(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                //相当于增加了苛刻的条件：条件1或条件2
                //找到第一个两种情况：1.mid==high，到达边界；2. 上一个元素不是target；其他情况需要继续循环
                if (mid == high || nums[mid + 1] != target) return mid;
                low = mid + 1;
            }
        }
        return -1;
    }

    //测试
    @Test
    public void test2() {
        int[] nums = {1, 2, 3, 3, 3, 3, 3, 3, 4, 8, 34, 223, 375, 375, 375};

        System.out.println(getNumberOfK(nums, 3));
        System.out.println(getNumberOfK(nums, 375));
        System.out.println(getNumberOfK(nums, 4));
        System.out.println(getNumberOfK(nums, 0));
    }

}
