package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class FullSort {

    @Test
    public void test(){
//        int[] nums = {1,2,3};
        int[] nums = {1,2,3,4};
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        fullSortArray(nums,0,lists);
        //一共应该是n!种情况
        System.out.println("lists.size() = " + lists.size());
        System.out.println("lists = " + lists);
    }


    /**
     *  全排列
     * @param nums  数组
     * @param start 开始索引
     * @param lists 结果集合
     */
    public void fullSortArray(int[] nums,int start,ArrayList<ArrayList<Integer>> lists){
        if(start == nums.length){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int num : nums)
                list.add(num);
            lists.add(list);
//            System.out.println(Arrays.toString(nums));
        }
        for(int i = start;i<nums.length;i++){
            swap(nums,i,start);//交换位置
            fullSortArray(nums,start + 1,lists);//递归调用
            swap(nums,i,start);//复位
        }
    }
    //交换数组中两个位置的元素
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
