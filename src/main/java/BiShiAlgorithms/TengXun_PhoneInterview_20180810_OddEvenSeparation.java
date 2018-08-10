package BiShiAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class TengXun_PhoneInterview_20180810_OddEvenSeparation {
    /**
     * 2018-08-10 腾讯校招提前批 电话面试算法题
     * 给定一个存放整型的数组，请将奇数和偶数分离，但保持原有的顺序。
     * 奇数在前面，偶数在后面。
     */

    /**
     * 测试
     */
    @Test
    public void test(){
        int[] nums = {0,1,2,34,3,4,5,6,7,8,9,11};
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        oddEvenSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }

    /**
     * 外循环，从左向右依次遍历，分成两种情况：
     * 1. 如果遇到偶数，则直接跳出本次循环；
     * 2. 如果遇到奇数，类似冒泡的算法不断向前交换。
     * 每遍历一个元素，得到的当前位置及之前的所有元素满足奇偶分离的要求，
     * 也就是说，在遇到奇数时，直接和前面的偶数交换即可，直到前面的元素不是偶数即可。
     * 所以第二层循环的终止条件是j > 0&&nums[j-1]%2==0；
     *
     */
    public void oddEvenSort(int[] nums){
        for(int i = 0;i < nums.length;i++){
            if(nums[i]%2 == 0) continue;//偶数直接跳过
            else{//奇数的话需要按照冒泡方法
                for(int j = i;j > 0&&nums[j-1]%2==0;j--){
                    swap(nums,j,j-1);
                }
            }
        }
    }

    /**
     * 交换数组中两个位置的值
     */
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
