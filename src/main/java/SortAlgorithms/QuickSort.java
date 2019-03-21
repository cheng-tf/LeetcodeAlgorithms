package SortAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {

    /**
     * 递归函数：重点是递归函数的终止条件
     *
     * @param array
     * @param low
     * @param high
     */
    public void quickSort(int[] array, int low, int high) {
        if (low >= high) return;//递归终止条件：包括=，因为这是排序需求，low==high时候，只有1个数，无需排序
        int mid = partition(array, low, high);//有一次把high写成nums.length-1，导致栈溢出java.lang.StackOverflowError
        quickSort(array, low, mid - 1);
        quickSort(array, mid + 1, high);
    }

    public int partition(int[] nums, int low, int high) {
        return partitionHighPivot(nums, low, high);
//        return partitionLowPivot(nums,low,high);
    }

    /**
     * 分治方法：返回中间值的索引；前一段都小于等于中间值，后一段都大于等于中间值
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int partitionHighPivot(int[] nums, int low, int high) {
//        if(low >= high) return high;//没有必要添加；1由于quickSort的逻辑，不可能出现low>high;2.low==high,后续while也不会执行，最终返回的也是high。
        int pivot = nums[high];//pivot基准
        while (low < high) {//当low>=high时，跳出循环
            while (low < high && nums[low] <= pivot) low++;//nums[low]<=pivot这里包括等号，原因是
            if (low < high) nums[high--] = nums[low];
            while (low < high && nums[high] >= pivot) high--;
            if (low < high) nums[low++] = nums[high];//退出外面的while循环时,执行了当前这句，则high位置就是空缺的；
            // 若是没有执行，说明high==low，又low是空缺的，所以，high也是空缺的。
        }
        nums[high] = pivot;
        return high;
    }

    /**
     * 分治方法：返回中间值的索引；前一段都小于等于中间值，后一段都大于等于中间值
     * 与partition的区别就是：
     */
    public int partitionLowPivot(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {//当low>=high时，跳出循环
            while (low < high && nums[high] >= pivot) high--;//nums[low]<=pivot这里包括等号，原因是
            if (low < high) nums[low++] = nums[high];
            while (low < high && nums[low] <= pivot) low++;
            if (low < high) nums[high--] = nums[low];
        }
        nums[low] = pivot;
        return high;
    }

    /**
     * 实现快速排序的分区操作
     * 利用的是for循环，以High位置元素作为Pivot
     */
    public int partitionForHighPivot(int[] nums, int low, int high) {
        int pivot = nums[high];
        int i = low;
        for (int j = low; j < high; j++) {//注意j一定从low开始，否则是错误的
            if (nums[j] < pivot) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, high);
        return i;
    }

    /**
     * 实现快速排序的分区操作
     * 利用的是for循环，以Low位置元素作为Pivot
     */
    public int partitionForLowPivot(int[] nums, int low, int high) {
        int pivot = nums[low];
        int j = high;
        for (int i = high; i > low; i--) {
            if (nums[i] > pivot) {
                swap(nums, j--, i);
            }
        }
        swap(nums, j, low);
        return j;
    }

    /**
     * 工具函数：交换数组的两个元素的位置
     */
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //测试
    @Test
    public void test() {
        int[] array = {3, 5, 1, -10, 99, 89, 32, 11, 893, -10, 0, 78, 45, 34, 11, -10, 0, 23};
        System.out.println("排序前:" + Arrays.toString((array)));
        quickSort(array, 0, array.length - 1);
        System.out.println("排序后:" + Arrays.toString((array)));
    }

}

