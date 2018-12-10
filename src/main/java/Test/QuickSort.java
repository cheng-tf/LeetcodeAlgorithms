package Test;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {

    @Test
    public void test() {
        int[] nums = {23, 45, 23, 66, 34, 9, 65, 3, -34, 233, 65, 232, 3, 0};
        System.out.println(Arrays.toString(nums));
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        System.out.println(binarySearch(nums, 0, nums.length - 1, 233));

    }

    public int binarySearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    public int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        while (start < end) {
            while (start < end && nums[start] <= pivot) start++;
            if (start < end) nums[end--] = nums[start];
            while (start < end && nums[end] >= pivot) end--;
            if (start < end) nums[start++] = nums[end];
        }
        nums[start] = pivot;
        return start;
    }
}
