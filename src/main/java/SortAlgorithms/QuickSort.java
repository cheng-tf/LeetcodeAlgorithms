package SortAlgorithms;

import java.util.Arrays;

import org.junit.Test;

public class QuickSort {

    @Test
    //
    public void test(){
        int[] array = {3,5,1,-10,99,89,32,11,893,-10,0,78,45,34,11,-10,0,23};
        System.out.println("排序前:"+Arrays.toString((array)));
        quickSort(array, 0, array.length-1);
        System.out.println("排序后:"+Arrays.toString((array)));
    }
    /**
     * 递归函数：重点是递归函数的终止条件
     * @param array
     * @param low
     * @param high
     */
    public void quickSort(int[] array,int low,int high){
        if(low < high){//递归终止条件
            int mid = partition(array,low,high);
            quickSort(array,low,mid-1);
            quickSort(array,mid+1,high);
        }
    }
    /**
     * 实现快速排序的分区操作
     * @param array
     * @param low
     * @param high
     * @return mid 分区的中间索引
     */
    private int partition(int[] array,int low,int high){
        int pivot = array[high];//随意指定，一般另high处为基准
        int i = low-1;
        for(int j = low;j < high;j++){
//		for(int j = 0;j < high;j++){//注意j一定从low开始，否则是错误的
            if(array[j] < pivot){
                swap(array,++i,j);
            }
        }
        swap(array,++i,high);
        return i;
    }

    /**
     * 工具函数：交换数组的两个元素的位置
     * @param array
     * @param i
     * @param j
     */
    private void swap(int[] array,int i,int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }



}

