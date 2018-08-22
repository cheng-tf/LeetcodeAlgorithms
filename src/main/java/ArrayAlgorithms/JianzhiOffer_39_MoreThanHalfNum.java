package ArrayAlgorithms;

import org.junit.Test;

public class JianzhiOffer_39_MoreThanHalfNum {

    /*********************剑指Offer39：数组中出现次数超过一半的数字*************************/

    /**
     * 问题描述：
     * 题目描述
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * 思路分析：
     * 解法1：依据：排序后，中位数一定就是出现次数超过一半的数。
     *        方法：根据快速排序的partition方法，寻找分割索引为mid即可；
     *        由于可能不存在出现次数超过一半的数，因此，需要遍历一遍统计该值的个数，用来验证是否正确。
     *
     * 解法2：利用一个计数器，初始时为0；保存将计数器设为1的那个数result，若新的数与result不同，则计数器减1，
     *         若相同，则加1；若计数器变为0，则需要保存下一个将计数器设为1的数为result。最终结果就是最后一次
     *         将计数器设为1的数。为了保证正确性，需要检查该数出现次数是否出现超过一半。.
     */

    /**
     *  解法1：利用快排的partition方法
     */
    public int MoreThanHalfNum_Solution1(int [] nums) {
        if(nums == null || nums.length == 0) return 0;//不存在返回0
        int result = nums[0];//当nums.length=1时，直接默认返回nums[0]
        int low = 0,high = nums.length-1,mid = nums.length >> 1;
        while( low < high){
            int index = partition(nums,low,high);
            if(index < mid){
                low = index + 1;
            }else if(index > mid){
                high = index-1;
            }else{
                result = nums[index];
                break;//必须添加，否则无限循环
            }
        }
        int count = 0;
        for(int num:nums){
            if(num == result){
                count++;
                if(count>mid) return result;
            }
        }
        return 0;
    }

    /**
     * 快排的partition方法
     */
    public int partition(int[] nums,int low,int high){
        int i = low,j = high,pivot = nums[high];
        while(i < j){
            while(i < j&& nums[i] <= pivot) i++;
            if(i < j) nums[j--] = nums[i];
            while(i < j&& nums[j] >= pivot) j--;
            if(i < j) nums[i++] = nums[j];
        }
        nums[j] = pivot;
        return j;
    }


    //测试
    @Test
    public void test(){
        int[] nums = {1,2,3,2,2,2,5,4,2};
        int result = MoreThanHalfNum_Solution(nums);
        System.out.println("result = " + result);
        int[] nums2 = {1,2,3,2,4,2,5,2,3};
        int result2 = MoreThanHalfNum_Solution(nums2);
        System.out.println("result2 = " + result2);
        int[] nums3 = {1};
        int result3 = MoreThanHalfNum_Solution(nums3);
        System.out.println("result3 = " + result3);
    }


    /**
     * 方法2：利用计数器实现
     */
    public int MoreThanHalfNum_Solution(int [] nums) {
        if (nums == null || nums.length == 0) return 0;//不存在返回0
        int count = 1,result = nums[0];//当nums.length=1时，直接默认返回nums[0]
        for(int i = 1;i < nums.length;i++){
            if(count == 0){
                count++;
                result = nums[i];
            }else {
                if (nums[i] == result) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        int count2 = 0;
        for(int num:nums){
            if(num == result){
                count2++;
                if(count2>nums.length>>1) return result;
            }
        }
        return 0;
    }



}
