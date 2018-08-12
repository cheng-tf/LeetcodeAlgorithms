package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class JianzhiOffer_FindNumbersWithSumOfS {

    @Test
    public void test(){
       int[] nums = {2,3,4,5,6,6,7,8,9,10,12,18};
       ArrayList<Integer> result = FindNumbersWithSum(nums,12);
        System.out.println(result.toString());
    }
    /**
     * 剑指Offer  和为S的两个数字
     * https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&
     * tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 题目描述
     *          输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     * 输出描述:
     *          对应每个测试案例，输出两个数，小的先输出。
     * 思路分析：start和end两个索引，初始分别指向数组开头和末尾；
     * 若两者之和等于S，则计算两者乘积，若乘积小于上次的乘积，则保存到result中；一定要start++或end--；
     * 若两者之和小于S，则start++；
     * 若两者之和大于S，则end--。
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] nums, int S) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(nums==null||nums.length==0) return result;
        int start = 0,end = nums.length-1;
        int currentProduct = Integer.MAX_VALUE;
        while(start < end){
           int currentSum = nums[start] + nums[end];
           if(currentSum == S){
               int product = nums[start]*nums[end];
               if(product < currentProduct){
                   currentProduct = product;//容易忽略
                   result.add(0,nums[start]);
                   result.add(1,nums[end]);
               }
               end--;//或者start++
           }else if(currentSum < S){
               start++;
           }else{
               end--;
           }
        }
        return result;
    }

    /************------------剑指Offer:和为S的正数S序列----------------***************************/

    /**
     *
     * https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&tPage=1&
     * rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 剑指Offer:和为S的正数S序列
     *
     * 思路分析：
     *
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(sum < 3) return result;//至少两个正数
        int start = 1,end = 2;
        int currentSum = start + end;
        int mid = (1+sum)>>2;//start的最大临界值(start<middle)(N-1)+N=sum;则N=(sum+1)/2
        while(start < mid){//循环终止条件
            if(currentSum == sum){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int i = start;i <= end;i++)
                    list.add(i);
                result.add(list);
                start++;
            }
        }


        return null;

    }


}
