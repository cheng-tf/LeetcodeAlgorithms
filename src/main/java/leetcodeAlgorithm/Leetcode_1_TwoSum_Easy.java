package leetcodeAlgorithm;

import java.util.HashMap;

public class Leetcode_1_TwoSum_Easy {
    /*************************Leetcode_1_TwoSum_Easy*********************************/

    /**
     * 难度：Easy
     * https://leetcode.com/problems/two-sum/description/
     *
     * Given an array of integers, return indices of the two numbers
     * such that they add up to a specific target.
     * You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     *
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     *
     * 思路分析：利用HashMap实现。
     *          hashMap的key为元素值，value为对应的索引；
     *          遍历一遍即可找到：如nums[i]元素，判断hashMap中是否存在target-nums[i];
     *          若存在，则返回它们对应索引即可，否则将该元素及对应下标存到hashMap中。
     *
     *  旷视科技问了这个算法题目，利用数组充当hashTable的条件比较苛刻，
     *  需要所有元素的值都是正值。由于元素都是正值，所以建立一个target+1大小的数组即可，
     *  大于target的元素直接丢弃即可。
     *  int[] hashTable = new hashTable[target+1];
     *  nums的元素值对应hashTable的下标，hashTable的元素值对应nums的元素下标。
     */

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        for(int i = 0;i < nums.length;i++){
            if(hashMap.containsKey(target-nums[i])){
                return new int[]{i,hashMap.get(target-nums[i])};
            }
            hashMap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }


    /**
     * 都是正值的情况
     */
/*     public int[] twoSum(int[] nums, int target) {
         int[] indexs = new int[]{-1,-1};
         int[] hashTable = new int[target+1];
         for(int i = 0;i < hashTable.length;i++)
             hashTable[i] = -1;
         for(int i = 0;i < nums.length;i++){
             if(nums[i]> target||nums[i]<0) continue;
             hashTable[nums[i]]  = i;
             if(hashTable[target-nums[i]]>-1){
                 indexs[0] = i;
                 indexs[1] = hashTable[target-nums[i]];
                 return indexs;
             }
         }
         return indexs;
     }*/





}
