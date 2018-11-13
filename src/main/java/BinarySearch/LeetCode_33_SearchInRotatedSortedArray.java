package BinarySearch;

import org.junit.Test;

public class LeetCode_33_SearchInRotatedSortedArray {
    /**********************旋转数组**********************/
    /**
     * 旋转排序数组：RotatedSortedArray
     * T1：在旋转数组中，寻找某个数值的位置
     * T2：旋转数组的最小数字
     */

    /***************************T1：在旋转数组中，寻找某个数值的位置****************************/
    /**
     * 题目描述:在旋转数组中，寻找某个数值的位置
     * 难度：Medium
     * <p>
     * 思路分析：基于二分查找的思路。
     * 普通排序数组，要么单调上升，要么单调下降。
     * 但是对于旋转数组SortedArray，出现两段不连续上升的情况，如 5,7,9,1,3;
     * 但是，随机选择一个数，将会把旋转数组分成两段，一段为递增区间，一段为旋转区间；
     * 这里的第一任务就是先确定前后各是哪个区间？
     * 确定区间只需要比较一下nums[low]和nums[mid]即可。
     * 若nums[low]<=nums[high]说明前一段是递增区间，后一段是旋转区间；
     * 若nums[low]>nums[high]说明前一段是旋转区间，后一段是递增区间。
     * 然后，只需要target与nums[mid]、nums[low]判断即可知道需要返回哪个区间了。
     * 需要三层判断：
     * F1：第一层nums[low]与nums[mid]的大小判断;确定前后区间性质；
     * F2：第二层：target与nums[mid]的判断;
     * F3：第三层：将target与nums[low]判断。
     */

    /**
     * 方法1：这个更好理解
     */
    public int binarySearchInRotatedSortedArray(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[low] <= nums[mid]) {//第一层判断：前一段为递增区间，后一段为旋转区间；<等号时表示特殊情况，可以归在一起>
                if (target > nums[mid]) {//第二层判断：说明target肯定在mid后面的旋转区间的前一部分
                    low = low + 1;
                } else if (target < nums[mid]) {
                    //target小于nums[mid]对应两种情况；1.mid之前的递增区间；2.mid之后的旋转区间的后一部分
                    if (target > nums[low]) {//确定是情况1，target在mid之前的递增区间
                        high = high - 1;
                    } else if (target < nums[low]) {
                        low = low + 1;
                    } else {//target == nums[low]
                        return low;
                    }
                } else {//target==nums[mid]
                    return mid;
                }
            } else if (nums[low] > nums[mid]) {//第一层判断：确定前一段为旋转区间，后一段为递增区间
                if (target < nums[mid]) {
                    high = high - 1;
                } else if (target > nums[mid]) {
                    if (target > nums[low]) {
                        high = high - 1;
                    } else if (target < nums[low]) {
                        low = low + 1;
                    } else {//target == nums[low]
                        return low;
                    }
                } else {//target == nums[mid]
                    return mid;
                }
            }
        }
        return -1;
    }

    /**
     * 方法2：if语句的判断先后不一样，这个不好理解
     */
    public int binarySearchInRotatedSortedArray_2(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (target < nums[mid]) {//第一层判断targe与nums[mid]
                if (nums[low] <= nums[mid]) {//第二层判断：
                    if (target > nums[low]) {
                        high = mid - 1;
                    } else if (target < nums[low]) {
                        low = mid + 1;
                    } else {
                        return low;
                    }
                } else {//nums[low] > nums[midIndex]后一段为递增区间，前一段为旋转区间
                    high = mid - 1;
                }
//                else{
//                    low = midIndex +1;
//                }
            } else if (target > nums[mid]) {
                if (nums[low] <= nums[mid]) {//前一段为递增区间(可以归在一起)
                    low = mid + 1;
                } else { //(nums[low] > nums[midIndex])
                    if (target > nums[low]) {
                        high = mid - 1;
                    } else if (target < nums[low]) {
                        low = mid + 1;
                    } else {
                        return low;
                    }
                }
//                else{//容易忽略
//                    low = midIndex + 1;
//                }
            } else {//target == nums[midIndex]
                return mid;
            }
        }
        return -1;
    }


    /******************测试*************************/
    @Test
    public void test() {
        int[] nums = {23, 25, 34, 56, 199, 2, 5, 6, 10};
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            int index = binarySearchInRotatedSortedArray(nums, 0, nums.length - 1, target);
            System.out.println("target+\";index = \" + index = " + target + ";index = " + index);
            int index2 = binarySearchInRotatedSortedArray_2(nums, 0, nums.length - 1, target);
            System.out.println("target+\";index = \" + index = " + target + ";index = " + index2);
        }
        int target = 66;
        int index = binarySearchInRotatedSortedArray(nums, 0, nums.length - 1, target);
        System.out.println("target+\";index = \" + index = " + target + ";index = " + index);
        int index2 = binarySearchInRotatedSortedArray_2(nums, 0, nums.length - 1, target);
        System.out.println("target+\";index = \" + index = " + target + ";index = " + index2);
    }


    /*************************T2：旋转数组的最小数字*********************/
    /**
     * 给定一个旋转数组，返回最小数字。
     * 难度：Medium
     * <p>
     * 题目描述：Given a 2d grid map of
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * <p>
     * 思路分析：二分查找的变式训练。
     * 首先判断是否是旋转数组，若不是，则直接返回首元素即可；
     * 然后，通过二分查找方法找寻最小元素：
     * while循环的条件是：循环数组。
     */

    public int minNumberInRotateArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0, high = nums.length - 1;
        if (nums[low] < nums[high]) return nums[low];//没有旋转的情况；注意一定没有等号，因为1,0,1,1也是旋转的情况
//      while (nums[low] >= nums[high]) {//有旋转
        while (low < high) {
            //为什么返回high? 因为之所以能进来，肯定是存在旋转区间，如2,1,2;最小的数至少从第2个开始;即只剩下两个数,肯定返回第二个数
            if (high - low <= 1) return nums[high];//终止条件
            int mid = (low + high) >> 1;
            //重点：对这种特殊情况的处理
            if (nums[mid] == nums[high] && nums[mid] == nums[low]) //三者相同的时候，只能顺序查找如[1,0,1,1,1,1]
                return searchMinNum(nums, low, high);
            //说明：传递mid，不是mid+1或mid-1目的就是让low始终指向前一段，而high指向后一段；
            // 否则low会指向后一段，high会指向前一段
            if (nums[mid] >= nums[low]) {
                low = mid;//一定是mid，不要习惯性mid+1,否则错误
            } else {//nums[mid] < nums[low]
//                if(nums[mid] <= nums[high])//可有可无
                high = mid;//一定是mid，不要习惯性mid-1,否则错误
            }

        }
        return -1;
    }

    //循环查找
    public int searchMinNum(int[] nums, int low, int high) {
        int min = nums[low];
        for (int i = low + 1; i <= high; i++) {
            if (min > nums[i]) min = nums[i];
        }
        return min;
    }

    /******************测试**********************************/
    @Test
    public void test1() {
        int[] nums = {6501, 6828, 6963, 7036, 7422, 7674, 8146, 8468, 8704, 8717, 9170, 9359,
                9719, 9895, 9896, 9913, 9962, 154, 293, 334, 492, 1323, 1479, 1539, 1727,
                1870, 1943, 2383, 2392, 2996, 3282, 3812, 3903, 4465, 4605, 4665, 4772,
                4828, 5142, 5437, 5448, 5668, 5706, 5725, 6300, 6335};
        int min = minNumberInRotateArray(nums);
        System.out.println("min = " + min);
    }
}
