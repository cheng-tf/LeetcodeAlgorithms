package SearchAlgorithm;

import org.junit.Test;
import java.util.Arrays;

    public class LeetCode_34_SearchRange {
        @Test
        public  void test() {
            int[] nums = {5,7,7,8,8,10};
            int[] result = searchRange2(nums,8);
            System.out.println(Arrays.toString(result));
        }

        public  int[] searchRange(int[] nums, int target) {
            int[] result = new int[2];
            result[0] = getLeftBound(nums,0,nums.length - 1,target);
            result[1] = getRightBound(nums,0,nums.length - 1,target);
            return result;
        }

        public  int getLeftBound(int[] nums,int low,int high,int target){
            while(low <= high){
                int midIndex = (low + high)/2;
                if(target < nums[midIndex]){
                    high = midIndex -1;
                }else if(target > nums[midIndex]){
                    low = midIndex + 1;
                }else{//target == nums[midIndex]
                    if(midIndex == 0 || target > nums[midIndex-1]){
                        return midIndex;
                    }
                    high = midIndex -1;
                }
            }
            return -1;
        }

        public  int getRightBound(int[] nums,int low,int high,int target){
            while(low <= high){
                int midIndex = (low + high)/2;
                if(target < nums[midIndex]){
                    high = midIndex -1;
                }else if(target > nums[midIndex]){
                    low = midIndex + 1;
                }else{//target == nums[midIndex]
                    if(midIndex == nums.length -1|| target < nums[midIndex + 1]){
                        return  midIndex;
                    }
                    low = midIndex + 1;
                }
            }
            return -1;
        }




        public int[] searchRange2(int[] nums, int target) {
            int low = 0;
            int high = nums.length - 1;
            int[] result = {-1,-1};
            while(low <= high){//获取左端点
                int midIndex = (low + high)/2;
                if(target < nums[midIndex]){
                    high = midIndex -1;
                }else if(target > nums[midIndex]){
                    low = midIndex + 1;
                }else{
                    if(midIndex == 0 || target > nums[midIndex-1]){
                        result[0] = midIndex;
                        break;
                    }
                    high = midIndex -1;
                }
            }
            low = 0;
            high = nums.length - 1;
            while(low <= high){//获取右端点
                int midIndex = (low + high)/2;
                if(target < nums[midIndex]){
                    high = midIndex -1;
                }else if(target > nums[midIndex]){
                    low = midIndex + 1;
                }else{
                    if(midIndex == nums.length -1|| target < nums[midIndex + 1]){
                        result[1] = midIndex;
                        return result;
                    }
                    low = midIndex + 1;
                }
            }
            return result;
        }
    }


