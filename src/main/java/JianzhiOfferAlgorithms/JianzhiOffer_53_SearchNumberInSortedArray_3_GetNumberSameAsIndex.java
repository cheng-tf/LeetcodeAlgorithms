package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_53_SearchNumberInSortedArray_3_GetNumberSameAsIndex {


    /**
     * 测试
     */
    @Test
    public void test(){
        int[] nums = {-9,-2,1,2,3,5,7,8,10};
        int number = getNumberSameAsIndex(nums);
        System.out.println("number = " + number);
    }
    /**
     * 题目描述：数组中数值和下标相等的元素
     * 数组是单调递增的，每个元素都是整数且是唯一的。
     * 找出任意一个元素值与下标相等的元素，没有则返回-1。
     * 方法：利用二分查找
     * 相对于二分查找，只是更改了判断条件。
     * 基本的二分查找是nums[mid]与target作比较，
     * 这里是nums[mid]与mid作比较。
     */
    public int getNumberSameAsIndex(int[] nums){
        if(nums == null||nums.length == 0) return -1;
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)>>1;
            if(nums[mid] > mid){
                high = mid - 1;
            }else if(nums[mid]<mid){
                low = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
