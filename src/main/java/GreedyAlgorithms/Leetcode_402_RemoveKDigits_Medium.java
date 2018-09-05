package GreedyAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;

public class Leetcode_402_RemoveKDigits_Medium {
    /**************************************************************/

    /**
     * 难度：Medium
     * https://leetcode.com/problems/remove-k-digits/description/
     *
     * Given a non-negative integer num represented as a string,
     * remove k digits from the number so that the new number is the smallest possible.
     * Note:
     * The length of num is less than 10002 and will be ≥ k.
     * The given num does not contain any leading zero.
     * Example 1:
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     * Example 2:
     * Input: num = "10200", k = 1
     * Output: "200"
     * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
     * Example 3:
     * Input: num = "10", k = 2
     * Output: "0"
     * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     * Example 4:
     * Input: num = "10069", k = 3
     * Output: "0"
     * Explanation: 直接去掉169，剩下0，即结果为0.
     * 注意：在栈为空时，如果来了0，这时候k是不减1的。
     *
     *
     * 思路分析：
     *
     *
     */

    public String removeKdigits(String nums, int k) {
        if(nums == null || nums.length() == 0) return "0";
        if(k == 0) return nums;
        char[] numChar = nums.toCharArray();
        ArrayDeque<Character> deque = new ArrayDeque<Character>();
        for(int i = 0;i < numChar.length;i++){
            while(!deque.isEmpty() && k > 0 && numChar[i] < deque.peekFirst()) {
                deque.pop();//删除栈顶元素
                k--;
            }
            if(!deque.isEmpty()||numChar[i] != '0'){
                deque.push(numChar[i]);//将新数字入栈
            }
        }
        //至此，栈中已经是排序的情况
        while(!deque.isEmpty()&&k>0){
            deque.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.removeLast());
        }
        String result = sb.toString();
        return "".equals(result)?"0":result;
    }

    //测试
    @Test
    public void test(){
        String str = "1432219";
        String result = removeKdigits(str,3);
        System.out.println("result = " + result);

        String str2 = "1234567890";
        String result2 = removeKdigits(str2,9);
        System.out.println("result2 = " + result2);
    }


}
