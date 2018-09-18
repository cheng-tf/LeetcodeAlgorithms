package SearchAlgorithm;

import org.junit.Test;

public class BinarySearch {
    @Test
    public void test() {
        int[] nums = {0, 0, 0, 1, 2, 2, 2, 2, 2, 3, 8, 32, 54, 89, 100, 100, 100, 999};
        for (int target : nums) {
            //递归方法
            System.out.println("binarySearchRecursive = " + binarySearchRecursive(nums, 0, nums.length, target));
            //while循环
            System.out.println("binarySearch = " + binarySearch(nums, 0, nums.length, target));
        }
    }
    /**
     * 最普通的二分查找，若存在多个相同的值(有序则索引必连续)，返回的索引无法确定
     */
    public int binarySearch(int[] nums,int low,int high,int target){
        while(low <= high){//这里包括等号，与快排不一样，快排不包括等号，二分查找必须包括等号，否则出错
            int middleIndex = (low+high)/2;
            if(target < nums[middleIndex]){
                high = middleIndex - 1;
            }else if(target > nums[middleIndex]){
                low = middleIndex + 1;
            }else{
                return middleIndex;//返回查找的索引
            }
        }
        return -1;//不存在则返回-1
    }

    /**
     * 递归方法实现
     */
    public int binarySearchRecursive(int[] nums,int low,int high,int target){
        if(low > high) return -1;//递归终止条件
        int midIndex = (low + high)/2;
        if(target < nums[midIndex]){
            return binarySearchRecursive(nums,low,midIndex-1,target);
        }else if(target > nums[midIndex]){
            return binarySearchRecursive(nums,midIndex+1,high,target);
        }else{
            return midIndex;
        }
    }
}

