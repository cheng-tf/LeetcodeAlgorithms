package SearchAlgorithm;

import org.junit.Test;
import java.util.Arrays;

    public class LeetCode_34_SearchRange {

        @Test
        public void test(){
            int[] nums = {0,0,0,1,2,2,2,2,2,3,8,32,54,89,100,100,100,999};
            for(int target : nums){
                //测试左边界
                int indexLeft = binarySearchLeft(nums,0,nums.length-1,target);
                //测试右边界
                int indexRight = binarySearchRight(nums,0,nums.length-1,target);
                System.out.println("indexLeft = " + indexLeft + ";indexRight = " + indexRight);
            }
            //获取范围
            for(int target:nums){
                System.out.println("binarySearchRange = " + Arrays.toString(binarySearchRange(nums,0,nums.length-1,target)));
                System.out.println("binarySearchRange2 = " + Arrays.toString(binarySearchRange2(nums,0,nums.length-1,target)));
                System.out.println("binarySearchRange3 = " + Arrays.toString(binarySearchRange3(nums,0,nums.length-1,target)));
            }
        }

        /**
         * 获取范围1
         */
        public int[] binarySearchRange(int[] nums,int low,int high,int target){
            int[] result = new int[2];
            result[0] = binarySearchLeft(nums,low,high,target);
            result[1] = binarySearchRight(nums,low,high,target);
            return result;
        }
        /**
         * 获取范围2
         */
        public int[] binarySearchRange2(int[] nums,int low,int high,int target){
            int[] result = new int[2];
            result[0] = binarySearchLeft2(nums,low,high,target);
            result[1] = binarySearchRight2(nums,low,high,target);
            return result;
        }

        /**
         * 获取范围2：综合
         */
        public int[] binarySearchRange3(int[] nums,int low,int high,int target){
            int low1 = low,high1 = high;
            int[] result = {-1,-1};
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
         * 若数组中存在多个相同的值，返回最左边那个值的索引
         */
        public int binarySearchLeft(int[] nums,int low,int high,int target){
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

        //判断是否到达左端点
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
        public int binarySearchRight(int[] nums,int low,int high,int target){
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

        //是否到达右端点
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




    }


