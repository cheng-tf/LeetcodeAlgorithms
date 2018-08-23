package SortAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort {

    /********归并排序*********************************/

    /**
     * 归并排序mergeSort
     * 核心就是归并操作，无论递归和非递归都得使用merge操作。
     * 两种实现方式：1递归方法；非递归方法。
     */

    public void merge(int[] nums,int low,int mid,int high){
        //默认low--mid和mid+1---high为两个排好的序的子数组
        int len = high-low+1;
        int[] temp = new int[len];
        int i = low,j = mid+1;
        int index = 0;
        while(i <= mid&&j<=high){
            temp[index++] = nums[i]<=nums[j]?nums[i++]:nums[j++];
        }
        while(i<=mid) temp[index++] = nums[i++];
        while(j<=high) temp[index++] = nums[j++];
        for(int k = low;k<=high;k++){
            nums[k] = temp[k-low];
        }
    }

    /**
     * 归并排序的递归实现
     * @param nums
     * @param low
     * @param high
     */
    public void mergeSort(int[] nums,int low,int high){
        if(low >= high) return;
        int mid = (low+high)>>1;
        mergeSort(nums,low,mid);
        mergeSort(nums,mid+1,high);
        merge(nums,low,mid,high);
    }

    @Test
    public void test() {
        //测试merge方法
        int[] nums = {1,3,4,5,2,4,6,11};
        System.out.println("Arrays.toString(num) = " + Arrays.toString(nums));
        merge(nums,0,3, nums.length-1);
        System.out.println("Arrays.toString(num) = " + Arrays.toString(nums));

        //递归法：归并排序
        int[] nums2 = {3,5,1,-10,99,89,32,11,893,-10,0,78,45,34,11,-10,0,23};
        System.out.println("Arrays.toString(num2) = " + Arrays.toString(nums2));
        mergeSort(nums2,0,nums2.length-1);
        System.out.println("Arrays.toString(num2) = " + Arrays.toString(nums2));
    }

}
