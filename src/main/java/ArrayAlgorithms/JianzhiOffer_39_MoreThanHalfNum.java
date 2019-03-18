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
     *         若相同，则加1；若计数器变为0，则需要保存下一个将计数器设为1的数为result。
     *         最终结果就是最后一次将计数器设为1的数。为了保证正确性，需要检查该数出现次数是否出现超过一半。.
     */

    /********************************第一种解法********************************/
    /**
     * 解法1：利用快排的partition方法+二分思想
     * 每次partition之后，只从左右两个区间中选择一个继续排序。
     */
    public int MoreThanHalfNum_Solution1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;//数组为空返回0
        int result = nums[0];//当nums.length=1时，直接默认返回nums[0]
        int low = 0, high = nums.length - 1;
        int half = (nums.length >> 1);
        while (low < high) {
            int mid = partition(nums, low, high);
            if (mid < half) {
                low = mid + 1;
            } else if (mid > half) {
                high = mid - 1;
            } else {
                result = nums[mid];
                break;//必须添加，否则无限循环
            }
        }
        return verify(nums, result) ? result : 0;
    }

    public int MoreThanHalfNum_Solution2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0, high = nums.length - 1;
        int half = (nums.length >> 1);
        int mid = partition(nums, low, high);
        while (mid != half) {//只有当mid等于half时才跳出循环
            if (mid > half) {
                high = mid - 1;
            } else {//mid < half
                low = mid + 1;
            }
            mid = partition(nums, low, high);
        }
        int result = nums[half];//若存在，此时中间位置的元素必定是个数大于一半的元素
        return verify(nums, result) ? result : 0;
    }

    /**
     * 快排的partition方法
     */
    public int partition(int[] nums, int low, int high) {
        int pivot = nums[high];
        while (low < high) {
            while (low < high && nums[low] <= pivot) low++;
            if (low < high) nums[high--] = nums[low];
            while (low < high && nums[high] >= pivot) high--;
            if (low < high) nums[low++] = nums[high];
        }
        nums[high] = pivot;
        return high;
    }

    /**
     * 验证result是否是出现超过一半的数
     */
    public boolean verify(int[] array, int result) {
        int count = 0;
        int half = (array.length >> 1);
        for (int num : array) {
            if (num == result) {
                count++;
                if (count > half) return true;
            }
        }
        return false;
    }


    /**
     * 方法2：利用计数器实现
     */
    public int MoreThanHalfNum_Solution(int[] nums) {
        if (nums == null || nums.length == 0) return 0;//不存在返回0
        int result = nums[0], count = 1;//当nums.length=1时，直接默认返回nums[0]
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                result = nums[i];
            } else {
                if (nums[i] == result) count++;
                else count--;
            }
        }
        return verify(nums, result) ? result : 0;
    }

    //测试
    @Test
    public void test() {
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
//        nums = new int[]{1, -2, 3, 2, 2, 2, 5, 4, 2};
        int result = MoreThanHalfNum_Solution1(nums);
        System.out.println("result = " + result);

        int[] nums2 = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        int result2 = MoreThanHalfNum_Solution(nums2);
        System.out.println("result2 = " + result2);

        int[] nums3 = {1};
        int result3 = MoreThanHalfNum_Solution(nums3);
        System.out.println("result3 = " + result3);
    }

}
