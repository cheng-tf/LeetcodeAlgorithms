package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_GetNumberOfK {


    
    @Test
    public void test(){
        int[] nums = {1,2,3,3,3,3,4,8,34,223,423,375,375};
        int num = GetNumberOfK2(nums,3);
        System.out.println("num = " + num);
    }
    
    
    /**
     * 方法1：二叉查找基础上
     * 若中间位置的值与target相等，向两边扩展，直到向左到low或非target，向右到high或非target。
     * 这种算法的在全是target的情况下时间复杂度为O(n)。
     */
    public int GetNumberOfK(int [] nums , int target) {
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(target < nums[mid]){
                high = mid-1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{//在二分查找的基础上，改变了相等的情况
                int mid1 = mid;
                while(mid1 >= low&&nums[mid1] == target) mid1--;
                while(mid <= high&&nums[mid] == target) mid++;
                return mid-mid1-1;
            }
        }
        return 0;//不存在情况下，返回0
    }
    /**
     * 方法2：二叉查找基础上
     * 由于方法1的时间复杂度为O(n)，而二分查找的复杂度为O(logn);
     * 如果只是寻找target连续的第一个位置和最后一个位置，作差加1就是次数，
     * 这样时间复杂度就是O(logn)
     */
    public int GetNumberOfK2(int [] nums , int target) {
        int firstIndex = getFirstIndexOfK(nums,target);
        int lastIndex = getLastIndexOfK(nums,target);
        return (firstIndex != -1 && lastIndex != -1) ? lastIndex-firstIndex +1 : 0;
    }

    /**
     * 获取第一个索引位置
     * @param nums
     * @param target
     * @return
     */
    public int getFirstIndexOfK(int[] nums,int target){
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(target < nums[mid]){
                high = mid-1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{
                if(mid == low) return mid;//保证后续mid>low
                if(nums[mid-1] != target) {
                    return mid;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }
    /**
     * 获取第一个索引位置
     * @param nums
     * @param target
     * @return
     */
    public int getLastIndexOfK(int[] nums,int target){
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(target < nums[mid]){
                high = mid-1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{
                if(mid == high) return mid;//保证后续mid>low
                if(nums[mid+1] != target) {
                    return mid;
                }else{
                    low = mid+1;
                }
            }
        }
        return -1;
    }


}
