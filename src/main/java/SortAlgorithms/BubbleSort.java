package SortAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {

    /**********冒泡排序算法：BubbleSort*************************/

    /**
     * 算法思想：不断判断两个相邻元素，若顺序不符，则交换。
     *
     * 编程实现:两层for循环：
     *           1. 外层循环控制比较轮数，需要N-1轮。(每一轮都会将最大致移到最后一位，
     *              移动N-1个元素即可）for(int i = 1;i < len;i++)；
     *           2. 内层循环指定每轮需要的比较次数，第1轮需要N-1次，第2轮需要N-2次，
     *              即for(int j = 0;j < len-i;j++)。
     */

    public void bubbleSort(int[] nums){
        if(nums == null||nums.length<=1) return;
        int len = nums.length;
        for(int i = 1;i < len;i++){//i从1开始，表示第几轮
            for(int j = 0;j < len-i;j++){//第1轮需要比较N-1次
                if(nums[j]>nums[j+1]){
                    swapByXOR(nums,j,j+1);
                }
            }
        }
    }




    /***************************冒泡算法的改进：鸡尾酒排序***********************************/





/********************************************三种交换算法**************************************************/
    /**
     * 方法1：元素交换辅助方法
     */
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    /**
     * 不利用额外空间
     *  方法2：利用异或位运算实现
     *  依据：任何数与本身异或都是0；任何数与0异或都是本身。
     */
    public void swapByXOR(int[] nums,int i,int j){
        nums[i] ^= nums[j];//numsNew[i] = nums[i]^nums[j];
        nums[j] ^= nums[i];//numsNew[j] = numsNew[i]^nums[j]=nums[i]^nums[j]^nums[j]=nums[i];
        nums[i] ^= nums[j];//numsNew[i]=numsNew[i]^numsNew[j]=nums[i]^nums[j]^nums[i]=nums[j]
        //numsNew[j]=nums[i];numsNew[i]=nums[j]
    }
    /**
     * 不利用额外空间
     * 方法2：利用和与差
     */
    public void swapBySum(int[] nums,int i,int j){
        nums[i] = nums[i]+nums[j];
        nums[j] = nums[i]-nums[j];//numsNew[j]=numsNew[i]-nums[j]=nums[i]
        nums[i] = nums[i]-nums[j];
    }
/****************************************************测试*************************************/
    //测试
    @Test
    public void swapTest(){
        int[] nums = {66,99};
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        swap(nums,0,1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        swapByXOR(nums,0,1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        swapBySum(nums,0,1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }

    @Test
    public void test(){
        int[] nums = {2,1,-3,6,90,-23,56};
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        bubbleSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}
