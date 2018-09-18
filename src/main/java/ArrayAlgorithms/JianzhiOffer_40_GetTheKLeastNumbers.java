package ArrayAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class JianzhiOffer_40_GetTheKLeastNumbers {

    /***************剑指Offer40:最小的k个数******************/
    /**
     * 题目描述
     * 输入n个整数，找出其中最小的K个数。
     * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     *
     * 思路分析:注意边界情况：k小于等于0时或大于数组长度时，直接返回空list；
     *
     * 方法1：利用快排对数组排序，然后输出最小的k个数至list中即可；
     * 方法2：利用partition方法，只要找到分割的mid为k或k-1即可；(剑指Offer超时)
     *        按理来说，这种算法应该比快排更快，但是超时不通过。
     * 方法2：待定。
     */

    /**
     * 方法1：快排
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] nums, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(nums == null|| nums.length==0||k<=0||k>nums.length) return list;
        quickSort(nums,0,nums.length-1);
        int i = 0;
        while(i < k ) list.add(nums[i++]);
        return list;
    }
    /**
     * 快排
     */
    public void quickSort(int[] nums,int low,int high){
        if(low >= high) return;
        int mid = partition(nums,low,high);
        quickSort(nums,low,mid-1);
        quickSort(nums,mid+1,high);
    }
    /**
     * 快排的partition方法
     */
    public int partition(int[] nums,int low,int high){
        int pivot = nums[high];
        while(low < high){
            while(low < high && nums[low] <= pivot) low++;
            if(low < high) nums[high--] = nums[low];
            while(low < high && nums[high] >= pivot) high--;
            if(low < high) nums[low++] = nums[high];
        }
        nums[high] = pivot;
        return high;
    }

    /**
     * 方法2：仅仅利用partition方法
     */

    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] nums, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(nums == null|| nums.length==0||k < 1||k>nums.length) return list;
        int low = 0,high = nums.length-1;
        int mid = partition(nums,low,high);
        while(mid != k){
            if(mid > k){
                high = mid-1;
            }else{
                low = mid+1;
            }
            mid = partition(nums,low,high);
        }
        while(--k >= 0 ) list.add(nums[k]);
        return list;
    }

    /**
     * 方法3：堆排序：
     * 用最大堆保存这k个数，每次只和堆顶比，如果比堆顶小，删除堆顶，新数入堆。
     */

    /*
    import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
public class Solution {
   public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
       ArrayList<Integer> result = new ArrayList<Integer>();
       int length = input.length;
       if(k > length || k == 0){
           return result;
       }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < length; i++) {
            if (maxHeap.size() != k) {
                maxHeap.offer(input[i]);
            } else if (maxHeap.peek() > input[i]) {
                Integer temp = maxHeap.poll();
                temp = null;
                maxHeap.offer(input[i]);
            }
        }
        for (Integer integer : maxHeap) {
            result.add(integer);
        }
        return result;
    }
}
     */

    /*



*基于堆排序算法，构建最大堆。时间复杂度为O(nlogk)
*如果用快速排序，时间复杂度为O(nlogn)；
*如果用冒泡排序，时间复杂度为O(n*k)

import java.util.ArrayList;
    public class Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            ArrayList<Integer> list=new ArrayList<Integer>();
            //检查输入的特殊情况
            if(input==null || input.length<=0 || input.length<k){
                return list;
            }
            //构建最大堆
            for(int len=k/2-1; len>=0; len--){
                adjustMaxHeapSort(input,len,k-1);
            }
            //从第k个元素开始分别与最大堆的最大值做比较，如果比最大值小，则替换并调整堆。
            //最终堆里的就是最小的K个数。
            int tmp;
            for(int i=k; i<input.length; i++){
                if(input[i]<input[0]){
                    tmp=input[0];
                    input[0]=input[i];
                    input[i]=tmp;
                    adjustMaxHeapSort(input,0,k-1);
                }
            }
            for(int j=0; j<k; j++){
                list.add(input[j]);
            }
            return list;
        }

        public void adjustMaxHeapSort(int[] input, int pos, int length){
            int temp;
            int child;
            for(temp=input[pos]; 2*pos+1<=length; pos=child){
                child=2*pos+1;
                if(child<length && input[child]<input[child+1]){
                    child++;
                }
                if(input[child]>temp){
                    input[pos]=input[child];
                }else{
                    break;
                }
            }
            input[pos]=temp;
        }
    }

     */



    //测试
    @Test
    public void test(){
        int[] nums = {2,3,4,5,1,3,64,23,-3};
        int[] nums2 = {4,5,1,6,2,7,3,8};
        ArrayList<Integer> list = GetLeastNumbers_Solution(nums2,17);
        System.out.println(list.toString());
        ArrayList<Integer> list2 = GetLeastNumbers_Solution2(nums2,4);
        System.out.println(list2.toString());
    }

}
