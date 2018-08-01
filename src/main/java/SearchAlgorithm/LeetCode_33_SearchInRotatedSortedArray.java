package SearchAlgorithm;

import org.junit.Test;

public class LeetCode_33_SearchInRotatedSortedArray {
    @Test
    public void test(){
        int[] nums = {23,25,34,56,199,2,5,6,10};
        for(int i = 0;i < nums.length;i++){
            int target = nums[i];
            int index = binarySearchInRotatedSortedArray(nums,0,nums.length-1,target);
            System.out.println("target+\";index = \" + index = " + target+";index = " + index);
        }
        int target = 66;
        int index = binarySearchInRotatedSortedArray(nums,0,nums.length-1,target);
        System.out.println("target+\";index = \" + index = " + target+";index = " + index);

    }

    public int binarySearchInRotatedSortedArray(int[] nums,int low,int high,int target){
        while(low <= high){
            int midIndex = (low + high)/2;
            if(target < nums[midIndex]) {
                if(nums[low] <= nums[midIndex]){//前一段为递增区间
                    if(target > nums[low]){
                        high = midIndex -1;
                    }else if (target < nums[low]){
                        low = midIndex+1;
                    }else{
                        return low;
                    }
                }else{//nums[low] > nums[midIndex]后一段为递增区间
                    high = midIndex -1;
                }
//                else{
//                    low = midIndex +1;
//                }
            }else if(target > nums[midIndex]){
                if(nums[low] <= nums[midIndex]){//前一段为递增区间(可以归在一起)
                    low = midIndex+1;
                }else{ //(nums[low] > nums[midIndex])
                    if(target > nums[low]){
                        high = midIndex - 1;
                    }else if (target < nums[low]){
                        low = midIndex + 1;
                    }else{
                        return low;
                    }
                }
//                else{//容易忽略
//                    low = midIndex + 1;
//                }
            }else{//target == nums[midIndex]
                return midIndex;
            }
        }
        return -1;
    }
}
