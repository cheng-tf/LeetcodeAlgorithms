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

    /**
     * 题目描述:在旋转数组中，寻找某个数值的位置
     *
     * 思路分析：基于二分查找的思路，更改targe大于或小于nums[mid]情况下的处理情况。
     *  需要三层判断：第一层targe与nums[mid]的大小判断；
     *  第二层：nums[low]与nums[mid]的判断，得出前后哪一段是递增的；
     *  第三层：将target与nums[low]判断，到底返回递增段还是旋转段。
     */
    public int binarySearchInRotatedSortedArray(int[] nums,int low,int high,int target){
        while(low <= high){
            int midIndex = (low + high)/2;
            if(target < nums[midIndex]) {
                if(nums[low] <= nums[midIndex]){//前一段为递增区间，后一段为旋转区间
                    if(target > nums[low]){
                        high = midIndex -1;
                    }else if (target < nums[low]){
                        low = midIndex+1;
                    }else{
                        return low;
                    }
                }else{//nums[low] > nums[midIndex]后一段为递增区间，前一段为旋转区间
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



    /*************************剑指Offer题目：旋转数组的最小数字*********************/
    /**
     * 给定一个旋转数组，返回最小数字。
     * 题目描述：
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */

    @Test
    public void test1(){
        int[] nums = {6501,6828,6963,7036,7422,7674,8146,8468,8704,8717,9170,9359,
                9719,9895,9896,9913,9962,154,293,334,492,1323,1479,1539,1727,
                1870,1943,2383,2392,2996,3282,3812,3903,4465,4605,4665,4772,
                4828,5142,5437,5448,5668,5706,5725,6300,6335};
        int min = minNumberInRotateArray(nums);
        System.out.println("min = " + min);
    }

    public int minNumberInRotateArray(int [] nums) {
        if(nums == null||nums.length == 0) return 0;
        int low = 0,high = nums.length-1;
        if(nums[low] < nums[high]) return nums[low]; //没有旋转的情况
        while(nums[low] >= nums[high]){//有旋转
            if(high - low == 1) return nums[high];//终止条件
            int mid = (low + high)>>1;
            if(nums[mid]==nums[high]&&nums[mid]==nums[low]){//三者相同的时候，只能顺序查找如[1,0,1,1,1,1]
                return searchMinNum(nums,low,high);
            }
            //说明：传递mid，不是mid+1或mid-1目的就是让low始终指向前一段，而high指向后一段；
            // 否则low会指向后一段，high会指向前一段
            if(nums[mid] >= nums[low]){
                low = mid;//一定是mid，不要习惯性mid+1,否则错误
            }else{//nums[mid] < nums[low]
//                if(nums[mid] <= nums[high])//可有可无
                         high = mid;//一定是mid，不要习惯性mid-1,否则错误
            }
        }
        return -1;
    }

    //循环查找
    public int searchMinNum(int[] nums,int low,int high){
        int min = nums[low];
        for(int i = low+1;i <= high;i++){
            if(min > nums[i]) min = nums[i];
        }
        return min;
    }


}
