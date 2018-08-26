package SortAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class My {

    public void mergeSort(int[] nums){
        if(nums == null|| nums.length<=1) return;
        int[] copyNums = Arrays.copyOf(nums,nums.length);
        mergeSort(nums,copyNums,0,nums.length-1);
    }

    public void mergeSort(int[] nums,int[] copyNums,int start,int end){
        if(start >= end) return;
//        int mid = (start+end)>>1;//
//         int mid =start +(end-start)>>1;//坑啊，算术运算符的优先级大于位运算符
        int mid =start +((end-start)>>1);
        mergeSort(copyNums,nums,start,mid);
        mergeSort(copyNums,nums,mid+1,end);
        merge(copyNums,nums,start,mid,end);
    }

    public void merge(int[] nums,int[] copyNums,int start,int mid,int end){
        System.out.println("\"ddddd\" = " + "ddddd");
        if(start >= end) return;
        int i = mid,j = end,indexCopy = end;
        while(i>= start&&j>= mid+1){
                copyNums[indexCopy--] = nums[i]>=nums[j]?nums[i--]:nums[j--];
        }
        while(i>=start) copyNums[indexCopy--] = nums[i--];
        while(j>=mid+1) copyNums[indexCopy--] = nums[j--];
    }

    @Test
    public void test(){
        int[] array = {3,5,1,-10};
        System.out.println("排序前:"+Arrays.toString((array)));
        mergeSort(array);
        System.out.println("排序后:"+Arrays.toString((array)));
    }
}
