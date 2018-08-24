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

    public void merge2(int[] nums,int low,int mid,int high){
        //默认low--mid和mid+1---high为两个排好的序的子数组
        int len = high-low+1;
        int[] temp = new int[len];//每次merge都要创建一个数组
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
    public void mergeSort2(int[] nums,int low,int high){
        if(low >= high) return;
        int mid = (low+high)>>1;
        mergeSort2(nums,low,mid);
        mergeSort2(nums,mid+1,high);
        merge2(nums,low,mid,high);
    }

    @Test
    public void test() {
        //测试merge方法
        int[] nums = {1,3,4,5,2,4,6,11};
        System.out.println("Arrays.toString(num) = " + Arrays.toString(nums));
        merge2(nums,0,3, nums.length-1);
        System.out.println("Arrays.toString(num) = " + Arrays.toString(nums));

        //递归法：归并排序
        int[] nums2 = {3,5,1,-10,99,89,32,11,893,-10,0,78,45,34,11,-10,0,23,8};
        System.out.println("Arrays.toString(num2) = " + Arrays.toString(nums2));
        mergeSort2(nums2,0,nums2.length-1);
        System.out.println("Arrays.toString(num2) = " + Arrays.toString(nums2));

        //递归法：归并排序
        int[] nums3 = {3,5,1,-10,99,89,32,11,893,-10,0,78,45,34,11,-10,0,23,8,-9,0};
        System.out.println("Arrays.toString(num3) = " + Arrays.toString(nums3));
        mergeSort(nums3);
        System.out.println("Arrays.toString(num3) = " + Arrays.toString(nums3));
    }


    /***********************归并排序：使用固定的辅助数组**********************************/
    public void mergeSort(int[] nums){//经过测试，无论nums数组是奇数还是偶数，正确结果都存在nums中，而copyNums差一次
        int[] copyNums = Arrays.copyOf(nums,nums.length);
        mergeSort(nums,copyNums,0,nums.length-1);
    }

    public void mergeSort(int[] nums,int[] copyNums,int low,int high){
        if(nums == null|| nums.length <= 1||low >= high) return ;
        int mid = (low + high)>>1;
        mergeSort(copyNums,nums,low,mid);//交替使用nums和copyNums
        mergeSort(copyNums,nums,mid+1,high);
        mergeTowSubArray(copyNums,nums,low,mid,high);//将最终排序结果拷贝至nums中
    }

    public void mergeTowSubArray(int[] nums,int[] copyNums,int low,int mid,int high){
        int i = mid,j = high,indexCopy = high;
        while(i >= low&&j >= mid+1){
            copyNums[indexCopy--] = nums[i]>nums[j]?nums[i--]:nums[j--];
        }
        while(i>=low) copyNums[indexCopy--] = nums[i--];
        while(j>=mid+1) copyNums[indexCopy--] = nums[j--];
    }
}
