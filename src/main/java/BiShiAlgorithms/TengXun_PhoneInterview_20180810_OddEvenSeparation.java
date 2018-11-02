package BiShiAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class TengXun_PhoneInterview_20180810_OddEvenSeparation {
    /**
     * 2018-08-10 腾讯校招提前批 电话面试算法题
     * 给定一个存放整型的数组，请将奇数和偶数分离，但保持原有的顺序。
     * 奇数在前面，偶数在后面。
     *
     * 剑指Offer题目：
     * https://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593?tpId=13&
     * tqId=11166&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */

    /**
     * 外循环，从左向右依次遍历，分成两种情况：
     * 1. 如果遇到偶数，则直接continue；
     * 2. 如果遇到奇数，类似冒泡的算法不断向前交换。
     * 每遍历一个元素，得到的当前位置及之前的所有元素满足奇偶分离的要求，
     * 也就是说，在遇到奇数时，直接和前面的偶数交换即可，直到前面的元素不是偶数即可。
     * 所以第二层循环的终止条件是j > 0&&nums[j-1]%2==0；
     */
    /**
     * while循环实现
     *
     * @param array
     */
    public void reOrderArray(int[] array) {
        if (array.length == 0) return;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 0) continue;
            int j = i;//新变量j
            while (--j >= 0 && (array[j] & 1) == 0) {
                swap(array, j, j + 1);
            }
        }
    }

    /**
     * for 循环实现
     *
     * @param nums
     */
    public void oddEvenSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) continue;//偶数直接跳过
            else {//奇数的话需要按照冒泡方法
                for (int j = i; j > 0 && (nums[j - 1] & 1) == 0; j--) {//终止条件是关键
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    /**
     * 交换数组中两个位置的值
     */
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 测试
     */
    @Test
    public void test() {
        int[] nums = {0, 1, 2, 34, 3, 4, 5, 6, 7, 8, 9, 11};
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        oddEvenSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        reOrderArray(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}
